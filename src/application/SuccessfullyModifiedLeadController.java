package application;

import javafx.event.ActionEvent;

public class SuccessfullyModifiedLeadController {
	
	SceneManager sceneManager = new SceneManager();

	public void returnToLeadsHome(ActionEvent e) {
		sceneManager.switchScene(e, "LeadsHome");
	}

}
