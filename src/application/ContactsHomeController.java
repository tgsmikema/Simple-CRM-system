package application;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContactsHomeController implements Initializable {


	//injectable tableview

	@FXML
	private TableView<Contacts> table_view;

	@FXML
	private TextField search_box;

	@FXML
	private TableColumn<Contacts, String> address_line_1_c;

	@FXML
	private TableColumn<Contacts, String> address_line_2_c;

	@FXML
	private TableColumn<Contacts, String> city_c;

	@FXML
	private TableColumn<Contacts, String> company_c;

	@FXML
	private TableColumn<Contacts, Integer> contact_id_c;

	@FXML
	private TableColumn<Contacts, String> contact_source_c;

	@FXML
	private TableColumn<Contacts, String> created_by_c;

	@FXML
	private TableColumn<Contacts, Timestamp> created_date_and_time_c;

	@FXML
	private TableColumn<Contacts, String> description_c;

	@FXML
	private TableColumn<Contacts, String> email_c;

	@FXML
	private TableColumn<Contacts, String> fax_c;

	@FXML
	private TableColumn<Contacts, String> first_name_c;

	@FXML
	private TableColumn<Contacts, String> industry_c;

	@FXML
	private TableColumn<Contacts, String> job_title_c;

	@FXML
	private TableColumn<Contacts, String> last_name_c;

	@FXML
	private TableColumn<Contacts, String> phone_or_mobile_c;

	@FXML
	private TableColumn<Contacts, String> state_or_county_c;

	@FXML
	private TableColumn<Contacts,String> country_c;

	// injectable buttons
	@FXML
	private Button view_details;

	@FXML
	private Button add_new_contacts;

	@FXML
	private Button search_contacts;

	@FXML
	private Button dashboard;

	@FXML
	private Button delete_contact;

	// new sceneManager instance
	private SceneManager sceneManager = new SceneManager();

	private ArrayList<Contacts> contactsArray;
	//private final ObservableList<Contacts> contactsObserve;
	private ObservableList<Contacts> selectedContact;

	private TempDataDAO tempDataDAO = new TempDataDAO();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// initiate contactsDAO type;
		ContactsDAO contactsDAO = new ContactsDAO();
		// new arraylist for contacts data
		contactsArray = new ArrayList<>();
		// inject all contacts data from MySQL thru ContactsDAO methods into Arraylist
		contactsArray = contactsDAO.getAllContacts();
		// inject all contacts data Arraylist into Observable arraylist
		final ObservableList<Contacts> contactsObserve = FXCollections.observableArrayList(contactsArray);

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


		//table_view.getSelectionModel().setCellSelectionEnabled(true);
		selectedContact = table_view.getSelectionModel().getSelectedItems();

		selectedContact.addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<? extends Object> arg0) {
				// TODO Auto-generated method stub
				//System.out.println(selectedContact.get(0).getAddress_line_1() == null ||
				//		selectedContact.get(0).getAddress_line_1().isEmpty());
			}

		});

		FilteredList<Contacts> filteredData = new FilteredList<>(contactsObserve, b -> true);

		search_box.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(contacts -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (contacts.getFirst_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (contacts.getLast_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(contacts.getContact_id()).indexOf(lowerCaseFilter)!=-1) {
					return true;
				} else if (contacts.getPhone_or_mobile().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (contacts.getFax().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (contacts.getAddress_line_1().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getAddress_line_2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (contacts.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getState_or_county().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (contacts.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getIndustry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getCompany().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getJob_title().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} //else if (contacts.getCreated_by().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				//  return true;
				//} 
				else if (String.valueOf(contacts.getCreated_date_and_time()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (contacts.getContact_source().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else {  
					return false; // Does not match.
				}
			});
		});

		SortedList<Contacts> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(table_view.comparatorProperty());

		this.table_view.setItems(sortedData);

	}

	public void returnToDashBoard(ActionEvent e) {
		sceneManager.switchScene(e, "DashBoard");
	}

	public void deleteSelectedContact(ActionEvent e) {
		// if nothing was selected, then pop-up warning alert.
		if (selectedContact.size() == 0) {
			this.warningAlert("You haven't selected any contact!");
		} else {
			// confirm that you want to delete the selected contacct.
			boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete the selected contact?");
			if (deleteConfirmation) {
				//delete selected contact
				// initiate contactsDAO type;
				ContactsDAO contactsDAO = new ContactsDAO();
				contactsDAO.deleteContactFromID(selectedContact.get(0).getContact_id());
				//show successful delete scene
				sceneManager.switchScene(e, "SuccessfullyDeletedSelectedContact");
			} else {
				return;
			}
		}
	}

	public void newContact(ActionEvent e) {
		sceneManager.switchScene(e, "NewContact");
	}

	private void warningAlert(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		alert.showAndWait();
		return;
	}

	private boolean confirmationAlert(String message) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;

	}

	@FXML
	private void modifyContact(ActionEvent event) {

		if (selectedContact.size() == 0) {
			this.warningAlert("You haven't selected any contact!");
		} else {
			tempDataDAO.setCurrentContactID(selectedContact.get(0).getContact_id());
			sceneManager.switchScene(event, "ModifyContact");
		}
	}



}

