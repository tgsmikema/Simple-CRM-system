package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SuccessfullyExportController {
	
	SceneManager sceneManager = new SceneManager();
	
	@FXML
	public void returnToDashboard(ActionEvent e) {
		sceneManager.switchScene(e, "Dashboard");
	}

}
