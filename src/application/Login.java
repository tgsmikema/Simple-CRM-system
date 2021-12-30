package application;

public class Login {
	
	//fields
	private int user_id;
	private String email;
	private String password;
	private String full_name;
	private int auth_level;
	
	
	public Login() {
		// TODO Auto-generated constructor stub
	}
	// getter and setter methods
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public int getAuth_level() {
		return auth_level;
	}
	public void setAuth_level(int auth_level) {
		this.auth_level = auth_level;
	}
	
	

}
