package application;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ModifyLeadController implements Initializable{

	@FXML
	private Label address_line_1_t;

	@FXML
	private Label address_line_2_t;

	@FXML
	private ChoiceBox<String> assigned_to_t;

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
	private TextField if_lost_reasons_t;

	@FXML
	private Label industry_t;

	@FXML
	private Label job_title_t;

	@FXML
	private Label last_name_t;

	@FXML
	private TextField lead_created_date_and_time_t;

	@FXML
	private TextField lead_source_t;

	@FXML
	private TextField lead_status_t;

	@FXML
	private Button new_activity_b;

	@FXML
	private Button new_task_b;

	@FXML
	private Label phone_or_mobile_t;

	@FXML
	private Button save_b;

	@FXML
	private Label state_or_county_t;

	@FXML
	private Label lead_created_by_t;

	private SceneManager sceneManager = new SceneManager();

	private TempDataDAO tempDataDAO = new TempDataDAO();

	private LoginDAO loginDAO = new LoginDAO();

	private ContactsDAO contactsDAO = new ContactsDAO();

	private LeadsDAO leadsDAO = new LeadsDAO();

	private ArrayList<String> allUserNameFromLogin = new ArrayList<>();

	// initialise parameters.
	int contact_id;
	String first_name;
	String last_name;
	String phone_or_mobile;
	String email;
	String fax;
	String address_line_1;
	String address_line_2;
	String city;
	String state_or_county;
	String country;
	String description;
	String industry;
	String company;
	String job_title;
	String created_by;
	String date_and_time; Timestamp created_date_and_time;
	String contact_source;

	String lead_source;
	String lead_status;
	String if_lost_reasons;
	String lead_created_by;
	String lead_date_and_time; Timestamp lead_created_date_and_time;
	String assigned_to;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		// get all selected contact data from database.
		contact_id = tempDataDAO.getCurrentContactID();


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


		lead_source = leadsDAO.getLeadFromID(contact_id).getLead_source();
		lead_status = leadsDAO.getLeadFromID(contact_id).getLead_status();
		if_lost_reasons = leadsDAO.getLeadFromID(contact_id).getIf_lost_reasons();
		lead_created_by = leadsDAO.getLeadFromID(contact_id).getCreated_by();
		lead_created_date_and_time = leadsDAO.getLeadFromID(contact_id).getCreated_date_and_time();
		assigned_to = leadsDAO.getLeadFromID(contact_id).getAssigned_to();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date_and_time = dateFormat.format(created_date_and_time);

		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lead_date_and_time = dateFormat2.format(lead_created_date_and_time);

		// get all usernames
		allUserNameFromLogin = loginDAO.getAllUserNamesFromLogin();
		// assign it to drop down menu selections
		for (String userName:allUserNameFromLogin) {
			assigned_to_t.getItems().add(userName);
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
		created_date_and_time_t.setText(date_and_time);
		contact_source_t.setText(contact_source);

		lead_source_t.setText(lead_source);
		lead_status_t.setText(lead_status);
		if_lost_reasons_t.setText(if_lost_reasons);
		lead_created_by_t.setText(lead_created_by);
		lead_created_date_and_time_t.setText(lead_date_and_time);
		assigned_to_t.setValue(assigned_to);
	}

	@FXML
	public void cancelAndReturn(ActionEvent event) {
		sceneManager.switchScene(event, "LeadsHome");
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

		lead_created_date_and_time_t.setText(localTime);


	}

	@FXML
	private void datePicking(ActionEvent event) {

		//get selected date
		LocalDate mydate = date_picker.getValue();
		String myFormattedDate = mydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// get created time only
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String created_time_only = dateFormat.format(lead_created_date_and_time);
		//System.out.println(localTime);

		String dateAndTimeConcat = myFormattedDate + " " + created_time_only;
		lead_created_date_and_time_t.setText(dateAndTimeConcat);

	}

	@FXML
	public void saveModifiedLead(ActionEvent event) {


		lead_source = lead_source_t.getText();
		if(lead_source.isEmpty()) {
			lead_source = null;
		}
		lead_status = lead_status_t.getText();
		if(lead_status.isEmpty()) {
			lead_status = null;
		}
		if_lost_reasons = if_lost_reasons_t.getText();
		if(if_lost_reasons.isEmpty()) {
			if_lost_reasons = null;
		}
		//lead_created_by not changable
		/////////////////////////////////////////////////////
		lead_date_and_time = lead_created_date_and_time_t.getText();

		// specify date and time pattern
		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m = p.matcher(lead_date_and_time);
		boolean b = m.matches();

		// if pattern matches, then proceed to convert to Timestamp type to aviod exceptions
		if (b) {
			lead_created_date_and_time = Timestamp.valueOf(lead_date_and_time);
		} else {
			lead_created_date_and_time = null;
		}
		////////////////////////////////////////////////////
		assigned_to = assigned_to_t.getValue();
		if(assigned_to.isEmpty()) {
			assigned_to = null;
		}


		if(leadsDAO.modifyLeadFromID(contact_id, lead_source, lead_status, if_lost_reasons, lead_created_by, lead_created_date_and_time, assigned_to)) {
			sceneManager.switchScene(event, "SuccessfullyModifiedLead");
		} else {
			this.warningAlert("Lead Save Failed, Please double check the details you entered and try again!");
		}


	}


}
