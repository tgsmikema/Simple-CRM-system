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

	public boolean isContactIDExistInSQL(String[] row) {
		String query = "SELECT * FROM contacts";

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

	public boolean isContactInfoExistInSQL(String[] row) {

		String query = "SELECT * FROM contacts";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				if((rs.getString("email") != null) && (rs.getString("email").equals(row[4]))) {
					return true;
				} else if(rs.getTimestamp("created_date_and_time").equals(Timestamp.valueOf(row[16]))) {
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


	public int getLargestContactIDFromSQL() {

		ArrayList<Integer> contacts_id = new ArrayList<Integer>();
		String query = "SELECT contact_id FROM contacts";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				contacts_id.add(rs.getInt("contact_id"));
			}

			//get the largest element
			if (contacts_id.isEmpty()) {
				return Integer.MIN_VALUE;
			}
			int largest = contacts_id.get(0);

			for(int i:contacts_id) {
				if (i > largest) {
					largest = i;
				}
			}

			return largest;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.MIN_VALUE;


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

	public boolean checkUserExist(String email, Timestamp created_date_and_time) {

		String query = "SELECT * FROM contacts";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				if((rs.getString("email") != null) && (rs.getString("email").equals(email))) {
					return true;
				} else if(rs.getTimestamp("created_date_and_time").equals(created_date_and_time)) {
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

	public boolean importContactWithID(String[] row) {

		int contact_id = Integer.valueOf(row[0]);
		String first_name = row[1];
		String last_name  = row[2];
		String phone_or_mobile  = row[3];
		String email  = row[4];
		String fax  = row[5];
		String address_line_1  = row[6];
		String address_line_2  = row[7];
		String city  = row[8];
		String state_or_county = row[9];
		String country = row[10];
		String description = row[11];
		String industry = row[12];
		String company = row[13];
		String job_title = row[14];
		String created_by = row[15];
		Timestamp created_date_and_time = Timestamp.valueOf(row[16]);
		String contact_source = row[17];


		// checking if mandatory fields are empty.
		if (first_name == null || last_name == null || created_by == null ||
				created_date_and_time == null) {
			return false;
		}

		String query = "INSERT INTO contacts VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection con;
		// check here change here
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, contact_id);
			st.setString(2, first_name);
			st.setString(3, last_name);
			st.setString(4, phone_or_mobile);
			st.setString(5, email);
			st.setString(6, fax);
			st.setString(7, address_line_1);
			st.setString(8, address_line_2);
			st.setString(9, city);
			st.setString(10, state_or_county);
			st.setString(11, country);
			st.setString(12, description);
			st.setString(13, industry);
			st.setString(14, company);
			st.setString(15, job_title);
			st.setString(16, created_by);
			st.setTimestamp(17, created_date_and_time);
			st.setString(18, contact_source);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}


	}

	public boolean importContactWithDefaultID(String[] row) {

		int contact_id = Integer.valueOf(row[0]);
		String first_name = row[1];
		String last_name  = row[2];
		String phone_or_mobile  = row[3];
		String email  = row[4];
		String fax  = row[5];
		String address_line_1  = row[6];
		String address_line_2  = row[7];
		String city  = row[8];
		String state_or_county = row[9];
		String country = row[10];
		String description = row[11];
		String industry = row[12];
		String company = row[13];
		String job_title = row[14];
		String created_by = row[15];
		Timestamp created_date_and_time = Timestamp.valueOf(row[16]);
		String contact_source = row[17];


		// checking if mandatory fields are empty.
		if (first_name == null || last_name == null || created_by == null ||
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
	public boolean addNewContact(String first_name,String last_name,String phone_or_mobile,String email,
			String fax, String address_line_1, String address_line_2,String city,String state_or_county,
			String country,String description,String industry,String company,String job_title,
			String created_by,Timestamp created_date_and_time,String contact_source) {

		// checking if user already exist in database by checking against email.
		if (checkUserExist(email,created_date_and_time)) {
			return false;
		}

		// checking if mandatory fields are empty.
		if (first_name == null || last_name == null || created_by == null ||
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

	public boolean checkIfContactIDExistInDataBase(int contact_id) {

		String query = "SELECT contact_id FROM contacts";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				if (rs.getInt("contact_id") == contact_id) {
					return true;
				}
			}

			return false;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public int getRowNumberFromID(int contact_id) {

		String query = "select row_num from (SELECT ROW_NUMBER() OVER (ORDER BY contact_id) row_num,contact_id FROM contacts) as aaa where contact_id = " + contact_id;

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();

			return ((rs.getInt(1))-1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
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
		if (first_name == null || last_name == null || created_by == null ||
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
