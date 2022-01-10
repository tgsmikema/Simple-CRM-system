package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfullyDeletedSelectedTaskController {

	
	SceneManager sceneManager = new SceneManager();
	
	public void returnToTasksHome(ActionEvent e) {
		sceneManager.switchScene(e, "TasksHome");
	}
}
