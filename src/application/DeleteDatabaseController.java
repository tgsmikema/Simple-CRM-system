package application;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class DeleteDatabaseController {

	SceneManager s = new SceneManager();

	ContactsDAO contactsDAO = new ContactsDAO();
	LeadsDAO leadsDAO = new LeadsDAO();
	TasksDAO tasksDAO = new TasksDAO();
	ActivitiesDAO activitiesDAO = new ActivitiesDAO();

	@FXML
	void deleteActivities(ActionEvent event) {
		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete all activities from database?");

		if(deleteConfirmation) {
			activitiesDAO.deleteAllActivities();
		} else {
			return;
		}
		
	}

	@FXML
	void deleteAll(ActionEvent event) {
		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete EVERYTHING from database?");
		
		if(deleteConfirmation) {
			contactsDAO.deleteAllContacts();
			leadsDAO.deleteAllLeads();
			tasksDAO.deleteAllTasks();
			activitiesDAO.deleteAllActivities();
			s.switchScene(event, "SuccessfullyDeletedDatabase");
		} else {
			return;
		}
		
	}

	@FXML
	void deleteContacts(ActionEvent event) {
		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete all contacts from database?");

		if(deleteConfirmation) {
			contactsDAO.deleteAllContacts();
		} else {
			return;
		}

	}

	@FXML
	void deleteLeads(ActionEvent event) {

		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete all leads from database?");

		if(deleteConfirmation) {
			leadsDAO.deleteAllLeads();
		} else {
			return;
		}
	}

	@FXML
	void deleteTasks(ActionEvent event) {
		
		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete all tasks from database?");

		if(deleteConfirmation) {
			tasksDAO.deleteAllTasks();
		} else {
			return;
		}
	}

	@FXML
	void returnToDashboard(ActionEvent event) {
		s.switchScene(event, "Dashboard");
	}

	private boolean confirmationAlert(String message) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;

	}

}
