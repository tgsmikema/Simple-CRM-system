package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				lead.setCreated_by(rs.getString("created_by"));
				lead.setCreated_date_and_time(rs.getTimestamp("created_date_and_time"));
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

}
