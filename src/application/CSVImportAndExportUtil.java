package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ResultSetHelperService;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;



public class CSVImportAndExportUtil {

	private final String URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	private final String uname = "root";
	private final String pass = "masiqi93";

	private final String CONTACTS_FILE_PATH = "./data/contacts.csv";
	private final String LEADS_FILE_PATH = "./data/leads.csv";
	private final String TASKS_FILE_PATH = "./data/tasks.csv";
	private final String ACTIVITIES_FILE_PATH = "./data/activities.csv";
	
	ContactsDAO contactsDAO = new ContactsDAO();
	LeadsDAO leadsDAO = new LeadsDAO();
	TasksDAO tasksDAO = new TasksDAO();
	ActivitiesDAO activitiesDAO = new ActivitiesDAO();


	public boolean exportContactsToCSV() {
		return(exportUtilToCSV("contacts", CONTACTS_FILE_PATH));
	}

	public boolean exportLeadsToCSV() {
		return(exportUtilToCSV("leads", LEADS_FILE_PATH));
	}

	public boolean exportTasksToCSV() {
		return(exportUtilToCSV("tasks", TASKS_FILE_PATH));
	}

	public boolean exportActivitiesToCSV() {
		return(exportUtilToCSV("activities", ACTIVITIES_FILE_PATH));
	}


	private boolean exportUtilToCSV(String type,String filepath) {

		String query = "SELECT * FROM " + type;
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			try (CSVWriter writer = new CSVWriter(new FileWriter(filepath))) {

				ResultSetHelperService service = new ResultSetHelperService(); 
				service.setDateTimeFormat("yyyy-MM-dd HH:mm:ss"); 
				writer.setResultService(service);

				writer.writeAll(rs, true, false, true); 
				st.close();
				con.close();
				return true;

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}


	}

	public ResultSet getResultSet(String tableName) {

		String query = "SELECT * FROM "+ tableName;
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private List<String[]> readImportCSVToList(String filePath){

		FileReader filereader;
		try {
			filereader = new FileReader(filePath);
			try (CSVReader csvReader = new CSVReader(filereader)) {
				try {
					List<String[]> allRecord = csvReader.readAll();
					return allRecord;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CsvException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private int getCSVRowSizeIncHeader(List<String[]> list) {
		if (!list.isEmpty()) {
			return(list.size());
		} else {
			return 0;
		}
	}

	private int getCSVColumnSize(List<String[]> list) {
		if (!list.isEmpty()) {
			return(list.get(0).length);
		} else {
			return 0;
		}
	}

	private boolean isColumnTypeUtil (String tableName, int columnIndex,String dataTypeInLowerCase) {
		ResultSet rs = getResultSet(tableName);
		try {

			ResultSetMetaData rsmd = rs.getMetaData();

			boolean result = (rsmd.getColumnClassName(columnIndex).toLowerCase().contains(dataTypeInLowerCase));
			return result;

		} catch (SQLException e) {
			return false;
		}
	}


	public boolean isColumnTypeString (String tableName,  int columnIndex) {
		return isColumnTypeUtil(tableName, columnIndex, "string");
	}
	public boolean isColumnTypeInteger (String tableName,  int columnIndex) {
		return isColumnTypeUtil(tableName, columnIndex, "int");
	}
	public boolean isColumnTypeTimestamp (String tableName,  int columnIndex) {
		return isColumnTypeUtil(tableName, columnIndex, "timestamp");
	}

	private void updateAllContactsID(List<String[]> contactsCSV,List<String[]> leadsCSV,List<String[]> tasksCSV,List<String[]> activitiesCSV, int old_contact_id, int new_contact_id) {



		int column_index_in_contacts = searchColumnIndexFromLabelCSV(contactsCSV, "contact_id");
		int column_index_in_leads = searchColumnIndexFromLabelCSV(leadsCSV, "contact_id");
		int column_index_in_tasks = searchColumnIndexFromLabelCSV(tasksCSV, "contact_id");
		int column_index_in_activities = searchColumnIndexFromLabelCSV(activitiesCSV, "contact_id");

		int total_rows_contacts = getCSVRowSizeIncHeader(contactsCSV);
		int total_rows_leads = getCSVRowSizeIncHeader(leadsCSV);
		int total_rows_tasks = getCSVRowSizeIncHeader(tasksCSV);
		int total_rows_activities = getCSVRowSizeIncHeader(activitiesCSV);

		//----------------developer testing functions-----------------------------
		//System.out.println(column_index_in_contacts+" "+column_index_in_leads+" "+column_index_in_tasks+" "+column_index_in_activities);
		//System.out.println(total_rows_contacts+" "+total_rows_leads+" "+total_rows_tasks+" "+total_rows_activities);
		//------------------------------------------------------------------------

		for (int i=1;i<total_rows_contacts;i++) {
			if (Integer.valueOf(contactsCSV.get(i)[column_index_in_contacts]) == old_contact_id) {
				contactsCSV.get(i)[column_index_in_contacts] = String.valueOf(new_contact_id); 
			}
		}

		for (int i=1;i<total_rows_leads;i++) {
			if (Integer.valueOf(leadsCSV.get(i)[column_index_in_leads]) == old_contact_id) {
				leadsCSV.get(i)[column_index_in_leads] = String.valueOf(new_contact_id); 
			}
		}

		for (int i=1;i<total_rows_tasks;i++) {
			if (Integer.valueOf(tasksCSV.get(i)[column_index_in_tasks]) == old_contact_id) {
				tasksCSV.get(i)[column_index_in_tasks] = String.valueOf(new_contact_id); 
			}
		}

		for (int i=1;i<total_rows_activities;i++) {
			if (Integer.valueOf(activitiesCSV.get(i)[column_index_in_activities]) == old_contact_id) {
				activitiesCSV.get(i)[column_index_in_activities] = String.valueOf(new_contact_id); 
			}
		}

	}

	private void updateTaskID (List<String[]> tasksCSV, int old_task_id, int new_task_id) {

		int column_index_in_tasks = searchColumnIndexFromLabelCSV(tasksCSV, "task_id");
		int total_rows_tasks = getCSVRowSizeIncHeader(tasksCSV);

		for (int i=1;i<total_rows_tasks;i++) {
			if (Integer.valueOf(tasksCSV.get(i)[column_index_in_tasks]) == old_task_id) {
				tasksCSV.get(i)[column_index_in_tasks] = String.valueOf(new_task_id); 
			}
		}

	}

	private void updateActivityID (List<String[]> activitiesCSV, int old_activity_id, int new_activity_id) {

		int column_index_in_activities = searchColumnIndexFromLabelCSV(activitiesCSV, "activity_id");
		int total_rows_activities = getCSVRowSizeIncHeader(activitiesCSV);

		for (int i=1;i<total_rows_activities;i++) {
			if (Integer.valueOf(activitiesCSV.get(i)[column_index_in_activities]) == old_activity_id) {
				activitiesCSV.get(i)[column_index_in_activities] = String.valueOf(new_activity_id); 
			}
		}

	}

	private int searchColumnIndexFromLabelCSV(List<String[]> list,String label) {
		for(int i=0;i<list.get(0).length;i++) {
			if (list.get(0)[i].equals(label)) {
				return i;
			}
		}
		return -1;
	}
	
	private void updateAllEmptyCellToNull(List<String[]> contactsCSV,List<String[]> leadsCSV,List<String[]> tasksCSV,List<String[]> activitiesCSV) {
		
		int total_cols_contacts = getCSVColumnSize(contactsCSV);
		int total_cols_leads = getCSVColumnSize(leadsCSV);
		int total_cols_tasks = getCSVColumnSize(tasksCSV);
		int total_cols_activities = getCSVColumnSize(activitiesCSV);

		int total_rows_contacts = getCSVRowSizeIncHeader(contactsCSV);
		int total_rows_leads = getCSVRowSizeIncHeader(leadsCSV);
		int total_rows_tasks = getCSVRowSizeIncHeader(tasksCSV);
		int total_rows_activities = getCSVRowSizeIncHeader(activitiesCSV);
		
		for (int i=0;i<total_rows_contacts;i++) {
			for (int j=0;j<total_cols_contacts;j++) {
				if (contactsCSV.get(i)[j].isBlank()) {
					contactsCSV.get(i)[j] = null;
				}
			}
		}
		
		for (int i=0;i<total_rows_leads;i++) {
			for (int j=0;j<total_cols_leads;j++) {
				if (leadsCSV.get(i)[j].isBlank()) {
					leadsCSV.get(i)[j] = null;
				}
			}
		}
		
		for (int i=0;i<total_rows_tasks;i++) {
			for (int j=0;j<total_cols_tasks;j++) {
				if (tasksCSV.get(i)[j].isBlank()) {
					tasksCSV.get(i)[j] = null;
				}
			}
		}
		
		for (int i=0;i<total_rows_activities;i++) {
			for (int j=0;j<total_cols_activities;j++) {
				if (activitiesCSV.get(i)[j].isBlank()) {
					activitiesCSV.get(i)[j] = null;
				}
			} 
		}
		
	}
	
	
	public boolean isHeaderRow(String[] row) {
		
		Pattern p = Pattern.compile("\\d{1,}");
		Matcher m = p.matcher(row[0]);
		boolean b = m.matches();
		
		// if matches, then not a header row
		
		if(b) {
			return false;
		} else {
			return true;
		}
	}

	public void importAllData() throws IOException, CsvException {

		List<String[]> contactsCSV = readImportCSVToList(CONTACTS_FILE_PATH);
		List<String[]> leadsCSV = readImportCSVToList(LEADS_FILE_PATH);
		List<String[]> tasksCSV = readImportCSVToList(TASKS_FILE_PATH);
		List<String[]> activitiesCSV = readImportCSVToList(ACTIVITIES_FILE_PATH);


		updateAllEmptyCellToNull(contactsCSV, leadsCSV, tasksCSV, activitiesCSV);
		
		for(String[] row:contactsCSV) {
			if (isHeaderRow(row)) {
				// do nothing;
			} else if (contactsDAO.isContactInfoExistInSQL(row)) {
				// do nothing;
			} else if (contactsDAO.isContactIDExistInSQL(row) == true){
				int newID = contactsDAO.getLargestContactIDFromSQL() + 1;
				int oldID = Integer.valueOf(row[0]);
				updateAllContactsID(contactsCSV, leadsCSV, tasksCSV, activitiesCSV, oldID, newID);
				contactsDAO.importContactWithID(row);
			} else if (contactsDAO.isContactIDExistInSQL(row) == false) {
				contactsDAO.importContactWithDefaultID(row);
			}
		}
		
		for(String[] row:leadsCSV) {
			if (isHeaderRow(row)) {
				// do nothing;
			} else if (leadsDAO.isLeadExistInSQL(row)){
				//do nothing;
			} else {
				leadsDAO.importLeadWithID(row);
			}
		}
		
		for(String[] row:tasksCSV) {
			
			if (isHeaderRow(row)) {
				// do nothing;
			} else if(tasksDAO.isTaskInfoExistInSQL(row)){
				// do nothing;
			} else {
				tasksDAO.importTask(row);
			}
		}
		
		for(String[] row:activitiesCSV) {
			
			if (isHeaderRow(row)) {
				// do nothing;
			} else if(activitiesDAO.isActivityInfoExistInSQL(row)) {
				// do nothing;
			} else {
				activitiesDAO.importActivity(row);
			}
		}
		

	}







}
