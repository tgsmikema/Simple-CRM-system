package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfullyDeletedSelectedActivityController {

	
	SceneManager sceneManager = new SceneManager();
	
	public void returnToActivitiesHome(ActionEvent e) {
		sceneManager.switchScene(e, "ActivitiesHome");
	}
}
