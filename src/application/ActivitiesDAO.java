package application;

import java.sql.*;
import java.util.ArrayList;

public class ActivitiesDAO {

	String URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	String uname = "root";
	String pass = "masiqi93";

	public ArrayList<Activities> getAllActivities() {

		ArrayList<Activities> activities = new ArrayList<>();
		String query = "SELECT * FROM activities";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Activities activitie = new Activities();
				activitie.setActivity_id(rs.getInt("activity_id"));
				activitie.setContact_id(rs.getInt("contact_id"));
				activitie.setActivity_type(rs.getString("activity_type"));
				activitie.setActivity_summary(rs.getString("activity_summary"));
				activitie.setActivity_description(rs.getString("activity_description"));
				activitie.setActivity_created_by(rs.getString("activity_created_by"));
				activitie.setActivity_created_date_and_time(rs.getTimestamp("activity_created_date_and_time"));
				activities.add(activitie);
			}

			return activities;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean isActivityInfoExistInSQL(String[] row) {
		String query = "SELECT * FROM activities";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			// loop result set that if email exist in database
			while(rs.next()) {
				if(rs.getTimestamp("activity_created_date_and_time").equals(Timestamp.valueOf(row[6]))) {
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

	public Activities getActivityFromActivityID(int activity_id) {

		String query = "SELECT * FROM activities WHERE activity_id = " + activity_id;
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			rs.next();

			Activities activity = new Activities();
			activity.setActivity_id(rs.getInt("activity_id"));

			activity.setContact_id(rs.getInt("contact_id"));

			if (rs.getString("activity_type") != null){
				activity.setActivity_type(rs.getString("activity_type"));
			} else {
				activity.setActivity_type("");
			}

			if (rs.getString("activity_summary") != null){
				activity.setActivity_summary(rs.getString("activity_summary"));
			} else {
				activity.setActivity_summary("");
			}

			if (rs.getString("activity_description") != null){
				activity.setActivity_description(rs.getString("activity_description"));
			} else {
				activity.setActivity_description("");
			}

			if (rs.getString("activity_created_by") != null){
				activity.setActivity_created_by(rs.getString("activity_created_by"));
			} else {
				activity.setActivity_created_by("");
			}

			if (rs.getTimestamp("activity_created_date_and_time") != null){
				activity.setActivity_created_date_and_time(rs.getTimestamp("activity_created_date_and_time"));
			} else {
				activity.setActivity_created_date_and_time(null);
			}



			return activity;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}


	public ArrayList<Activities> getActivitiesFromContactID(int contact_id) {

		ArrayList<Activities> activities = new ArrayList<>();

		String query = "SELECT * FROM activities WHERE contact_id = " + contact_id;
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {

				Activities activity = new Activities();
				activity.setActivity_id(rs.getInt("activity_id"));

				activity.setContact_id(rs.getInt("contact_id"));

				if (rs.getString("activity_type") != null){
					activity.setActivity_type(rs.getString("activity_type"));
				} else {
					activity.setActivity_type("");
				}

				if (rs.getString("activity_summary") != null){
					activity.setActivity_summary(rs.getString("activity_summary"));
				} else {
					activity.setActivity_summary("");
				}

				if (rs.getString("activity_description") != null){
					activity.setActivity_description(rs.getString("activity_description"));
				} else {
					activity.setActivity_description("");
				}

				if (rs.getString("activity_created_by") != null){
					activity.setActivity_created_by(rs.getString("activity_created_by"));
				} else {
					activity.setActivity_created_by("");
				}

				if (rs.getTimestamp("activity_created_date_and_time") != null){
					activity.setActivity_created_date_and_time(rs.getTimestamp("activity_created_date_and_time"));
				} else {
					activity.setActivity_created_date_and_time(null);
				}

				activities.add(activity);

			}
			return activities;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean checkActivityExist(int activity_id) {

		String query = "SELECT * FROM activities";
		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				if(rs.getInt("activity_id") == (activity_id)) {
					return true;
				}
			}

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void deleteActivitiesFromContactID(int contact_id) {

		String query = "DELETE FROM activities WHERE contact_id = " + contact_id;
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

	public void deleteActivityFromActivityID(int activity_id) {

		String query = "DELETE FROM activities WHERE activity_id = " + activity_id;
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

	public boolean addNewActivity(int activity_id,int contact_id,String activity_type,String activity_summary,String activity_description,String activity_created_by,Timestamp activity_created_date_and_time) {

		if (checkActivityExist(activity_id)) {
			return false;
		}

		if (activity_type == null || activity_created_by == null || activity_created_date_and_time == null ) {

			return false;
		}

		String query = "INSERT INTO activities VALUES (DEFAULT,?,?,?,?,?,?)";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, contact_id);
			st.setString(2, activity_type);
			st.setString(3, activity_summary);
			st.setString(4, activity_description);
			st.setString(5, activity_created_by);
			st.setTimestamp(6, activity_created_date_and_time);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean importActivity(String[] row) {
		
		int activity_id = Integer.valueOf(row[0]);
		int contact_id = Integer.valueOf(row[1]);
		String activity_type = row[2];
		String activity_summary = row[3];
		String activity_description = row[4];
		String activity_created_by = row[5];
		Timestamp activity_created_date_and_time = Timestamp.valueOf(row[6]);

		
		if (activity_type == null || activity_created_by == null || activity_created_date_and_time == null ) {

			return false;
		}

		String query = "INSERT INTO activities VALUES (DEFAULT,?,?,?,?,?,?)";

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, contact_id);
			st.setString(2, activity_type);
			st.setString(3, activity_summary);
			st.setString(4, activity_description);
			st.setString(5, activity_created_by);
			st.setTimestamp(6, activity_created_date_and_time);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean modifyActivityFromID(int activity_id,int contact_id,String activity_type,String activity_summary,String activity_description,String activity_created_by,Timestamp activity_created_date_and_time) {

		if (activity_type == null || activity_created_by == null || activity_created_date_and_time == null ) {
			return false;
		}

		String query = "UPDATE activities SET contact_id = ? , activity_type = ? , activity_summary = ? , activity_description = ? , activity_created_by = ? , activity_created_date_and_time = ? WHERE activity_id = " + activity_id;

		Connection con;

		try {
			con = DriverManager.getConnection(URL, uname, pass);
			PreparedStatement st = con.prepareStatement(query);

			st.setInt(1, contact_id);
			st.setString(2, activity_type);
			st.setString(3, activity_summary);
			st.setString(4, activity_description);
			st.setString(5, activity_created_by);
			st.setTimestamp(6, activity_created_date_and_time);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<ActivitiesHybridContacts> getAllActivitiesHybridContacts() {

		ArrayList<ActivitiesHybridContacts> activitieshybridcontacts = new ArrayList<>();
		String query = "SELECT * FROM activities a JOIN contacts b USING (contact_id)";

		Connection con;
		try {
			con = DriverManager.getConnection(URL, uname, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				ActivitiesHybridContacts activitieshybridcontact = new ActivitiesHybridContacts();
				activitieshybridcontact.setActivity_id(rs.getInt("activity_id"));
				activitieshybridcontact.setContact_id(rs.getInt("contact_id"));
				activitieshybridcontact.setActivity_type(rs.getString("activity_type"));
				activitieshybridcontact.setActivity_summary(rs.getString("activity_summary"));
				activitieshybridcontact.setActivity_description(rs.getString("activity_description"));
				activitieshybridcontact.setActivity_created_by(rs.getString("activity_created_by"));
				activitieshybridcontact.setActivity_created_date_and_time(rs.getTimestamp("activity_created_date_and_time"));
				
				activitieshybridcontact.setFirst_name(rs.getString("first_name"));
				activitieshybridcontact.setLast_name(rs.getString("last_name"));
				activitieshybridcontact.setPhone_or_mobile(rs.getString("phone_or_mobile"));
				activitieshybridcontact.setEmail(rs.getString("email"));
				activitieshybridcontact.setFax(rs.getString("fax"));
				activitieshybridcontact.setAddress_line_1(rs.getString("address_line_1"));
				activitieshybridcontact.setAddress_line_2(rs.getString("address_line_2"));
				activitieshybridcontact.setCity(rs.getString("city"));
				activitieshybridcontact.setState_or_county(rs.getString("state_or_county"));
				activitieshybridcontact.setCountry(rs.getString("country"));
				activitieshybridcontact.setDescription(rs.getString("description"));
				activitieshybridcontact.setIndustry(rs.getString("industry"));
				activitieshybridcontact.setCompany(rs.getString("company"));
				activitieshybridcontact.setJob_title(rs.getString("job_title"));
				activitieshybridcontact.setCreated_by(rs.getString("created_by"));
				activitieshybridcontact.setCreated_date_and_time(rs.getTimestamp("created_date_and_time"));
				activitieshybridcontact.setContact_source(rs.getString("contact_source"));
				activitieshybridcontacts.add(activitieshybridcontact);
			}

			return activitieshybridcontacts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}


























}
