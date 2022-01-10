package application;

import java.sql.Timestamp;

public class Activities {
	
	//fields
	int activity_id;
	int contact_id;
	String activity_type;
	String activity_summary;
	String activity_description;
	String activity_created_by;
	Timestamp activity_created_date_and_time;
	
	//setter and getters
	
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}

	public String getActivity_type() {
		return activity_type;
	}

	public void setActivity_summary(String activity_summary) {
		this.activity_summary = activity_summary;
	}

	public String getActivity_summary() {
		return activity_summary;
	}

	public void setActivity_description(String activity_description) {
		this.activity_description = activity_description;
	}

	public String getActivity_description() {
		return activity_description;
	}

	public void setActivity_created_by(String activity_created_by) {
		this.activity_created_by = activity_created_by;
	}

	public String getActivity_created_by() {
		return activity_created_by;
	}

	public void setActivity_created_date_and_time(Timestamp activity_created_date_and_time) {
		this.activity_created_date_and_time = activity_created_date_and_time;
	}

	public Timestamp getActivity_created_date_and_time() {
		return activity_created_date_and_time;
	}


	

}
