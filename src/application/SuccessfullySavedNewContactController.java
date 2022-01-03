package application;

import javafx.event.ActionEvent;

public class SuccessfullySavedNewContactController {

	SceneManager sceneManager = new SceneManager();

	public void returnToContactsHome(ActionEvent e) {
		sceneManager.switchScene(e, "ContactsHome");
	}

}
