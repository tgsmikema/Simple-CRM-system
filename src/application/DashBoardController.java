package application;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashBoardController implements Initializable{

	@FXML
	private Button contacts_b;

	@FXML
	private Label logged_in_as_t;

	@FXML
	private TableView<TasksHybridContacts> table_view;

	@FXML
	private TableColumn<TasksHybridContacts, Timestamp> task_due_date_and_time_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_summary_c;

	@FXML
	private TableColumn<TasksHybridContacts, String> task_type_c;

	@FXML
	private TableColumn<TasksHybridContacts, Integer> task_id_c;

	@FXML
	private Label todays_date_t;

	private ArrayList<TasksHybridContacts> tasksHybridContactsArray;
	private ObservableList<TasksHybridContacts> tasksHybridContactsObserve;
	private ObservableList<TasksHybridContacts> selectedTasksHybridContacts;

	private TempDataDAO tempDataDAO = new TempDataDAO();
	private LoginDAO loginDAO = new LoginDAO();


	private SceneManager sceneManager = new SceneManager();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		TasksDAO tasksDAO = new TasksDAO();
		tasksHybridContactsArray = new ArrayList<>();
		tasksHybridContactsArray = tasksDAO.getAllTasksHybridContacts();
		tasksHybridContactsObserve = FXCollections.observableArrayList(tasksHybridContactsArray);


		this.task_id_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Integer>("task_id")));
		this.task_type_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_type")));
		this.task_summary_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,String>("task_summary")));
		this.task_due_date_and_time_c.setCellValueFactory((new PropertyValueFactory<TasksHybridContacts,Timestamp>("due_date_and_time")));

		this.table_view.setItems(tasksHybridContactsObserve);

		table_view.getSortOrder().add(task_due_date_and_time_c);


		selectedTasksHybridContacts = table_view.getSelectionModel().getSelectedItems();


		selectedTasksHybridContacts.addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<? extends Object> arg0) {
				//System.out.println(selectedTasksHybridContacts.get(0).getFirst_name().toString());
			}

		});

		//get local time and display on top
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		String localTime = dtf.format(now).toString();

		todays_date_t.setText(localTime);

		//get who logged in as current user
		logged_in_as_t.setText(loginDAO.getLoginFromID(tempDataDAO.getCurrentUserID()).getFull_name());

	}

	@FXML
	void deleteDatabase(ActionEvent event) {
		sceneManager.switchScene(event, "deleteDatabase");
	}

	@FXML
	void logOut(ActionEvent event) {
		boolean logout = this.confirmationAlert("Are You Sure that you want to log out?");
		
		if (logout) {
			sceneManager.switchScene(event, "Login");
		}
	}

	@FXML
	void modifyOrViewDetailLead(ActionEvent event) {
		if (selectedTasksHybridContacts.size() == 0) {
			this.warningAlert("You haven't selected any task!");
		} else {
			tempDataDAO.setCurrentContactID(selectedTasksHybridContacts.get(0).getTask_id());
			sceneManager.switchScene(event, "ModifyTask");
		}
	}

	private void warningAlert(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		alert.showAndWait();
		return;
	}

	public void goToContactsHome(ActionEvent e) {
		sceneManager.switchScene(e, "ContactsHome");
	}

	@FXML
	public void goToLeadsHome(ActionEvent e) {
		sceneManager.switchScene(e, "LeadsHome");
	}

	@FXML
	public void goToTasksHome(ActionEvent e) {
		sceneManager.switchScene(e, "TasksHome");
	}

	@FXML
	public void goToActivitiesHome(ActionEvent e) {
		sceneManager.switchScene(e, "ActivitiesHome");
	}

	@FXML
	public void importExportBackupFiles(ActionEvent e) {
		sceneManager.switchScene(e, "ImportExportBackupFile");
	}
	
	private boolean confirmationAlert(String message) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;

	}



}
