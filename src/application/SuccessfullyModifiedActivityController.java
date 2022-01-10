package application;

import javafx.event.ActionEvent;

public class SuccessfullyModifiedActivityController {
	
	SceneManager sceneManager = new SceneManager();

	public void returnToActivitiesHome(ActionEvent e) {
		sceneManager.switchScene(e, "ActivitiesHome");
	}

}
