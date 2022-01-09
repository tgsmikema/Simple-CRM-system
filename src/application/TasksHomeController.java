package application;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class TasksHomeController implements Initializable{

	@FXML
	private Button add_new_task;

	@FXML
	private TableColumn<TasksHybridContacts, String> address_line_1_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> address_line_2_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> city_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> company_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> contact_created_by_c;

	@FXML
	private TableColumn<TasksHybridContacts, Timestamp> contact_created_date_and_time_c;

	@FXML
	private TableColumn<TasksHybridContacts, Integer> contact_id_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> contact_source_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> country_c;

	@FXML
	private Button dashboard;

	@FXML
	private TableColumn<TasksHybridContacts, String> description_c;

	@FXML
	private TableColumn<TasksHybridContacts, Timestamp> due_date_and_time_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> email_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> fax_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> first_name_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> industry_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> job_title_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> last_name_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> phone_or_mobile_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> priority_c;

	@FXML
	private TableColumn<TasksHybridContacts, Integer> progress_c;

	@FXML
	private Button search_tasks;

	@FXML
	private Button show_hide_columns_b;

	@FXML
	private TableColumn<TasksHybridContacts, String> state_or_county_c;

	@FXML
	private TableView<TasksHybridContacts> table_view;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_assigned_to_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_created_by_c;

	@FXML
	private TableColumn<TasksHybridContacts, Timestamp> task_created_date_and_time_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_current_status_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_description_c;

	@FXML
	private TableColumn<TasksHybridContacts, Integer> task_id_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_summary_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_type_c;

	@FXML
	private Button view_details;

	@FXML
	void modifyOrViewDetailLead(ActionEvent event) {

	}

	@FXML
	void newTask(ActionEvent event) {
		sceneManager.switchScene(event, "NewTask");
	}

	@FXML
	void returnToDashBoard(ActionEvent event) {
		sceneManager.switchScene(event, "Dashboard");
	}


	private SceneManager sceneManager = new SceneManager();

	private ArrayList<TasksHybridContacts> tasksHybridContactsArray;
	private ObservableList<TasksHybridContacts> tasksHybridContactsObserve;
	private ObservableList<TasksHybridContacts> selectedTasksHybridContacts;

	private TempDataDAO tempDataDAO = new TempDataDAO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		TasksDAO tasksDAO = new TasksDAO();
		tasksHybridContactsArray = new ArrayList<>();
		tasksHybridContactsArray = tasksDAO.getAllTasksHybridContacts();
		tasksHybridContactsObserve = FXCollections.observableArrayList(tasksHybridContactsArray);

		this.contact_id_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Integer>("contact_id")));
		this.task_id_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Integer>("task_id")));
		this.task_type_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_type")));
		this.task_summary_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_summary")));
		this.task_description_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_description")));
		this.task_created_by_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_created_by")));
		this.task_created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Timestamp>("task_created_date_and_time")));
		this.task_assigned_to_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_assigned_to")));
		this.due_date_and_time_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Timestamp>("due_date_and_time")));
		this.priority_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("priority")));
		this.progress_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Integer>("progress")));
		this.task_current_status_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_current_status")));
		this.first_name_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("first_name")));
		this.last_name_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("last_name")));
		this.phone_or_mobile_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("phone_or_mobile")));
		this.email_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("email")));
		this.fax_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("fax")));
		this.address_line_1_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("address_line_1")));
		this.address_line_2_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("address_line_2")));
		this.city_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("city")));
		this.state_or_county_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("state_or_county")));
		this.country_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("country")));
		this.description_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("description")));
		this.industry_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("industry")));
		this.company_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("company")));
		this.job_title_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("job_title")));
		this.contact_created_by_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("created_by")));
		this.contact_created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Timestamp>("created_date_and_time")));
		this.contact_source_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("contact_source")));

		this.table_view.setItems(tasksHybridContactsObserve);

		selectedTasksHybridContacts = table_view.getSelectionModel().getSelectedItems();

		//hide unnecessary columns
		this.hideUnnecessaryColumns(null);

		selectedTasksHybridContacts.addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<? extends Object> arg0) {
				//System.out.println(selectedTasksHybridContacts.get(0).getFirst_name().toString());
			}

		});

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




}
