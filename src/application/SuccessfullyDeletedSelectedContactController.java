package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfullyDeletedSelectedContactController {

	
	SceneManager sceneManager = new SceneManager();
	
	public void returnToContactsHome(ActionEvent e) {
		sceneManager.switchScene(e, "ContactsHome");
	}
}
