package application;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ModifyContactController implements Initializable {

	@FXML
	private Label address_line_1_l;

	@FXML
	private TextField address_line_1_t;

	@FXML
	private Label address_line_2_l;

	@FXML
	private TextField address_line_2_t;

	@FXML
	private Button cancel_b;

	@FXML
	private Label city_l;

	@FXML
	private TextField city_t;

	@FXML
	private Label company_l;

	@FXML
	private TextField company_t;

	@FXML
	private Label contact_id_l;

	@FXML
	private Label contact_id_t;

	@FXML
	private Label contact_source_l;

	@FXML
	private TextField contact_source_t;

	@FXML
	private Label country_l;

	@FXML
	private TextField country_t;

	@FXML
	private Label created_by_l;

	@FXML
	private Label created_by_t;

	@FXML
	private Label created_date_and_time_l;

	@FXML
	private TextField created_date_and_time_t;

	@FXML
	private DatePicker date_picker;

	@FXML
	private Label description_l;

	@FXML
	private TextField description_t;

	@FXML
	private Label email_l;

	@FXML
	private TextField email_t;

	@FXML
	private Label fax_l;

	@FXML
	private TextField fax_t;

	@FXML
	private Label first_name_l;

	@FXML
	private TextField first_name_t;

	@FXML
	private Button get_current_date_and_time_b;

	@FXML
	private Label industry_l;

	@FXML
	private TextField industry_t;

	@FXML
	private Label job_title_l;

	@FXML
	private TextField job_title_t;

	@FXML
	private Label last_name_l;

	@FXML
	private TextField last_name_t;

	@FXML
	private Button new_activity_b;

	@FXML
	private Button new_task_b;

	@FXML
	private Label phone_or_mobile_l;

	@FXML
	private TextField phone_or_mobile_t;

	@FXML
	private Button save_b;

	@FXML
	private Label state_or_county_l;

	@FXML
	private TextField state_or_county_t;


	private SceneManager sceneManager = new SceneManager();

	private TempDataDAO tempDataDAO = new TempDataDAO();

	private LoginDAO loginDAO = new LoginDAO();

	private ContactsDAO contactsDAO = new ContactsDAO();

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

		// set related fields with database values
		contact_id_t.setText(String.valueOf(contact_id));
		first_name_t.setText(first_name);
		last_name_t.setText(last_name);

		if (phone_or_mobile != null) {
			phone_or_mobile_t.setText(phone_or_mobile);
		}

		email_t.setText(email);

		if (fax != null) {
			fax_t.setText(fax);
		}

		if (address_line_1 != null) {
			address_line_1_t.setText(address_line_1);
		}

		if (address_line_2 != null) {
			address_line_2_t.setText(address_line_2);
		}

		if (city != null) {
			city_t.setText(city);
		}

		if (state_or_county != null) {
			state_or_county_t.setText(state_or_county);
		}

		if (country != null) {
			country_t.setText(country);
		}

		if (description != null) {
			description_t.setText(description);
		}

		if (industry != null) {
			industry_t.setText(industry);
		}

		if (company != null) {
			company_t.setText(company);
		}

		if (job_title != null) {
			job_title_t.setText(job_title);
		}


		created_by_t.setText(created_by);
		
		// makesure the timestamp format is following:
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    date_and_time = dateFormat.format(created_date_and_time);
	    created_date_and_time_t.setText(date_and_time);
	    

		if (contact_source != null) {
			contact_source_t.setText(contact_source);
		}

	}
	
	@FXML
	public void newActivity(ActionEvent event) {
		sceneManager.switchScene(event, "NewActivity");
	}
	
	@FXML
	public void newTask(ActionEvent event) {
		sceneManager.switchScene(event, "NewTask");
	}

	public void saveModifiedContact(ActionEvent event) {



		first_name = first_name_t.getText();
		if(first_name.isEmpty()) {
			first_name = null;
		}
		last_name = last_name_t.getText();
		if(last_name.isEmpty()) {
			last_name = null;
		}
		phone_or_mobile = phone_or_mobile_t.getText();
		if(phone_or_mobile.isEmpty()) {
			phone_or_mobile = null;
		}
		email = email_t.getText();
		if(email.isEmpty()) {
			email = null;
		}
		fax = fax_t.getText();
		if(fax.isEmpty()) {
			fax = null;
		}
		address_line_1 = address_line_1_t.getText();
		if(address_line_1.isEmpty()) {
			address_line_1 = null;
		}
		address_line_2 = address_line_2_t.getText();
		if(address_line_2.isEmpty()) {
			address_line_2 = null;
		}
		city = city_t.getText();
		if(city.isEmpty()) {
			city = null;
		}
		state_or_county = state_or_county_t.getText();
		if(state_or_county.isEmpty()) {
			state_or_county = null;
		}
		country = country_t.getText();
		if(country.isEmpty()) {
			country = null;
		}
		description = description_t.getText();
		if(description.isEmpty()) {
			description = null;
		}
		industry = industry_t.getText();
		if(industry.isEmpty()) {
			industry = null;
		}
		company = company_t.getText();
		if(company.isEmpty()) {
			company = null;
		}
		job_title = job_title_t.getText();
		if(job_title.isEmpty()) {
			job_title = null;
		}
		//created_by not changable
		/////////////////////////////////////////////////////
		date_and_time = created_date_and_time_t.getText();

		// specify date and time pattern
		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m = p.matcher(date_and_time);
		boolean b = m.matches();
		
		// if pattern matches, then proceed to convert to Timestamp type to aviod exceptions
		if (b) {
			created_date_and_time = Timestamp.valueOf(date_and_time);
		} else {
			created_date_and_time = null;
		}
		////////////////////////////////////////////////////
		contact_source = contact_source_t.getText();
		if(contact_source.isEmpty()) {
			contact_source = null;
		}

		if(contactsDAO.modifyContactFromID(contact_id,first_name, last_name, phone_or_mobile, email, fax, 
				address_line_1, address_line_2, city, state_or_county, country, description,
				industry, company, job_title, created_by, created_date_and_time, contact_source)) {
			sceneManager.switchScene(event, "SuccessfullyModifiedContact");
		} else {
			this.warningAlert("Contact Save Failed, Please double check the details you entered and try again!");
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

		created_date_and_time_t.setText(localTime);


	}
	
	@FXML
	private void datePicking(ActionEvent event) {
		
		//get selected date
		LocalDate mydate = date_picker.getValue();
		String myFormattedDate = mydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		// get created time only
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    String created_time_only = dateFormat.format(created_date_and_time);
		//System.out.println(localTime);
		
		String dateAndTimeConcat = myFormattedDate + " " + created_time_only;
		created_date_and_time_t.setText(dateAndTimeConcat);
		
	}
	
	@FXML
	private void cancelAndReturn(ActionEvent event) {
		sceneManager.switchScene(event, "ContactsHome");
	}



}
