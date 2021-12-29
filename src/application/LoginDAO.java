package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

	String URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	String uname = "root";
	String pass = "masiqi93";
	
	
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
	
	


}
