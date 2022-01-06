package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

	public Contacts getContactFromID(int contact_id) {

		String query = "SELECT * FROM contacts WHERE contact_id = " + contact_id;

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();

			Contacts contact = new Contacts();
			

			contact.setContact_id(rs.getInt("contact_id"));
			
			if (rs.getString("first_name") != null){
				contact.setFirst_name(rs.getString("first_name"));
			} else {
				contact.setFirst_name("");
			}
			
			if (rs.getString("last_name") != null){
				contact.setLast_name(rs.getString("last_name"));
			} else {
				contact.setLast_name("");
			}
			
			if (rs.getString("phone_or_mobile") != null){
				contact.setPhone_or_mobile(rs.getString("phone_or_mobile"));
			} else {
				contact.setPhone_or_mobile("");
			}
			
			if (rs.getString("email") != null){
				contact.setEmail(rs.getString("email"));
			} else {
				contact.setEmail("");
			}
			
			if (rs.getString("fax") != null){
				contact.setFax(rs.getString("fax"));
			} else {
				contact.setFax("");
			}
			
			if (rs.getString("address_line_1") != null){
				contact.setAddress_line_1(rs.getString("address_line_1"));
			} else {
				contact.setAddress_line_1("");
			}
			
			if (rs.getString("address_line_2") != null){
				contact.setAddress_line_2(rs.getString("address_line_2"));
			} else {
				contact.setAddress_line_2("");
			}
			
			if (rs.getString("city") != null){
				contact.setCity(rs.getString("city"));
			} else {
				contact.setCity("");
			}
			
			if (rs.getString("state_or_county") != null){
				contact.setState_or_county(rs.getString("state_or_county"));
			} else {
				contact.setState_or_county("");
			}
			
			if (rs.getString("country") != null){
				contact.setCountry(rs.getString("country"));
			} else {
				contact.setCountry("");
			}
			
			if (rs.getString("description") != null){
				contact.setDescription(rs.getString("description"));
			} else {
				contact.setDescription("");
			}
			
			if (rs.getString("industry") != null){
				contact.setIndustry(rs.getString("industry"));
			} else {
				contact.setIndustry("");
			}
			
			if (rs.getString("company") != null){
				contact.setCompany(rs.getString("company"));
			} else {
				contact.setCompany("");
			}
			
			if (rs.getString("job_title") != null){
				contact.setJob_title(rs.getString("job_title"));
			} else {
				contact.setJob_title("");
			}
			
			if (rs.getString("created_by") != null){
				contact.setCreated_by(rs.getString("created_by"));
			} else {
				contact.setCreated_by("");
			}
		
			
			contact.setCreated_date_and_time(rs.getTimestamp("created_date_and_time"));
			
			
			if (rs.getString("contact_source") != null){
				contact.setContact_source(rs.getString("contact_source"));
			} else {
				contact.setContact_source("");
			}
			


			return contact;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public boolean checkUserExist(String email) {

		String query = "SELECT * FROM contacts";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				if(rs.getString("email").equals(email)) {
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

	/**
	 * this method returns a boolean, ture if insertion was successful, else false.
	 * 
	 */
	public boolean addNewContact(String first_name,String last_name,String phone_or_mobile,String email,
			String fax, String address_line_1, String address_line_2,String city,String state_or_county,
			String country,String description,String industry,String company,String job_title,
			String created_by,Timestamp created_date_and_time,String contact_source) {

		// checking if user already exist in database by checking against email.
		if (checkUserExist(email)) {
			return false;
		}

		// checking if mandatory fields are empty.
		if (first_name == null || last_name == null || email == null || created_by == null ||
				created_date_and_time == null) {
			return false;
		}

		String query = "INSERT INTO contacts VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection con;
		// check here change here
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, first_name);
			st.setString(2, last_name);
			st.setString(3, phone_or_mobile);
			st.setString(4, email);
			st.setString(5, fax);
			st.setString(6, address_line_1);
			st.setString(7, address_line_2);
			st.setString(8, city);
			st.setString(9, state_or_county);
			st.setString(10, country);
			st.setString(11, description);
			st.setString(12, industry);
			st.setString(13, company);
			st.setString(14, job_title);
			st.setString(15, created_by);
			st.setTimestamp(16, created_date_and_time);
			st.setString(17, contact_source);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}


	}

	/**
	 * this method returns a boolean, ture if insertion was successful, else false.
	 * 
	 */
	public boolean modifyContactFromID(int contact_id, String first_name,String last_name,String phone_or_mobile,String email,
			String fax, String address_line_1, String address_line_2,String city,String state_or_county,
			String country,String description,String industry,String company,String job_title,
			String created_by,Timestamp created_date_and_time,String contact_source) {

		// checking if mandatory fields are empty.
		if (first_name == null || last_name == null || email == null || created_by == null ||
				created_date_and_time == null) {
			return false;
		}
		
		String query = "UPDATE contacts SET first_name = ? , last_name = ? , phone_or_mobile = ? , email = ? , fax = ? , address_line_1 = ? , address_line_2 = ? , city = ? , state_or_county = ? , country = ? , description = ? , industry = ? , company = ? , job_title = ? , created_by = ? , created_date_and_time = ? , contact_source = ? WHERE contact_id = " + contact_id;
		
		Connection con;
		// check here change here
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, first_name);
			st.setString(2, last_name);
			st.setString(3, phone_or_mobile);
			st.setString(4, email);
			st.setString(5, fax);
			st.setString(6, address_line_1);
			st.setString(7, address_line_2);
			st.setString(8, city);
			st.setString(9, state_or_county);
			st.setString(10, country);
			st.setString(11, description);
			st.setString(12, industry);
			st.setString(13, company);
			st.setString(14, job_title);
			st.setString(15, created_by);
			st.setTimestamp(16, created_date_and_time);
			st.setString(17, contact_source);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
