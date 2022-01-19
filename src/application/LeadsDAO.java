package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class LeadsDAO {

	String URL = DataBaseConnectionCredentials.URL;
	String uname = DataBaseConnectionCredentials.username;
	String pass = DataBaseConnectionCredentials.password;
	
	public void deleteAllLeads() {

		String query = "TRUNCATE TABLE leads";

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

	public ArrayList<Leads> getAllLeads(){
		ArrayList<Leads> leads = new ArrayList<>();
		String query = "SELECT * FROM leads";

		Connection con;
		try {
			con = DriverManager.getConnection(URL,uname,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Leads lead = new Leads();
				lead.setContact_id(rs.getInt("contact_id"));
				lead.setLead_source(rs.getString("lead_source"));
				lead.setLead_status(rs.getString("lead_status"));
				lead.setIf_lost_reasons(rs.getString("if_lost_reasons"));
				lead.setCreated_by(rs.getString("lead_created_by"));
				lead.setCreated_date_and_time(rs.getTimestamp("lead_created_date_and_time"));
				lead.setAssigned_to(rs.getString("assigned_to"));
				leads.add(lead);
			}
			return leads;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

	public boolean modifyLeadFromID(int contact_id, String lead_source, String lead_status,
			String if_lost_reasons, String lead_created_by, Timestamp lead_created_date_and_time,
			String assigned_to) {

		// checking if mandatory fields are empty.
		if ( lead_status == null || lead_created_by == null || lead_created_date_and_time == null) {
			return false;
		}

		String query = "UPDATE leads SET lead_source = ? , lead_status = ? , if_lost_reasons = ? , lead_created_by = ? , lead_created_date_and_time = ? , assigned_to = ? WHERE contact_id = " + contact_id;

		Connection con;
		// check here change here
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, lead_source);
			st.setString(2, lead_status);
			st.setString(3, if_lost_reasons);
			st.setString(4, lead_created_by);
			st.setTimestamp(5, lead_created_date_and_time);
			st.setString(6, assigned_to);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}


	}
	
	public boolean isLeadExistInSQL(String[] row) {
		String query = "SELECT * FROM leads";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				if(rs.getInt("contact_id") == Integer.valueOf(row[0])) {
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

	public boolean checkNewLeadAlreadyExist(int contact_id) {

		String query = "SELECT * FROM leads";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				if(rs.getInt("contact_id") == contact_id) {
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



	public boolean addNewLead(int contact_id, String lead_source, String lead_status,
			String if_lost_reasons, String lead_created_by, Timestamp lead_created_date_and_time,
			String assigned_to) {

		// checking if user already exist in database by checking against contact_id.
		if (checkNewLeadAlreadyExist(contact_id)) {
			return false;
		}

		// checking if mandatory fields are empty.
		if ( lead_status == null || lead_created_by == null || lead_created_date_and_time == null) {
			return false;
		}

		String query = "INSERT INTO leads VALUES (?,?,?,?,?,?,?)";

		Connection con;
		// check here change here
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, contact_id);
			st.setString(2, lead_source);
			st.setString(3, lead_status);
			st.setString(4, if_lost_reasons);
			st.setString(5, lead_created_by);
			st.setTimestamp(6, lead_created_date_and_time);
			st.setString(7, assigned_to);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean importLeadWithID(String[] row) {

		int contact_id = Integer.valueOf(row[0]);
		String lead_source = row[1];
		String lead_status = row[2];
		String if_lost_reasons = row[3];
		String lead_created_by = row[4];
		Timestamp lead_created_date_and_time = Timestamp.valueOf(row[5]);
		String assigned_to = row[6];

		// checking if mandatory fields are empty.
		if ( lead_status == null || lead_created_by == null || lead_created_date_and_time == null) {
			return false;
		}

		String query = "INSERT INTO leads VALUES (?,?,?,?,?,?,?)";

		Connection con;
		// check here change here
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, contact_id);
			st.setString(2, lead_source);
			st.setString(3, lead_status);
			st.setString(4, if_lost_reasons);
			st.setString(5, lead_created_by);
			st.setTimestamp(6, lead_created_date_and_time);
			st.setString(7, assigned_to);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	public ArrayList<Integer> getAllContactsIDNotLeadsYet(){

		ArrayList<Integer> allContactIDNotLeadsYet = new ArrayList<>();

		String query = "SELECT contact_id FROM contacts WHERE contact_id NOT IN (SELECT contact_id FROM contacts c JOIN leads l USING (contact_id))";

		Connection con;
		try {
			con = DriverManager.getConnection(URL,uname,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				allContactIDNotLeadsYet.add(rs.getInt("contact_id"));
			}

			return allContactIDNotLeadsYet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


	public Leads getLeadFromID(int contact_id) {

		String query = "SELECT * FROM leads WHERE contact_id = " + contact_id;

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();

			Leads lead = new Leads();


			lead.setContact_id(rs.getInt("contact_id"));

			if (rs.getString("lead_source") != null){
				lead.setLead_source(rs.getString("lead_source"));
			} else {
				lead.setLead_source("");
			}
			
			if (rs.getString("lead_status") != null){
				lead.setLead_status(rs.getString("lead_status"));
			} else {
				lead.setLead_status("");
			}
			
			if (rs.getString("if_lost_reasons") != null) {
				lead.setIf_lost_reasons(rs.getString("if_lost_reasons"));
			} else {
				lead.setIf_lost_reasons("");
			}
			
			if (rs.getString("lead_created_by") != null) {
				lead.setCreated_by(rs.getString("lead_created_by"));
			} else {
				lead.setCreated_by("");
			}
			
			
			lead.setCreated_date_and_time(rs.getTimestamp("lead_created_date_and_time"));

			if (rs.getString("assigned_to") != null) {
				lead.setAssigned_to(rs.getString("assigned_to"));
			} else {
				lead.setAssigned_to(rs.getString(""));
			}
			

			return lead;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


	public ArrayList<LeadsHybridContacts> getAllLeadsHybridContacts(){

		ArrayList<LeadsHybridContacts> leadsHybridContacts = new ArrayList<>();

		String query = "SELECT * FROM leads l JOIN contacts c USING (contact_id)";

		Connection con;
		try {
			con = DriverManager.getConnection(URL,uname,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				LeadsHybridContacts leadsHybridContact = new LeadsHybridContacts();
				// leads section
				leadsHybridContact.setContact_id(rs.getInt("contact_id"));
				
				if (rs.getString("lead_source") != null){
					leadsHybridContact.setLead_source(rs.getString("lead_source"));
				} else {
					leadsHybridContact.setLead_source("");
				}

				if (rs.getString("lead_status") != null){
					leadsHybridContact.setLead_status(rs.getString("lead_status"));
				} else {
					leadsHybridContact.setLead_status("");
				}

				if (rs.getString("if_lost_reasons") != null){
					leadsHybridContact.setIf_lost_reasons(rs.getString("if_lost_reasons"));
				} else {
					leadsHybridContact.setIf_lost_reasons("");
				}

				if (rs.getString("lead_created_by") != null){
					leadsHybridContact.setLead_created_by(rs.getString("lead_created_by"));
				} else {
					leadsHybridContact.setLead_created_by("");
				}

				if (rs.getTimestamp("lead_created_date_and_time") != null){
					leadsHybridContact.setLead_created_date_and_time(rs.getTimestamp("lead_created_date_and_time"));
				} else {
					leadsHybridContact.setLead_created_date_and_time(null);
				}

				if (rs.getString("assigned_to") != null){
					leadsHybridContact.setAssigned_to(rs.getString("assigned_to"));
				} else {
					leadsHybridContact.setAssigned_to("");
				}

				if (rs.getString("first_name") != null){
					leadsHybridContact.setFirst_name(rs.getString("first_name"));
				} else {
					leadsHybridContact.setFirst_name("");
				}

				if (rs.getString("last_name") != null){
					leadsHybridContact.setLast_name(rs.getString("last_name"));
				} else {
					leadsHybridContact.setLast_name("");
				}

				if (rs.getString("phone_or_mobile") != null){
					leadsHybridContact.setPhone_or_mobile(rs.getString("phone_or_mobile"));
				} else {
					leadsHybridContact.setPhone_or_mobile("");
				}

				if (rs.getString("email") != null){
					leadsHybridContact.setEmail(rs.getString("email"));
				} else {
					leadsHybridContact.setEmail("");
				}

				if (rs.getString("fax") != null){
					leadsHybridContact.setFax(rs.getString("fax"));
				} else {
					leadsHybridContact.setFax("");
				}

				if (rs.getString("address_line_1") != null){
					leadsHybridContact.setAddress_line_1(rs.getString("address_line_1"));
				} else {
					leadsHybridContact.setAddress_line_1("");
				}

				if (rs.getString("address_line_2") != null){
					leadsHybridContact.setAddress_line_2(rs.getString("address_line_2"));
				} else {
					leadsHybridContact.setAddress_line_2("");
				}

				if (rs.getString("city") != null){
					leadsHybridContact.setCity(rs.getString("city"));
				} else {
					leadsHybridContact.setCity("");
				}

				if (rs.getString("state_or_county") != null){
					leadsHybridContact.setState_or_county(rs.getString("state_or_county"));
				} else {
					leadsHybridContact.setState_or_county("");
				}

				if (rs.getString("country") != null){
					leadsHybridContact.setCountry(rs.getString("country"));
				} else {
					leadsHybridContact.setCountry("");
				}

				if (rs.getString("description") != null){
					leadsHybridContact.setDescription(rs.getString("description"));
				} else {
					leadsHybridContact.setDescription("");
				}

				if (rs.getString("industry") != null){
					leadsHybridContact.setIndustry(rs.getString("industry"));
				} else {
					leadsHybridContact.setIndustry("");
				}

				if (rs.getString("company") != null){
					leadsHybridContact.setCompany(rs.getString("company"));
				} else {
					leadsHybridContact.setCompany("");
				}

				if (rs.getString("job_title") != null){
					leadsHybridContact.setJob_title(rs.getString("job_title"));
				} else {
					leadsHybridContact.setJob_title("");
				}

				if (rs.getString("created_by") != null){
					leadsHybridContact.setCreated_by(rs.getString("created_by"));
				} else {
					leadsHybridContact.setCreated_by("");
				}

				if (rs.getTimestamp("created_date_and_time") != null){
					leadsHybridContact.setCreated_date_and_time(rs.getTimestamp("created_date_and_time"));
				} else {
					leadsHybridContact.setCreated_date_and_time(null);
				}

				if (rs.getString("contact_source") != null){
					leadsHybridContact.setContact_source(rs.getString("contact_source"));
				} else {
					leadsHybridContact.setContact_source("");
				}
				//add into arraylist
				leadsHybridContacts.add(leadsHybridContact);
			}

			return leadsHybridContacts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}
}
