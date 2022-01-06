package application;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
				
				
				
				
	}
	
	

}
