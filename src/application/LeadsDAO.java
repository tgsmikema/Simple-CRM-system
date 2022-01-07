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

	String URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	String uname = "root";
	String pass = "masiqi93";

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
				leadsHybridContact.setLead_source(rs.getString("lead_source"));
				leadsHybridContact.setLead_status(rs.getString("lead_status"));
				leadsHybridContact.setIf_lost_reasons(rs.getString("if_lost_reasons"));
				leadsHybridContact.setLead_created_by(rs.getString("lead_created_by"));
				leadsHybridContact.setLead_created_date_and_time(rs.getTimestamp("lead_created_date_and_time"));
				leadsHybridContact.setAssigned_to(rs.getString("assigned_to"));
				// contacts section
				leadsHybridContact.setFirst_name(rs.getString("first_name"));
				leadsHybridContact.setLast_name(rs.getString("last_name"));
				leadsHybridContact.setPhone_or_mobile(rs.getString("phone_or_mobile"));
				leadsHybridContact.setEmail(rs.getString("email"));
				leadsHybridContact.setFax(rs.getString("fax"));
				leadsHybridContact.setAddress_line_1(rs.getString("address_line_1"));
				leadsHybridContact.setAddress_line_2(rs.getString("address_line_2"));
				leadsHybridContact.setCity(rs.getString("city"));
				leadsHybridContact.setState_or_county(rs.getString("state_or_county"));
				leadsHybridContact.setCountry(rs.getString("country"));
				leadsHybridContact.setDescription(rs.getString("description"));
				leadsHybridContact.setIndustry(rs.getString("industry"));
				leadsHybridContact.setCompany(rs.getString("company"));
				leadsHybridContact.setJob_title(rs.getString("job_title"));
				leadsHybridContact.setCreated_by(rs.getString("created_by"));
				leadsHybridContact.setCreated_date_and_time(rs.getTimestamp("created_date_and_time"));
				leadsHybridContact.setContact_source(rs.getString("contact_source"));
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
