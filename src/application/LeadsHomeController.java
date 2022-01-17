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

public class LeadsHomeController implements Initializable {

	@FXML
	private Button add_new_lead;

	@FXML
	private TableColumn<LeadsHybridContacts,String> address_line_1_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> address_line_2_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> assigned_to_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> city_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> company_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> contact_created_by_c;

	@FXML
	private TableColumn<LeadsHybridContacts,Timestamp> contact_created_date_and_time_c;

	@FXML
	private TableColumn<LeadsHybridContacts,Integer> contact_id_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> contact_source_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> country_c;

	@FXML
	private Button dashboard;

	@FXML
	private TextField search_box;

	@FXML
	private TableColumn<LeadsHybridContacts,String> description_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> email_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> fax_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> first_name_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> if_lost_reasons_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> industry_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> job_title_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> last_name_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> lead_created_by_c;

	@FXML
	private TableColumn<LeadsHybridContacts,Timestamp> lead_created_date_and_time_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> lead_source_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> lead_status_c;

	@FXML
	private TableColumn<LeadsHybridContacts,String> phone_or_mobile_c;

	@FXML
	private Button search_lead;

	@FXML
	private TableColumn<LeadsHybridContacts,String> state_or_county_c;

	@FXML
	private TableView<LeadsHybridContacts> table_view;

	@FXML
	private Button view_details;

	@FXML
	private Button show_hide_columns_b;


	@FXML
	void newLead(ActionEvent event) {
		sceneManager.switchScene(event, "NewLead");
	}

	@FXML
	void returnToDashBoard(ActionEvent event) {
		sceneManager.switchScene(event, "DashBoard");
	}

	// new sceneManager instance
	private SceneManager sceneManager = new SceneManager();

	private ArrayList<LeadsHybridContacts> leadsHybridContactsArray;
	private ObservableList<LeadsHybridContacts> leadsHybridContactsObserve;
	private ObservableList<LeadsHybridContacts> selectedleadsHybridContacts;

	private TempDataDAO tempDataDAO = new TempDataDAO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		LeadsDAO leadsDAO = new LeadsDAO();

		leadsHybridContactsArray = new ArrayList<>();

		leadsHybridContactsArray = leadsDAO.getAllLeadsHybridContacts();

		leadsHybridContactsObserve = FXCollections.observableArrayList(leadsHybridContactsArray);


		this.contact_id_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,Integer>("contact_id")));
		this.lead_source_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("lead_source")));
		this.lead_status_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("lead_status")));
		this.if_lost_reasons_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("if_lost_reasons")));
		this.lead_created_by_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("lead_created_by")));
		this.lead_created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,Timestamp>("lead_created_date_and_time")));
		this.assigned_to_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("assigned_to")));


		this.first_name_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("first_name")));
		this.last_name_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("last_name")));
		this.phone_or_mobile_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("phone_or_mobile")));
		this.email_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("email")));
		this.fax_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("fax")));
		this.address_line_1_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("address_line_1")));
		this.address_line_2_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("address_line_2")));
		this.city_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("city")));
		this.state_or_county_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("state_or_county")));
		this.country_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("country")));
		this.description_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("description")));
		this.industry_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("industry")));
		this.company_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("company")));
		this.job_title_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("job_title")));
		this.contact_created_by_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("created_by")));
		this.contact_created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,Timestamp>("created_date_and_time")));
		this.contact_source_c.setCellValueFactory((new PropertyValueFactory<LeadsHybridContacts,String>("contact_source")));
		//this.table_view.setItems(leadsHybridContactsObserve);

		//hide unnecessary columns
		this.hideUnnecessaryColumns(null);

		selectedleadsHybridContacts = table_view.getSelectionModel().getSelectedItems();


		selectedleadsHybridContacts.addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<? extends Object> arg0) {
				// TODO Auto-generated method stub

			}

		});

		FilteredList<LeadsHybridContacts> filteredData = new FilteredList<>(leadsHybridContactsObserve, b -> true);

		search_box.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(leadsHybridContacts -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (leadsHybridContacts.getFirst_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (leadsHybridContacts.getLead_source().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}  else if (leadsHybridContacts.getLead_status().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}  else if (leadsHybridContacts.getIf_lost_reasons().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}  else if (leadsHybridContacts.getLead_created_by().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(leadsHybridContacts.getLead_created_date_and_time()).indexOf(lowerCaseFilter) != -1) {
						return true;
				}  else if (leadsHybridContacts.getAssigned_to().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}  else if (leadsHybridContacts.getLast_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(leadsHybridContacts.getContact_id()).indexOf(lowerCaseFilter)!=-1) {
					return true;
				} else if (leadsHybridContacts.getPhone_or_mobile().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (leadsHybridContacts.getFax().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (leadsHybridContacts.getAddress_line_1().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getAddress_line_2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (leadsHybridContacts.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getState_or_county().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (leadsHybridContacts.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getIndustry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getCompany().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getJob_title().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} //else if (contacts.getCreated_by().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				//  return true;
				//} 
				else if (String.valueOf(leadsHybridContacts.getCreated_date_and_time()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (leadsHybridContacts.getContact_source().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else {  
					return false; // Does not match.
				}
			});
		});

		SortedList<LeadsHybridContacts> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(table_view.comparatorProperty());

		this.table_view.setItems(sortedData);



	}

	@FXML
	public void hideUnnecessaryColumns(ActionEvent event) {
		fax_c.setVisible(false);
		address_line_1_c.setVisible(false);
		address_line_2_c.setVisible(false);
		city_c.setVisible(false);
		state_or_county_c.setVisible(false);
		country_c.setVisible(false);
		description_c.setVisible(false);
		industry_c.setVisible(false);
		company_c.setVisible(false);
		job_title_c.setVisible(false);
		contact_created_by_c.setVisible(false);
		contact_created_date_and_time_c.setVisible(false);
		contact_source_c.setVisible(false);

		show_hide_columns_b.setText("Show Extra Columns");

		show_hide_columns_b.setOnAction(this::showUnnecessaryColumns);
	}

	@FXML
	public void showUnnecessaryColumns(ActionEvent event) {
		fax_c.setVisible(true);
		address_line_1_c.setVisible(true);
		address_line_2_c.setVisible(true);
		city_c.setVisible(true);
		state_or_county_c.setVisible(true);
		country_c.setVisible(true);
		description_c.setVisible(true);
		industry_c.setVisible(true);
		company_c.setVisible(true);
		job_title_c.setVisible(true);
		contact_created_by_c.setVisible(true);
		contact_created_date_and_time_c.setVisible(true);
		contact_source_c.setVisible(true);

		show_hide_columns_b.setText("Hide Extra Columns");

		show_hide_columns_b.setOnAction(this::hideUnnecessaryColumns);

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
	private void modifyOrViewDetailLead(ActionEvent event) {
		if (selectedleadsHybridContacts.size() == 0) {
			this.warningAlert("You haven't selected any lead!");
		} else {
			tempDataDAO.setCurrentContactID(selectedleadsHybridContacts.get(0).getContact_id());
			sceneManager.switchScene(event, "ModifyLead");
		}
	}

}
