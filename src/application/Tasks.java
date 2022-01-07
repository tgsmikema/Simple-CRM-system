package application;

import java.sql.Timestamp;

public class Tasks {
	
	//fields

	int contact_id;
	String task_type;
	String task_summary;
	String task_description;
	String task_created_by;
	Timestamp task_created_date_and_time;
	String task_assigned_to;
	Timestamp due_date_and_time;
	String priority;
	int progress;
	String task_current_status;
	
	//setters and getters
	
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getTask_summary() {
		return task_summary;
	}
	public void setTask_summary(String task_summary) {
		this.task_summary = task_summary;
	}
	public String getTask_description() {
		return task_description;
	}
	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}
	public String getTask_created_by() {
		return task_created_by;
	}
	public void setTask_created_by(String task_created_by) {
		this.task_created_by = task_created_by;
	}
	public Timestamp getTask_created_date_and_time() {
		return task_created_date_and_time;
	}
	public void setTask_created_date_and_time(Timestamp task_created_date_and_time) {
		this.task_created_date_and_time = task_created_date_and_time;
	}
	public String getTask_assigned_to() {
		return task_assigned_to;
	}
	public void setTask_assigned_to(String task_assigned_to) {
		this.task_assigned_to = task_assigned_to;
	}
	public Timestamp getDue_date_and_time() {
		return due_date_and_time;
	}
	public void setDue_date_and_time(Timestamp due_date_and_time) {
		this.due_date_and_time = due_date_and_time;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getTask_current_status() {
		return task_current_status;
	}
	public void setTask_current_status(String task_current_status) {
		this.task_current_status = task_current_status;
	}
	
	
}
