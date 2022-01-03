package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContactsDAO {
	
	String URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	String uname = "root";
	String pass = "masiqi93";
	
	public ArrayList<Contacts> getAllContacts(){
		
		ArrayList<Contacts> contacts = new ArrayList<>();
		String query = "SELECT * FROM contacts";
		
		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Contacts contact = new Contacts();
				contact.setContact_id(rs.getInt("contact_id"));
				contact.setFirst_name(rs.getString("first_name"));
				contact.setLast_name(rs.getString("last_name"));
				contact.setPhone_or_mobile(rs.getString("phone_or_mobile"));
				contact.setEmail(rs.getString("email"));
				contact.setFax(rs.getString("fax"));
				contact.setAddress_line_1(rs.getString("address_line_1"));
				contact.setAddress_line_2(rs.getString("address_line_2"));
				contact.setCity(rs.getString("city"));
				contact.setState_or_county(rs.getString("state_or_county"));
				contact.setCountry(rs.getString("country"));
				contact.setDescription(rs.getString("description"));
				contact.setIndustry(rs.getString("industry"));
				contact.setCompany(rs.getString("company"));
				contact.setJob_title(rs.getString("job_title"));
				contact.setCreated_by(rs.getString("created_by"));
				contact.setCreated_date_and_time(rs.getTimestamp("created_date_and_time"));
				contact.setContact_source(rs.getString("contact_source"));
				contacts.add(contact);
			}
			
			return contacts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void deleteContactFromID(int contact_id) {
		
		String query = "DELETE FROM contacts WHERE contact_id = " + contact_id;

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

}
