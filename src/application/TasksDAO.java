package application;

import java.sql.*;
import java.util.ArrayList;

public class TasksDAO {

	String URL = DataBaseConnectionCredentials.URL;
	String uname = DataBaseConnectionCredentials.username;
	String pass = DataBaseConnectionCredentials.password;


	
	public void deleteAllTasks() {

		String query = "TRUNCATE TABLE tasks";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			st.execute(query);

			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public ArrayList<Tasks> getAllTasks() {

		ArrayList<Tasks> tasks = new ArrayList<>();
		String query = "SELECT * FROM tasks";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Tasks task = new Tasks();
				task.setContact_id(rs.getInt("contact_id"));
				task.setTask_id(rs.getInt("task_id"));
				task.setTask_type(rs.getString("task_type"));
				task.setTask_summary(rs.getString("task_summary"));
				task.setTask_description(rs.getString("task_description"));
				task.setTask_created_by(rs.getString("task_created_by"));
				task.setTask_created_date_and_time(rs.getTimestamp("task_created_date_and_time"));
				task.setTask_assigned_to(rs.getString("task_assigned_to"));
				task.setDue_date_and_time(rs.getTimestamp("due_date_and_time"));
				task.setPriority(rs.getString("priority"));
				task.setProgress(rs.getInt("progress"));
				task.setTask_current_status(rs.getString("task_current_status"));
				tasks.add(task);
			}

			return tasks;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean isTaskInfoExistInSQL(String[] row) {
		String query = "SELECT * FROM tasks";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				if(rs.getTimestamp("task_created_date_and_time").equals(Timestamp.valueOf(row[6]))) {
					return true;
				}
			}

			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public ArrayList<Tasks> getTaskFromContactID(int contact_id) {

		String query = "SELECT * FROM tasks WHERE contact_id = " + contact_id;
		Connection con;
		ArrayList<Tasks> tasks = new ArrayList<>(); 

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {

				Tasks task = new Tasks();
				task.setContact_id(rs.getInt("contact_id"));

				task.setTask_id(rs.getInt("task_id"));

				if (rs.getString("task_type") != null){
					task.setTask_type(rs.getString("task_type"));
				} else {
					task.setTask_type("");
				}

				if (rs.getString("task_summary") != null){
					task.setTask_summary(rs.getString("task_summary"));
				} else {
					task.setTask_summary("");
				}

				if (rs.getString("task_description") != null){
					task.setTask_description(rs.getString("task_description"));
				} else {
					task.setTask_description("");
				}

				if (rs.getString("task_created_by") != null){
					task.setTask_created_by(rs.getString("task_created_by"));
				} else {
					task.setTask_created_by("");
				}

				if (rs.getTimestamp("task_created_date_and_time") != null){
					task.setTask_created_date_and_time(rs.getTimestamp("task_created_date_and_time"));
				} else {
					task.setTask_created_date_and_time(null);
				}

				if (rs.getString("task_assigned_to") != null){
					task.setTask_assigned_to(rs.getString("task_assigned_to"));
				} else {
					task.setTask_assigned_to("");
				}

				if (rs.getTimestamp("due_date_and_time") != null){
					task.setDue_date_and_time(rs.getTimestamp("due_date_and_time"));
				} else {
					task.setDue_date_and_time(null);
				}

				if (rs.getString("priority") != null){
					task.setPriority(rs.getString("priority"));
				} else {
					task.setPriority("");
				}

				task.setProgress(rs.getInt("progress"));

				if (rs.getString("task_current_status") != null){
					task.setTask_current_status(rs.getString("task_current_status"));
				} else {
					task.setTask_current_status("");
				}

				tasks.add(task);
			}

			return tasks;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Tasks getTaskFromTaskID(int task_id) {

		String query = "SELECT * FROM tasks WHERE task_id = " + task_id;
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();

			Tasks task = new Tasks();
			task.setContact_id(rs.getInt("contact_id"));

			task.setTask_id(rs.getInt("task_id"));

			if (rs.getString("task_type") != null){
				task.setTask_type(rs.getString("task_type"));
			} else {
				task.setTask_type("");
			}

			if (rs.getString("task_summary") != null){
				task.setTask_summary(rs.getString("task_summary"));
			} else {
				task.setTask_summary("");
			}

			if (rs.getString("task_description") != null){
				task.setTask_description(rs.getString("task_description"));
			} else {
				task.setTask_description("");
			}

			if (rs.getString("task_created_by") != null){
				task.setTask_created_by(rs.getString("task_created_by"));
			} else {
				task.setTask_created_by("");
			}

			if (rs.getTimestamp("task_created_date_and_time") != null){
				task.setTask_created_date_and_time(rs.getTimestamp("task_created_date_and_time"));
			} else {
				task.setTask_created_date_and_time(null);
			}

			if (rs.getString("task_assigned_to") != null){
				task.setTask_assigned_to(rs.getString("task_assigned_to"));
			} else {
				task.setTask_assigned_to("");
			}

			if (rs.getTimestamp("due_date_and_time") != null){
				task.setDue_date_and_time(rs.getTimestamp("due_date_and_time"));
			} else {
				task.setDue_date_and_time(null);
			}

			if (rs.getString("priority") != null){
				task.setPriority(rs.getString("priority"));
			} else {
				task.setPriority("");
			}

			task.setProgress(rs.getInt("progress"));

			if (rs.getString("task_current_status") != null){
				task.setTask_current_status(rs.getString("task_current_status"));
			} else {
				task.setTask_current_status("");
			}



			return task;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean checkTaskExist(Timestamp task_created_date_and_time) {

		String query = "SELECT * FROM tasks";
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				if(rs.getTimestamp("task_created_date_and_time").equals(task_created_date_and_time)) {
					return true;
				}
			}

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}


	public void deleteTaskFromTaskID(int task_id) {

		String query = "DELETE FROM tasks WHERE task_id = " + task_id;
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			st.execute(query);

			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteMultipleTaskFromContactID(int contact_id) {

		String query = "DELETE FROM tasks WHERE contact_id = " + contact_id;
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			st.execute(query);

			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean addNewTask(int contact_id,int task_id,String task_type,String task_summary,String task_description,String task_created_by,Timestamp task_created_date_and_time,String task_assigned_to,Timestamp due_date_and_time,String priority,int progress,String task_current_status) {

		if (checkTaskExist(task_created_date_and_time)) {
			return false;
		}

		if (task_type == null || task_created_by == null || task_created_date_and_time == null ) {

			return false;
		}

		String query = "INSERT INTO tasks VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?)";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, contact_id);
			st.setString(2, task_type);
			st.setString(3, task_summary);
			st.setString(4, task_description);
			st.setString(5, task_created_by);
			st.setTimestamp(6, task_created_date_and_time);
			st.setString(7, task_assigned_to);
			st.setTimestamp(8, due_date_and_time);
			st.setString(9, priority);
			st.setInt(10, progress);
			st.setString(11, task_current_status);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	
	public boolean importTask(String[] row) {
		
		
		int contact_id = Integer.valueOf(row[1]);
		int task_id = Integer.valueOf(row[0]);
		String task_type = row[2];
		String task_summary = row[3];
		String task_description = row[4];
		String task_created_by = row[5];
		Timestamp task_created_date_and_time = Timestamp.valueOf(row[6]);
		String task_assigned_to = row[7];
		Timestamp due_date_and_time;
		if (row[8] == null) {
			due_date_and_time = null;
		} else {
			due_date_and_time = Timestamp.valueOf(row[8]);
		}

		String priority = row[9];
		int progress = Integer.valueOf(row[10]);
		String task_current_status = row[11];
		

		if (task_type == null || task_created_by == null || task_created_date_and_time == null ) {

			return false;
		}

		String query = "INSERT INTO tasks VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?)";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, contact_id);
			st.setString(2, task_type);
			st.setString(3, task_summary);
			st.setString(4, task_description);
			st.setString(5, task_created_by);
			st.setTimestamp(6, task_created_date_and_time);
			st.setString(7, task_assigned_to);
			st.setTimestamp(8, due_date_and_time);
			st.setString(9, priority);
			st.setInt(10, progress);
			st.setString(11, task_current_status);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}


	public boolean modifyTaskFromTaskID(int contact_id,int task_id,String task_type,String task_summary,String task_description,String task_created_by,Timestamp task_created_date_and_time,String task_assigned_to,Timestamp due_date_and_time,String priority,int progress,String task_current_status) {

		if (task_type == null || task_created_by == null || task_created_date_and_time == null ) {
			return false;
		}

		String query = "UPDATE tasks SET contact_id = ? , task_type = ? , task_summary = ? , task_description = ? , task_created_by = ? , task_created_date_and_time = ? , task_assigned_to = ? , due_date_and_time = ? , priority = ? , progress = ? , task_current_status = ? WHERE task_id = " + task_id;

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);

			st.setInt(1, contact_id);
			st.setString(2, task_type);
			st.setString(3, task_summary);
			st.setString(4, task_description);
			st.setString(5, task_created_by);
			st.setTimestamp(6, task_created_date_and_time);
			st.setString(7, task_assigned_to);
			st.setTimestamp(8, due_date_and_time);
			st.setString(9, priority);
			st.setInt(10, progress);
			st.setString(11, task_current_status);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}


	public ArrayList<TasksHybridContacts> getAllTasksHybridContacts() {

		ArrayList<TasksHybridContacts> taskshybridcontacts = new ArrayList<>();
		String query = "SELECT * FROM tasks a JOIN contacts b USING (contact_id)";

				Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				TasksHybridContacts taskshybridcontact = new TasksHybridContacts();
				
				taskshybridcontact.setContact_id(rs.getInt("contact_id"));

				taskshybridcontact.setTask_id(rs.getInt("task_id"));

				if (rs.getString("task_type") != null){
					taskshybridcontact.setTask_type(rs.getString("task_type"));
				} else {
					taskshybridcontact.setTask_type("");
				}

				if (rs.getString("task_summary") != null){
					taskshybridcontact.setTask_summary(rs.getString("task_summary"));
				} else {
					taskshybridcontact.setTask_summary("");
				}

				if (rs.getString("task_description") != null){
					taskshybridcontact.setTask_description(rs.getString("task_description"));
				} else {
					taskshybridcontact.setTask_description("");
				}

				if (rs.getString("task_created_by") != null){
					taskshybridcontact.setTask_created_by(rs.getString("task_created_by"));
				} else {
					taskshybridcontact.setTask_created_by("");
				}

	
				taskshybridcontact.setTask_created_date_and_time(rs.getTimestamp("task_created_date_and_time"));
				

				if (rs.getString("task_assigned_to") != null){
					taskshybridcontact.setTask_assigned_to(rs.getString("task_assigned_to"));
				} else {
					taskshybridcontact.setTask_assigned_to("");
				}

				if (rs.getTimestamp("due_date_and_time") != null){
					taskshybridcontact.setDue_date_and_time(rs.getTimestamp("due_date_and_time"));
				} else {
					taskshybridcontact.setDue_date_and_time(null);
				}

				if (rs.getString("priority") != null){
					taskshybridcontact.setPriority(rs.getString("priority"));
				} else {
					taskshybridcontact.setPriority("");
				}

				taskshybridcontact.setProgress(rs.getInt("progress"));

				if (rs.getString("task_current_status") != null){
					taskshybridcontact.setTask_current_status(rs.getString("task_current_status"));
				} else {
					taskshybridcontact.setTask_current_status("");
				}

				if (rs.getString("first_name") != null){
					taskshybridcontact.setFirst_name(rs.getString("first_name"));
				} else {
					taskshybridcontact.setFirst_name("");
				}

				if (rs.getString("last_name") != null){
					taskshybridcontact.setLast_name(rs.getString("last_name"));
				} else {
					taskshybridcontact.setLast_name("");
				}

				if (rs.getString("phone_or_mobile") != null){
					taskshybridcontact.setPhone_or_mobile(rs.getString("phone_or_mobile"));
				} else {
					taskshybridcontact.setPhone_or_mobile("");
				}

				if (rs.getString("email") != null){
					taskshybridcontact.setEmail(rs.getString("email"));
				} else {
					taskshybridcontact.setEmail("");
				}

				if (rs.getString("fax") != null){
					taskshybridcontact.setFax(rs.getString("fax"));
				} else {
					taskshybridcontact.setFax("");
				}

				if (rs.getString("address_line_1") != null){
					taskshybridcontact.setAddress_line_1(rs.getString("address_line_1"));
				} else {
					taskshybridcontact.setAddress_line_1("");
				}

				if (rs.getString("address_line_2") != null){
					taskshybridcontact.setAddress_line_2(rs.getString("address_line_2"));
				} else {
					taskshybridcontact.setAddress_line_2("");
				}

				if (rs.getString("city") != null){
					taskshybridcontact.setCity(rs.getString("city"));
				} else {
					taskshybridcontact.setCity("");
				}

				if (rs.getString("state_or_county") != null){
					taskshybridcontact.setState_or_county(rs.getString("state_or_county"));
				} else {
					taskshybridcontact.setState_or_county("");
				}

				if (rs.getString("country") != null){
					taskshybridcontact.setCountry(rs.getString("country"));
				} else {
					taskshybridcontact.setCountry("");
				}

				if (rs.getString("description") != null){
					taskshybridcontact.setDescription(rs.getString("description"));
				} else {
					taskshybridcontact.setDescription("");
				}

				if (rs.getString("industry") != null){
					taskshybridcontact.setIndustry(rs.getString("industry"));
				} else {
					taskshybridcontact.setIndustry("");
				}

				if (rs.getString("company") != null){
					taskshybridcontact.setCompany(rs.getString("company"));
				} else {
					taskshybridcontact.setCompany("");
				}

				if (rs.getString("job_title") != null){
					taskshybridcontact.setJob_title(rs.getString("job_title"));
				} else {
					taskshybridcontact.setJob_title("");
				}

				if (rs.getString("created_by") != null){
					taskshybridcontact.setCreated_by(rs.getString("created_by"));
				} else {
					taskshybridcontact.setCreated_by("");
				}

				if (rs.getTimestamp("created_date_and_time") != null){
					taskshybridcontact.setCreated_date_and_time(rs.getTimestamp("created_date_and_time"));
				} else {
					taskshybridcontact.setCreated_date_and_time(null);
				}

				if (rs.getString("contact_source") != null){
					taskshybridcontact.setContact_source(rs.getString("contact_source"));
				} else {
					taskshybridcontact.setContact_source("");
				}
				taskshybridcontacts.add(taskshybridcontact);
			}

			return taskshybridcontacts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}



}
