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
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewActivityController implements Initializable {

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
	private TableColumn<Contacts,String> address_line_1_c;

	@FXML
	private TableColumn<Contacts,String> address_line_2_c;

	@FXML
	private Button cancel_b;

	@FXML
	private TableColumn<Contacts,String> city_c;

	@FXML
	private TableColumn<Contacts,String> company_c;

	@FXML
	private TableColumn<Contacts,Integer> contact_id_c;

	@FXML
	private Label contact_id_t;

	@FXML
	private TableColumn<Contacts,String> contact_source_c;

	@FXML
	private TableColumn<Contacts,String> country_c;

	@FXML
	private TableColumn<Contacts,String> created_by_c;

	@FXML
	private TableColumn<Contacts,Timestamp> created_date_and_time_c;

	@FXML
	private TableColumn<Contacts,String> description_c;

	@FXML
	private TableColumn<Contacts,String> email_c;

	@FXML
	private TableColumn<Contacts,String> fax_c;

	@FXML
	private TableColumn<Contacts,String> first_name_c;

	@FXML
	private TableColumn<Contacts,String> industry_c;

	@FXML
	private TableColumn<Contacts,String> job_title_c;

	@FXML
	private TableColumn<Contacts,String> last_name_c;

	@FXML
	private Label lead_created_by_t;

	@FXML
	private TableColumn<Contacts,String> phone_or_mobile_c;

	@FXML
	private Button save_b;

	@FXML
	private TableColumn<Contacts,String> state_or_county_c;

	@FXML
	private TableView<Contacts> table_view;

	@FXML
	private Label task_created_by_t;
	@FXML
	private DatePicker date_picker;
	@FXML
	private Button get_local_time;

	@FXML
	void cancelAndReturn(ActionEvent event) {
		sceneManager.switchScene(event, "ActivitiesHome");
	}

	@FXML
	void saveNewTask(ActionEvent event) {

	}

	@FXML
	void switchToAddNewContact(ActionEvent event) {

	}
	private SceneManager sceneManager = new SceneManager();

	private TempDataDAO tempDataDAO = new TempDataDAO();

	private LoginDAO loginDAO = new LoginDAO();

	private ContactsDAO contactsDAO = new ContactsDAO();
	
	private ActivitiesDAO activitiesDAO = new ActivitiesDAO();


	private String task_created_by;

	private ArrayList<Contacts> contactsArray;
	private ObservableList<Contacts> contactsObserve;
	private ObservableList<Contacts> selectedContact;

	private ArrayList<String> allUserNameFromLogin = new ArrayList<>();
	private ArrayList<Integer> allContactIDNotYetLeads = new ArrayList<>();

	private int contact_id;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		task_created_by = loginDAO.getLoginFromID(tempDataDAO.getCurrentUserID()).getFull_name();
		activity_created_by_t.setText(task_created_by);


		// new arraylist for contacts data
		contactsArray = new ArrayList<>();

		contactsArray = contactsDAO.getAllContacts();


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
		
		this.description_c.setVisible(false);
		
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


		//automatic select contacts from the previous memory
		int current_contact = tempDataDAO.getCurrentContactID();
		if (current_contact != -1 && contactsDAO.checkIfContactIDExistInDataBase(current_contact)) {

			int row_num = contactsDAO.getRowNumberFromID(current_contact);
			contact_id_t.setText(String.valueOf(current_contact));
			table_view.getSelectionModel().select(row_num);

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
		activity_created_date_and_time_t.setText(dateAndTimeConcat);

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
	private void saveNewActivity(ActionEvent event) {

		int activity_id = -1;


		if (contact_id_t.getText().isEmpty()) {
			this.warningAlert("Please Select A Contact First!");
			return;
		} else {
			contact_id = Integer.valueOf(contact_id_t.getText());
		}

		String activity_type = activity_type_t.getText();
		if (activity_type.isEmpty()) {
			activity_type = null;
		}

		String activity_summary = activity_summary_t.getText();
		if (activity_summary.isEmpty()) {
			activity_summary = null;
		}

		String activity_description = activity_description_t.getText();
		if (activity_description.isEmpty()) {
			activity_description = null;
		}

		String activity_created_by = activity_created_by_t.getText();
		if (activity_created_by.isEmpty()) {
			activity_created_by = null;
		}

		///////////////////
		String activity_created_date_and_time_string = activity_created_date_and_time_t.getText();

		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
		Matcher m = p.matcher(activity_created_date_and_time_string);
		boolean b = m.matches();

		Timestamp activity_created_date_and_time;

		// if yes, then proceed to convert to Timestamp type to aviod exceptions
		if (b) {
			activity_created_date_and_time = Timestamp.valueOf(activity_created_date_and_time_string);
		} else {
			activity_created_date_and_time = null;
		}
		//////////////////////

		if(activitiesDAO.addNewActivity(activity_id,contact_id,activity_type,activity_summary,activity_description,activity_created_by,activity_created_date_and_time)) {
			sceneManager.switchScene(event, "SuccessfullySavedNewActivity");
		} else {
			this.warningAlert("Activity Save Failed, Please double check the details you entered and try again!");
		}


	}

}
