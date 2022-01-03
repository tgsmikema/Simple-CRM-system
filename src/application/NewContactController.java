package application;

import java.net.URL;
import java.sql.Timestamp;
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

public class NewContactController implements Initializable {

	@FXML
	private Label address_line_1_l;

	@FXML
	private TextField address_line_1_t;

	@FXML
	private Label address_line_2_l;

	@FXML
	private TextField address_line_2_t;

	@FXML
	private Button cancel;

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
	private Button getLocalTime;

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
	private Label phone_or_mobile_l;

	@FXML
	private TextField phone_or_mobile_t;

	@FXML
	private Button save;

	@FXML
	private Label state_or_county_l;

	@FXML
	private TextField state_or_county_t;
	
    @FXML
    private DatePicker datePicker;

	private SceneManager sceneManager = new SceneManager();

	private TempDataDAO tempDataDAO = new TempDataDAO();

	private LoginDAO loginDAO = new LoginDAO();

	private ContactsDAO contactsDAO = new ContactsDAO();

	private String created_by;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// get current loggen in user full name, and set label.
		created_by = loginDAO.getLoginFromID(tempDataDAO.getCurrentUserID()).getFull_name();
		created_by_t.setText(created_by);

	}

	public void saveNewContact(ActionEvent event) {

		// initialise input fields into variables

		// new contact doesn't need to specify contact_id
		String first_name = first_name_t.getText();
		String last_name = last_name_t.getText();
		String phone_or_mobile = phone_or_mobile_t.getText();
		String email = email_t.getText();
		String fax = fax_t.getText();
		String address_line_1 = address_line_1_t.getText();
		String address_line_2 = address_line_2_t.getText();
		String city = city_t.getText();
		String state_or_county = state_or_county_t.getText();
		String country = country_t.getText();
		String description = description_t.getText();
		String industry = industry_t.getText();
		String company = company_t.getText();
		String job_title = job_title_t.getText();
		//created_by already initialized in the initialize method
		String date_and_time = created_date_and_time_t.getText();

		String contact_source = contact_source_t.getText();

		if (first_name.isEmpty()) {
			first_name = null;
		}

		if (last_name.isEmpty()) {
			last_name = null;
		}

		if (phone_or_mobile.isEmpty()) {
			phone_or_mobile = null;
		}

		if (email.isEmpty()) {
			email = null;
		}

		if (fax.isEmpty()) {
			fax = null;
		}

		if (address_line_1.isEmpty()) {
			address_line_1 = null;
		}

		if (address_line_2.isEmpty()) {
			address_line_2 = null;
		}

		if (city.isEmpty()) {
			city = null;
		}

		if (state_or_county.isEmpty()) {
			state_or_county = null;
		}

		if (country.isEmpty()) {
			country = null;
		}

		if (description.isEmpty()) {
			description = null;
		}

		if (industry.isEmpty()) {
			industry = null;
		}

		if (company.isEmpty()) {
			company = null;
		}

		if (job_title.isEmpty()) {
			job_title = null;
		}

		//checking if time and date is entered in a date pattern.

		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m = p.matcher(date_and_time);
		boolean b = m.matches();

		Timestamp created_date_and_time;

		// if yes, then proceed to convert to Timestamp type to aviod exceptions
		if (b) {
			created_date_and_time = Timestamp.valueOf(date_and_time);
		} else {
			created_date_and_time = null;
		}

		if (contact_source.isEmpty()) {
			contact_source = null;
		}

		if(contactsDAO.addNewContact(first_name, last_name, phone_or_mobile, email, fax, 
				address_line_1, address_line_2, city, state_or_county, country, description,
				industry, company, job_title, created_by, created_date_and_time, contact_source)) {
			sceneManager.switchScene(event, "SuccessfullySavedNewContact");
		} else {
			this.warningAlert("Contact Save Failed, Please double check the details you entered and try again!");
		}

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
	private void cancelAndReturn(ActionEvent event) {
		sceneManager.switchScene(event, "ContactsHome");
	}
	

	private void warningAlert(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		alert.showAndWait();
		return;
	}
	
	@FXML
	private void datePicking(ActionEvent event) {
		
		//get selected date
		LocalDate mydate = datePicker.getValue();
		String myFormattedDate = mydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		//get local time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String localTime = dtf.format(now).toString();
		System.out.println(localTime);
		
		String dateAndTimeConcat = myFormattedDate + " " + localTime;
		created_date_and_time_t.setText(dateAndTimeConcat);
		
	}



}
