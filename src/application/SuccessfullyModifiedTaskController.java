package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class SuccessfullyModifiedTaskController implements Initializable{

	@FXML
	private Button button;

	@FXML
	private Label label;



	SceneManager sceneManager = new SceneManager();

	public void returnToTasksHome(ActionEvent e) {
		sceneManager.switchScene(e, "TasksHome");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//label.setVisible(false);
		button.setVisible(true);
		
	}

}
