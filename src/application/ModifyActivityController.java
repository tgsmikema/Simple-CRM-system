package application;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ModifyActivityController implements Initializable {

	@FXML
	private Label activity_created_by_t;

	@FXML
	private TextField activity_created_date_and_time_t;

	@FXML
	private TextField activity_description_t;

	@FXML
	private Label activity_id_t;

	@FXML
	private TextField activity_summary_t;

	@FXML
	private TextField activity_type_t;

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
	private Label phone_or_mobile_t;

	@FXML
	private Button save_b;

	@FXML
	private Label state_or_county_t;

	@FXML
	private Label task_created_date_and_time_t;

	private SceneManager sceneManager = new SceneManager();

	private TempDataDAO tempDataDAO = new TempDataDAO();

	private LoginDAO loginDAO = new LoginDAO();

	private ContactsDAO contactsDAO = new ContactsDAO();

	private TasksDAO tasksDAO = new TasksDAO();

	private ActivitiesDAO activitiesDAO = new ActivitiesDAO();

	private int activity_id;
	private int contact_id;
	private String activity_type;
	private String activity_summary;
	private String activity_description;
	private String activity_created_by;
	private String activity_created_date_and_time_string; private Timestamp activity_created_date_and_time;

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
	private String created_date_and_time_string; private Timestamp created_date_and_time;
	private String contact_source;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		activity_id = tempDataDAO.getCurrentContactID();

		contact_id = activitiesDAO.getActivityFromActivityID(activity_id).getContact_id();
		activity_type = activitiesDAO.getActivityFromActivityID(activity_id).getActivity_type();
		activity_summary = activitiesDAO.getActivityFromActivityID(activity_id).getActivity_summary();
		activity_description = activitiesDAO.getActivityFromActivityID(activity_id).getActivity_description();
		activity_created_by = activitiesDAO.getActivityFromActivityID(activity_id).getActivity_created_by();
		activity_created_date_and_time = activitiesDAO.getActivityFromActivityID(activity_id).getActivity_created_date_and_time();

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


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		activity_created_date_and_time_string = dateFormat.format(activity_created_date_and_time);
		created_date_and_time_string = dateFormat.format(created_date_and_time);

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
		created_date_and_time_t.setText(created_date_and_time_string);
		contact_source_t.setText(contact_source);

		activity_id_t.setText(String.valueOf(activity_id));
		activity_type_t.setText(activity_type);
		activity_summary_t.setText(activity_summary);
		activity_description_t.setText(activity_description);
		activity_created_by_t.setText(activity_created_by);
		activity_created_date_and_time_t.setText(activity_created_date_and_time_string);


	}

	@FXML
	void cancelAndReturn(ActionEvent event) {
		sceneManager.switchScene(event, "ActivitiesHome");
	}

	@FXML
	private void deleteCurrentActivity(ActionEvent event) {
		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete this activity?");
		if (deleteConfirmation) {
			//delete selected contact
			// initiate contactsDAO type;
			activitiesDAO.deleteActivityFromActivityID(activity_id);
			//show successful delete scene
			sceneManager.switchScene(event, "SuccessfullyDeletedSelectedActivity");
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
	void saveModifiedActivity(ActionEvent event) {

		activity_type = activity_type_t.getText();
		if(activity_type.isEmpty()) {
			activity_type = null;
		}

		activity_summary = activity_summary_t.getText();
		if(activity_summary.isEmpty()) {
			activity_summary = null;
		}

		activity_description = activity_description_t.getText();
		if(activity_description.isEmpty()) {
			activity_description = null;
		}

		//////////////////////////////////////////////
		activity_created_date_and_time_string = activity_created_date_and_time_t.getText();

		// specify date and time pattern
		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m = p.matcher(activity_created_date_and_time_string);
		boolean b = m.matches();

		// if pattern matches, then proceed to convert to Timestamp type to aviod exceptions
		if (b) {
			activity_created_date_and_time = Timestamp.valueOf(activity_created_date_and_time_string);
		} else {
			activity_created_date_and_time = null;
		}
		/////////////////////////////////////////////

		if(activitiesDAO.modifyActivityFromID(activity_id, contact_id, activity_type, activity_summary, activity_description, activity_created_by, activity_created_date_and_time)) {
			sceneManager.switchScene(event, "SuccessfullyModifiedActivity");
		} else {
			this.warningAlert("Activity Save Failed, Please double check the details you entered and try again!");
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

		activity_created_date_and_time_t.setText(localTime);


	}

	@FXML
	private void datePicking(ActionEvent event) {

		String created_time_only;

		//get selected date
		LocalDate mydate = date_picker.getValue();
		String myFormattedDate = mydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// get created time only

		
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			created_time_only = dateFormat.format(activity_created_date_and_time);
		
		String dateAndTimeConcat = myFormattedDate + " " + created_time_only;
		activity_created_date_and_time_t.setText(dateAndTimeConcat);

	}



}
