package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TempDataDAO {

	String URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	String uname = "root";
	String pass = "masiqi93";


	public int getCurrentUserID() {

		String query = "SELECT * FROM temp_data";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();
			return rs.getInt("user_id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;

		}
	}

	public int getCurrentContactID() {

		String query = "SELECT * FROM temp_data";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();
			return rs.getInt("contact_id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;

		}
	}
	
	



	/**
	 * saves current data into temp_data database.
	 * @param current_user_id
	 */
	public void setCurrentUserID(int current_user_id) {

		int current_contact_id;

		if (this.checkEmpty()) {
			current_contact_id = -1;
		} else {
			current_contact_id = this.getCurrentContactID();
		}

		this.resetData();

		String query = "INSERT INTO temp_data VALUES (?,?)";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, current_user_id);
			st.setInt(2, current_contact_id);

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setCurrentContactID(int current_contact_id) {

		int current_user_id;

		if (this.checkEmpty()) {
			current_user_id = -1;
		} else {
			current_user_id = this.getCurrentUserID();
		}

		this.resetData();

		String query = "INSERT INTO temp_data VALUES (?,?)";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, current_user_id);
			st.setInt(2, current_contact_id);

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void resetData() {

		String query = "TRUNCATE TABLE temp_data";

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

	public boolean checkEmpty() {

		String query = "SELECT count(user_id) AS count FROM temp_data";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();
			int count = rs.getInt("count");
			

			if (count != 0) {
				return false;
			} else {
				return true;
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;

	}

}
