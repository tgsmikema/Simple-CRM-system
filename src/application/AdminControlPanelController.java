package application;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AdminControlPanelController {
	
	LoginDAO loginDAO = new LoginDAO();
	SceneManager sceneManager = new SceneManager();

	@FXML
	void deleteAllLogin(ActionEvent event) {

		boolean deleteConfirmation = this.confirmationAlert("Are You Sure that you want to delete ALL USERs from database?");

		if(deleteConfirmation) {
			loginDAO.deleteAllUsers();
			sceneManager.switchScene(event, "Login");
		}

	}
	
	@FXML
	void returnToLogin(ActionEvent event) {
		
		sceneManager.switchScene(event, "Login");
		
	}
	
	private boolean confirmationAlert(String message) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(message);

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;

	}
}
