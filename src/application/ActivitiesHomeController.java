package application;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ActivitiesHomeController implements Initializable{

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> activity_created_by_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,Timestamp> activity_created_date_and_time_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> activity_description_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,Integer> activity_id_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> activity_summary_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> activity_type_c;

	@FXML
	private Button add_new_task;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> address_line_1_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> address_line_2_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> city_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> company_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> contact_created_by_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,Timestamp> contact_created_date_and_time_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,Integer> contact_id_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> contact_source_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> country_c;

	@FXML
	private Button dashboard;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> description_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> email_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> fax_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> first_name_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> industry_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> job_title_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> last_name_c;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> phone_or_mobile_c;

	@FXML
	private Button search_tasks;
	
    @FXML
    private TextField search_box;

	@FXML
	private Button show_hide_columns_b;

	@FXML
	private TableColumn<ActivitiesHybridContacts,String> state_or_county_c;

	@FXML
	private TableView<ActivitiesHybridContacts> table_view;

	@FXML
	private Button view_details;


	@FXML
	void returnToDashBoard(ActionEvent event) {
		sceneManager.switchScene(event, "DashBoard");
	}

	private SceneManager sceneManager = new SceneManager();

	private ArrayList<ActivitiesHybridContacts> activitiesHybridContactsArray;
	private ObservableList<ActivitiesHybridContacts> activitiesHybridContactsObserve;
	private ObservableList<ActivitiesHybridContacts> selectedActivitiesHybridContacts;

	private TempDataDAO tempDataDAO = new TempDataDAO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ActivitiesDAO activitiesDAO = new ActivitiesDAO();
		activitiesHybridContactsArray = new ArrayList<>();
		activitiesHybridContactsArray = activitiesDAO.getAllActivitiesHybridContacts();
		activitiesHybridContactsObserve = FXCollections.observableArrayList(activitiesHybridContactsArray);

		this.activity_id_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,Integer>("activity_id")));
		this.contact_id_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,Integer>("contact_id")));
		this.activity_type_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("activity_type")));
		this.activity_summary_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("activity_summary")));
		this.activity_description_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("activity_description")));
		this.activity_created_by_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("activity_created_by")));
		this.activity_created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,Timestamp>("activity_created_date_and_time")));

		this.first_name_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("first_name")));
		this.last_name_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("last_name")));
		this.phone_or_mobile_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("phone_or_mobile")));
		this.email_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("email")));
		this.fax_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("fax")));
		this.address_line_1_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("address_line_1")));
		this.address_line_2_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("address_line_2")));
		this.city_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("city")));
		this.state_or_county_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("state_or_county")));
		this.country_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("country")));
		this.description_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("description")));
		this.industry_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("industry")));
		this.company_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("company")));
		this.job_title_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("job_title")));
		this.contact_created_by_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("created_by")));
		this.contact_created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,Timestamp>("created_date_and_time")));
		this.contact_source_c.setCellValueFactory((new PropertyValueFactory<ActivitiesHybridContacts,String>("contact_source")));

		
		this.table_view.setItems(activitiesHybridContactsObserve);

		this.hideUnnecessaryColumns(null);

		selectedActivitiesHybridContacts = table_view.getSelectionModel().getSelectedItems();

		selectedActivitiesHybridContacts.addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<? extends Object> arg0) {

			}

		});
		
		FilteredList<ActivitiesHybridContacts> filteredData = new FilteredList<>(activitiesHybridContactsObserve, b -> true);

		search_box.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(activitiesHybridContacts -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (activitiesHybridContacts.getFirst_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (activitiesHybridContacts.getActivity_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}  else if (activitiesHybridContacts.getActivity_summary().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}  else if (activitiesHybridContacts.getActivity_description().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(activitiesHybridContacts.getActivity_created_date_and_time()).indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (activitiesHybridContacts.getLast_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(activitiesHybridContacts.getActivity_id()).indexOf(lowerCaseFilter)!=-1) {
					return true;
				} else if (String.valueOf(activitiesHybridContacts.getContact_id()).indexOf(lowerCaseFilter)!=-1) {
					return true;
				} else if (activitiesHybridContacts.getPhone_or_mobile().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (activitiesHybridContacts.getFax().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (activitiesHybridContacts.getAddress_line_1().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getAddress_line_2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (activitiesHybridContacts.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getState_or_county().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}  else if (activitiesHybridContacts.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getIndustry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getCompany().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getJob_title().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} //else if (contacts.getCreated_by().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				//  return true;
				//} 
				else if (String.valueOf(activitiesHybridContacts.getCreated_date_and_time()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (activitiesHybridContacts.getContact_source().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else {  
					return false; // Does not match.
				}
			});
		});

		SortedList<ActivitiesHybridContacts> sortedData = new SortedList<>(filteredData);

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

	@FXML
	void modifyOrViewDetailActivity(ActionEvent event) {
		if (selectedActivitiesHybridContacts.size() == 0) {
			this.warningAlert("You haven't selected any activity!");
		} else {
			tempDataDAO.setCurrentContactID(selectedActivitiesHybridContacts.get(0).getActivity_id());
			sceneManager.switchScene(event, "ModifyActivity");
		}
	}

	@FXML
	void newActivity(ActionEvent event) {
		sceneManager.switchScene(event, "NewActivity");
	}

	private void warningAlert(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		alert.showAndWait();
		return;
	}





}
