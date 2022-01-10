package application;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ModifyTaskController implements Initializable {

	@FXML
	private Label address_line_1_t;

	@FXML
	private Label address_line_2_t;

	@FXML
	private Button cancel_b;

	@FXML
	private Label city_t;

	@FXML
	private Label company_t;

	@FXML
	private Label contact_id_t;

	@FXML
	private Label contact_source_t;

	@FXML
	private Label country_t;

	@FXML
	private Label created_by_t;

	@FXML
	private Label created_date_and_time_t;

	@FXML
	private DatePicker date_picker;

	@FXML
	private Label description_t;

	@FXML
	private TextField due_date_and_time_t;

	@FXML
	private Label email_t;

	@FXML
	private Label fax_t;

	@FXML
	private Label first_name_t;

	@FXML
	private Button get_local_time_b;

	@FXML
	private Label industry_t;

	@FXML
	private Label job_title_t;

	@FXML
	private Label last_name_t;

	@FXML
	private Label lead_created_by_t;

	@FXML
	private Button new_activity_b;

	@FXML
	private Label phone_or_mobile_t;

	@FXML
	private TextField priority_t;

	@FXML
	private Slider progress_t;

	@FXML
	private Button save_b;

	@FXML
	private Label state_or_county_t;

	@FXML
	private ChoiceBox<String> task_assigned_to_t;

	@FXML
	private Label task_created_by_t;

	@FXML
	private Label task_created_date_and_time_t;

	@FXML
	private TextField task_current_status_t;

	@FXML
	private TextField task_description_t;

	@FXML
	private Label task_id_t;

	@FXML
	private TextField task_summary_t;

	@FXML
	private TextField task_type_t;

	private SceneManager sceneManager = new SceneManager();

	private TempDataDAO tempDataDAO = new TempDataDAO();

	private LoginDAO loginDAO = new LoginDAO();

	private ContactsDAO contactsDAO = new ContactsDAO();

	private TasksDAO tasksDAO = new TasksDAO();

	@FXML
	void cancelAndReturn(ActionEvent event) {
		sceneManager.switchScene(event, "TasksHome");
	}

	private int contact_id;
	private int task_id;
	private String task_type;
	private String task_summary;
	private String task_description;
	private String task_created_by;
	private String task_created_date_and_time_string;Timestamp task_created_date_and_time;
	private String task_assigned_to;
	private String task_due_date_and_time_string;Timestamp due_date_and_time;
	private String priority;
	private int progress;
	private String task_current_status;

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
	private String date_and_time_string; Timestamp created_date_and_time;
	private String contact_source;

	private ArrayList<String> allUserNameFromLogin = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		task_id = tempDataDAO.getCurrentContactID();

		contact_id = tasksDAO.getTaskFromTaskID(task_id).getContact_id();
		task_type = tasksDAO.getTaskFromTaskID(task_id).getTask_type();
		task_summary = tasksDAO.getTaskFromTaskID(task_id).getTask_summary();
		task_description = tasksDAO.getTaskFromTaskID(task_id).getTask_description();
		task_created_by = tasksDAO.getTaskFromTaskID(task_id).getTask_created_by();
		task_created_date_and_time = tasksDAO.getTaskFromTaskID(task_id).getTask_created_date_and_time();
		task_assigned_to = tasksDAO.getTaskFromTaskID(task_id).getTask_assigned_to();
		due_date_and_time = tasksDAO.getTaskFromTaskID(task_id).getDue_date_and_time();
		priority = tasksDAO.getTaskFromTaskID(task_id).getPriority();
		progress = tasksDAO.getTaskFromTaskID(task_id).getProgress();
		task_current_status = tasksDAO.getTaskFromTaskID(task_id).getTask_current_status();

		first_name = contactsDAO.getContactFromID(contact_id).getFirst_name();
		last_name = contactsDAO.getContactFromID(contact_id).getLast_name();
		phone_or_mobile = contactsDAO.getContactFromID(contact_id).getPhone_or_mobile();
		email = contactsDAO.getContactFromID(contact_id).getEmail();
		fax = contactsDAO.getContactFromID(contact_id).getFax();
		address_line_1 = contactsDAO.getContactFromID(contact_id).getAddress_line_1();
		address_line_2 = contactsDAO.getContactFromID(contact_id).getAddress_line_2();
		city = contactsDAO.getContactFromID(contact_id).getCity();
		state_or_county = contactsDAO.getContactFromID(contact_id).getState_or_county();
		country = contactsDAO.getContactFromID(contact_id).getCountry();
		description = contactsDAO.getContactFromID(contact_id).getDescription();
		industry = contactsDAO.getContactFromID(contact_id).getIndustry();
		company = contactsDAO.getContactFromID(contact_id).getCompany();
		job_title = contactsDAO.getContactFromID(contact_id).getJob_title();
		created_by = contactsDAO.getContactFromID(contact_id).getCreated_by();
		created_date_and_time = contactsDAO.getContactFromID(contact_id).getCreated_date_and_time();
		contact_source = contactsDAO.getContactFromID(contact_id).getContact_source();


		// get all usernames
		allUserNameFromLogin = loginDAO.getAllUserNamesFromLogin();
		// assign it to drop down menu selections
		for (String userName:allUserNameFromLogin) {
			task_assigned_to_t.getItems().add(userName);
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date_and_time_string = dateFormat.format(created_date_and_time);
		task_created_date_and_time_string = dateFormat.format(task_created_date_and_time);
		if (due_date_and_time != null) {
			task_due_date_and_time_string = dateFormat.format(due_date_and_time);
		}

		contact_id_t.setText(String.valueOf(contact_id));
		first_name_t.setText(first_name);
		last_name_t.setText(last_name);
		phone_or_mobile_t.setText(phone_or_mobile);
		email_t.setText(email);
		fax_t.setText(fax);
		address_line_1_t.setText(address_line_1);
		address_line_2_t.setText(address_line_2);
		city_t.setText(city);
		state_or_county_t.setText(state_or_county);
		country_t.setText(country);
		description_t.setText(description);
		industry_t.setText(industry);
		company_t.setText(company);
		job_title_t.setText(job_title);
		created_by_t.setText(created_by);
		created_date_and_time_t.setText(date_and_time_string);
		contact_source_t.setText(contact_source);

		task_id_t.setText(String.valueOf(task_id));
		task_type_t.setText(task_type);
		task_summary_t.setText(task_summary);
		task_description_t.setText(task_description);
		task_created_by_t.setText(task_created_by);
		task_created_date_and_time_t.setText(task_created_date_and_time_string);
		task_assigned_to_t.setValue(task_assigned_to);
		due_date_and_time_t.setText(task_due_date_and_time_string);
		priority_t.setText(priority);
		progress_t.setValue(progress);
		task_current_status_t.setText(task_current_status);


	}
	
	@FXML
	private void deleteCurrentTask(ActionEvent event) {
		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete this task?");
		if (deleteConfirmation) {
			//delete selected contact
			// initiate contactsDAO type;
			tasksDAO.deleteTaskFromTaskID(task_id);
			//show successful delete scene
			sceneManager.switchScene(event, "SuccessfullyDeletedSelectedTask");
		} else {
			return;
		}
	}
	
	private boolean confirmationAlert(String message) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;

	}

	@FXML
	private void saveModifiedTask(ActionEvent event) {

		task_type = task_type_t.getText();
		if(task_type.isEmpty()) {
			task_type = null;
		}

		task_summary = task_summary_t.getText();
		if(task_summary.isEmpty()) {
			task_summary = null;
		}

		task_description = task_description_t.getText();
		if(task_description.isEmpty()) {
			task_description = null;
		}

		//////////////////////////////////////////////
		task_created_date_and_time_string = task_created_date_and_time_t.getText();

		// specify date and time pattern
		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m = p.matcher(task_created_date_and_time_string);
		boolean b = m.matches();

		// if pattern matches, then proceed to convert to Timestamp type to aviod exceptions
		if (b) {
			task_created_date_and_time = Timestamp.valueOf(task_created_date_and_time_string);
		} else {
			task_created_date_and_time = null;
		}
		/////////////////////////////////////////////

		task_assigned_to = task_assigned_to_t.getValue();
		if(task_assigned_to.isEmpty()) {
			task_assigned_to = null;
		}

		//////////////////////////////////////////////
		task_due_date_and_time_string = due_date_and_time_t.getText();

		// specify date and time pattern
		Pattern p1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m1 = p1.matcher(task_due_date_and_time_string);
		boolean b1 = m1.matches();

		// if pattern matches, then proceed to convert to Timestamp type to aviod exceptions
		if (b1) {
			due_date_and_time = Timestamp.valueOf(task_due_date_and_time_string);
		} else {
			due_date_and_time = null;
		}
		/////////////////////////////////////////////

		priority = priority_t.getText();
		if(priority.isEmpty()) {
			priority = null;
		}

		progress = Integer.valueOf((int) progress_t.getValue());

		task_current_status = task_current_status_t.getText();
		if(task_current_status.isEmpty()) {
			task_current_status = null;
		}

		if(tasksDAO.modifyTaskFromTaskID(contact_id,task_id,task_type,task_summary,task_description,task_created_by,task_created_date_and_time,task_assigned_to,due_date_and_time,priority,progress,task_current_status)) {
			sceneManager.switchScene(event, "SuccessfullyModifiedTask");
		} else {
			this.warningAlert("Task Save Failed, Please double check the details you entered and try again!");
		}

	}

	private void warningAlert(String message) {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		alert.showAndWait();
		return;
	}

	@FXML
	private void localTimeButton(ActionEvent event) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String localTime = dtf.format(now).toString();
		//System.out.println(localTime);

		due_date_and_time_t.setText(localTime);


	}

	@FXML
	private void datePicking(ActionEvent event) {

		String created_time_only;

		//get selected date
		LocalDate mydate = date_picker.getValue();
		String myFormattedDate = mydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// get created time only

		if(due_date_and_time != null) {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			created_time_only = dateFormat.format(due_date_and_time);
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			created_time_only = dtf.format(now).toString();
			//System.out.println(localTime);
		}

		String dateAndTimeConcat = myFormattedDate + " " + created_time_only;
		due_date_and_time_t.setText(dateAndTimeConcat);

	}



}
