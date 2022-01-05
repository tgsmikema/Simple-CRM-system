package application;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewLeadController implements Initializable {

	@FXML
	private TableColumn<Contacts, String> address_line_1_c;

	@FXML
	private TableColumn<Contacts, String> address_line_2_c;

	@FXML
	private ChoiceBox<String> assigned_to_t;

	@FXML
	private Button cancel_b;

	@FXML
	private TableColumn<Contacts, String> city_c;

	@FXML
	private TableColumn<Contacts, String> company_c;

	@FXML
	private TableColumn<Contacts, Integer> contact_id_c;

	@FXML
	private Label contact_id_t;

	@FXML
	private TableColumn<Contacts, String> contact_source_c;

	@FXML
	private TableColumn<Contacts, String> country_c;

	@FXML
	private TableColumn<Contacts, String> created_by_c;

	@FXML
	private TableColumn<Contacts, Timestamp> created_date_and_time_c;

	@FXML
	private DatePicker date_picker;

	@FXML
	private TableColumn<Contacts, String> description_c;

	@FXML
	private TableColumn<Contacts, String> email_c;

	@FXML
	private TableColumn<Contacts, String> fax_c;

	@FXML
	private TableColumn<Contacts, String> first_name_c;

	@FXML
	private TextField if_lost_reasons_t;

	@FXML
	private TableColumn<Contacts, String> industry_c;

	@FXML
	private TableColumn<Contacts, String> job_title_c;

	@FXML
	private TableColumn<Contacts, String> last_name_c;

	@FXML
	private Label lead_created_by_t;

	@FXML
	private TextField lead_created_date_and_time;

	@FXML
	private TextField lead_source_t;

	@FXML
	private TextField lead_status_t;

	@FXML
	private TableColumn<Contacts, String> phone_or_mobile_c;

	@FXML
	private Button save_b;

	@FXML
	private TableColumn<Contacts, String> state_or_county_c;

	@FXML
	private TableView<Contacts> table_view;



	private SceneManager sceneManager = new SceneManager();

	private TempDataDAO tempDataDAO = new TempDataDAO();

	private LoginDAO loginDAO = new LoginDAO();

	private ContactsDAO contactsDAO = new ContactsDAO();
	
	private LeadsDAO leadsDAO = new LeadsDAO();

	private String lead_created_by;

	private ArrayList<Contacts> contactsArray;
	private ObservableList<Contacts> contactsObserve;
	private ObservableList<Contacts> selectedContact;

	private ArrayList<String> allUserNameFromLogin = new ArrayList<>();
	private ArrayList<Integer> allContactIDNotYetLeads = new ArrayList<>();
	
	private int contact_id;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lead_created_by = loginDAO.getLoginFromID(tempDataDAO.getCurrentUserID()).getFull_name();
		lead_created_by_t.setText(lead_created_by);

		// get all usernames
		allUserNameFromLogin = loginDAO.getAllUserNamesFromLogin();
		// assign it to drop down menu selections
		for (String userName:allUserNameFromLogin) {
			assigned_to_t.getItems().add(userName);
		}
		
		// new arraylist for contacts data
		contactsArray = new ArrayList<>();
	
		allContactIDNotYetLeads = leadsDAO.getAllContactsIDNotLeadsYet();
		
		if (allContactIDNotYetLeads != null) {
			for (int contactID: allContactIDNotYetLeads) {
				Contacts contact = new Contacts();
				contact = contactsDAO.getContactFromID(contactID);
				contactsArray.add(contact);
			}
		}

		
		// inject all contacts data from MySQL thru ContactsDAO methods into Arraylist
		
		//contactsArray = contactsDAO.getAllContacts();
		
		// inject all contacts data Arraylist into Observable arraylist
		contactsObserve = FXCollections.observableArrayList(contactsArray);

		// display observable list items into tableview
		this.contact_id_c.setCellValueFactory((new PropertyValueFactory<Contacts,Integer>("contact_id")));
		this.first_name_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("first_name")));
		this.last_name_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("last_name")));
		this.phone_or_mobile_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("phone_or_mobile")));
		this.email_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("email")));
		this.fax_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("fax")));
		this.address_line_1_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("address_line_1")));
		this.address_line_2_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("address_line_2")));
		this.city_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("city")));
		this.state_or_county_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("state_or_county")));
		this.country_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("country")));
		this.description_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("description")));
		this.industry_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("industry")));
		this.company_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("company")));
		this.job_title_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("job_title")));
		this.created_by_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("created_by")));
		this.created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<Contacts,Timestamp>("created_date_and_time")));
		this.contact_source_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("contact_source")));
		this.table_view.setItems(contactsObserve);

		//table_view.getSelectionModel().setCellSelectionEnabled(true);
		selectedContact = table_view.getSelectionModel().getSelectedItems();

		selectedContact.addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<? extends Object> arg0) {
				// TODO Auto-generated method stub
				//System.out.println(selectedContact.get(0).getAddress_line_1() == null ||
				//		selectedContact.get(0).getAddress_line_1().isEmpty());

				contact_id_t.setText(String.valueOf(selectedContact.get(0).getContact_id()));
			}

		});

	}

	@FXML
	public void saveNewLead(ActionEvent event) {
		
		if (contact_id_t.getText().isEmpty()) {
			this.warningAlert("Please Select A Contact First!");
			return;
		}
		
		contact_id = selectedContact.get(0).getContact_id();
		String lead_source = lead_source_t.getText();
		String lead_status = lead_status_t.getText();
		String if_lost_reasons = if_lost_reasons_t.getText();
		// lead_created_by already initialised earlier.
		String lead_date_and_time_string = lead_created_date_and_time.getText();
		String assigned_to = assigned_to_t.getValue();

		if (lead_source.isEmpty()) {
			lead_source = null;
		}

		if (lead_status.isEmpty()) {
			lead_status = null;
		}

		if (if_lost_reasons.isEmpty()) {
			if_lost_reasons = null;
		}

		///////////////////
		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m = p.matcher(lead_date_and_time_string);
		boolean b = m.matches();

		Timestamp lead_created_date_and_time;

		// if yes, then proceed to convert to Timestamp type to aviod exceptions
		if (b) {
			lead_created_date_and_time = Timestamp.valueOf(lead_date_and_time_string);
		} else {
			lead_created_date_and_time = null;
		}
		//////////////////////


		if(leadsDAO.addNewLead(contact_id, lead_source, lead_status, if_lost_reasons, lead_created_by, lead_created_date_and_time, assigned_to)) {
			sceneManager.switchScene(event, "SuccessfullySavedNewLead");
		} else {
			this.warningAlert("Lead Save Failed, Please double check the details you entered and try again!");
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
	private void datePicker(ActionEvent event) {
		
		//get selected date
		LocalDate mydate = date_picker.getValue();
		String myFormattedDate = mydate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		//get local time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String localTime = dtf.format(now).toString();
		//System.out.println(localTime);
		
		String dateAndTimeConcat = myFormattedDate + " " + localTime;
		lead_created_date_and_time.setText(dateAndTimeConcat);
		
	}

	
	@FXML
	private void cancelAndReturn(ActionEvent event) {
		sceneManager.switchScene(event, "LeadsHome");
	}


	@FXML
	private void localTimeButton(ActionEvent event) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String localTime = dtf.format(now).toString();
		//System.out.println(localTime);

		lead_created_date_and_time.setText(localTime);


	}
	
	@FXML
	private void switchToAddNewContact(ActionEvent event) {
		sceneManager.switchScene(event, "NewContact");
	}



}
