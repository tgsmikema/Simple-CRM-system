package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RegisterController implements Initializable{

	@FXML private Button cancelAndReturn;
	@FXML private Button registerNow;

	private SceneManager sceneManager = new SceneManager();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	public void cancel(ActionEvent e) {
		sceneManager.switchScene(e, "Login");
	}
	
	
	
	
}
