package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginDAO {

	String URL = DataBaseConnectionCredentials.URL;
	String uname = DataBaseConnectionCredentials.username;
	String pass = DataBaseConnectionCredentials.password;


	public boolean checkUserExist(String email) {

		String query = "SELECT * FROM login";

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


	public Login getLogin(String email) throws SQLException {

		Login login = new Login();
		login.setEmail(email);

		String query = "SELECT * FROM login WHERE email = " + "'"+ email + "'";

		Connection con = DriverManager.getConnection(URL, uname, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		rs.next();

		login.setUser_id(rs.getInt("user_id"));
		login.setPassword(rs.getString("password")); 
		login.setFull_name(rs.getString("full_name"));
		login.setAuth_level(rs.getInt("auth_level"));

		st.close();
		con.close();

		return login;
	}

	public void register(String email, String password, String full_name, int auth_level) throws SQLException {

		// Testing duplicate entry

		if (checkUserExist(email)) {
			return;
		}

		String query = "INSERT INTO login VALUES (DEFAULT,?,?,?,?)";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, password);
			st.setString(3, full_name);
			st.setInt(4, auth_level);

			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public ArrayList<String> getAllUserNamesFromLogin() {

		ArrayList<String> allLoginUserName = new ArrayList<>();

		String query = "SELECT full_name FROM login";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				allLoginUserName.add(rs.getString("full_name"));
			}

			return allLoginUserName;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}

	public Login getLoginFromID(int user_id) {

		Login login = new Login();
		login.setUser_id(user_id);

		String query = "SELECT * FROM login WHERE user_id = " + "'"+ user_id + "'";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();

			login.setEmail(rs.getString("email"));
			login.setPassword(rs.getString("password")); 
			login.setFull_name(rs.getString("full_name"));
			login.setAuth_level(rs.getInt("auth_level"));

			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


		return login;

	}

	public Login getLoginFromName(String name) {

		Login login = new Login();
		login.setFull_name(name);

		String query = "SELECT * FROM login WHERE full_name = "+"'"+ name + "'";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();

			login.setEmail(rs.getString("email"));
			login.setPassword(rs.getString("password")); 
			login.setFull_name(rs.getString("full_name"));
			login.setUser_id(rs.getInt("user_id"));
			login.setAuth_level(rs.getInt("auth_level"));

			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


		return login;

	}




}
