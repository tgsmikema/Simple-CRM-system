package application;

import javafx.event.ActionEvent;

public class SuccessfullyModifiedTaskController {
	
	SceneManager sceneManager = new SceneManager();

	public void returnToTasksHome(ActionEvent e) {
		sceneManager.switchScene(e, "TasksHome");
	}

}
