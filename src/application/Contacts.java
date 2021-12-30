package application;

import java.sql.Timestamp;
import java.util.Date;

public class Contacts {

	//fields
	private int contact_id;
	private String first_name;
	private String last_name;
	private String phone_or_mobile;
	private String email;
	private String fax;
	private String address_line_1;
	private String address_line_2;
	private String city;
	private String state_or_county;
	private String country;
	private String description;
	private String industry;
	private String company;
	private String job_title;
	private String created_by;
	private Timestamp created_date_and_time;
	private String contact_source;
	
	public Contacts() {
		
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_or_mobile() {
		return phone_or_mobile;
	}

	public void setPhone_or_mobile(String phone_or_mobile) {
		this.phone_or_mobile = phone_or_mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress_line_1() {
		return address_line_1;
	}

	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState_or_county() {
		return state_or_county;
	}

	public void setState_or_county(String state_or_county) {
		this.state_or_county = state_or_county;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Timestamp getCreated_date_and_time() {
		return created_date_and_time;
	}

	public void setCreated_date_and_time(Timestamp date) {
		this.created_date_and_time = date;
	}

	public String getContact_source() {
		return contact_source;
	}
	
	public void setContact_source(String contact_source) {
		this.contact_source = contact_source;
	}
	
	
	
}
