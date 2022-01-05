package application;

import java.sql.Timestamp;

public class Leads {
	
	// fields
	
	private int contact_id;
	private String lead_source;
	private String lead_status;
	private String if_lost_reasons;
	private String lead_created_by;
	private Timestamp lead_created_date_and_time;
	private String assigned_to;
	
	//setted and getters
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public String getLead_source() {
		return lead_source;
	}
	public void setLead_source(String lead_source) {
		this.lead_source = lead_source;
	}
	public String getLead_status() {
		return lead_status;
	}
	public void setLead_status(String lead_status) {
		this.lead_status = lead_status;
	}
	public String getIf_lost_reasons() {
		return if_lost_reasons;
	}
	public void setIf_lost_reasons(String if_lost_reasons) {
		this.if_lost_reasons = if_lost_reasons;
	}
	public String getCreated_by() {
		return lead_created_by;
	}
	public void setCreated_by(String created_by) {
		this.lead_created_by = created_by;
	}
	public Timestamp getCreated_date_and_time() {
		return lead_created_date_and_time;
	}
	public void setCreated_date_and_time(Timestamp created_date_and_time) {
		this.lead_created_date_and_time = created_date_and_time;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

}
