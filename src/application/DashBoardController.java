package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashBoardController implements Initializable{
	
	@FXML private Button contacts_b;
	
	@FXML private Label user_id_label;
	@FXML private Label user_name_label;
	
	private SceneManager sceneManager = new SceneManager();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void goToContactsHome(ActionEvent e) {
		sceneManager.switchScene(e, "ContactsHome");
	}
	
	/**
	 * this methods receive info from the previous scene
	 * @param userId
	 */
	public void setUserIdLabelText(String userId) {
		user_id_label.setText(userId);
	}
	
	/**
	 * this methods receive info from the previous scene
	 * @param userName
	 */
	public void setUserNameLabelText(String userName) {
		user_name_label.setText(userName);
	}

}
