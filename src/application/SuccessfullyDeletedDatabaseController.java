package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfullyDeletedDatabaseController {

	
	SceneManager sceneManager = new SceneManager();
	
	public void returnToHome(ActionEvent e) {
		sceneManager.switchScene(e, "DashBoard");
	}
}
