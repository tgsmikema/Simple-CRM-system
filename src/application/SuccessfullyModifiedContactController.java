package application;

import javafx.event.ActionEvent;

public class SuccessfullyModifiedContactController {
	
	SceneManager sceneManager = new SceneManager();

	public void returnToContactsHome(ActionEvent e) {
		sceneManager.switchScene(e, "ContactsHome");
	}

}
