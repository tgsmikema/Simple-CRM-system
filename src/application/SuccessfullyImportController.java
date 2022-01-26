package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SuccessfullyImportController {
	
	SceneManager sceneManager = new SceneManager();
	
	@FXML
	public void returnToDashboard(ActionEvent e) {
		sceneManager.switchScene(e, "DashBoard");
	}

}
