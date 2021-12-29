package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfullyRegisteredController {

	@FXML private Button registerNow;
	
	SceneManager sceneManager = new SceneManager();
	
	public void login(ActionEvent e) {
		sceneManager.switchScene(e, "Login");
	}
}
