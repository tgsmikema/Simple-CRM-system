package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class RegisterController implements Initializable{

	@FXML private Button cancelAndReturn;
	@FXML private Button registerNow;

	@FXML private RadioButton authLevel1;
	@FXML private RadioButton authLevel2;
	@FXML private ToggleGroup auth_level;

	@FXML private TextField email;
	@FXML private TextField fullName;
	@FXML private PasswordField password;

	private int DEFAULT_AUTH_LEVEL = 2;

	private SceneManager sceneManager = new SceneManager();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}

	public void submitRegister(ActionEvent e) {

		// new instance of login and loginDAO
		LoginDAO loginDAO = new LoginDAO();
		Login login = new Login();

		// get user input email,password,fullName and authLevel
		String enterEmail = email.getText().toString();
		String enterPassword = password.getText().toString();
		String enterFullName = fullName.getText().toString();
		int enterAuthLevel = DEFAULT_AUTH_LEVEL;
		if (authLevel1.isSelected()) {
			enterAuthLevel = 1;
		} else if(authLevel2.isSelected()) {
			enterAuthLevel = 2;
		}
		
		// checking if any field is empty and issue warning.
		if (enterEmail.equals("") || enterPassword.equals("") || enterFullName.equals("")) {
			fieldEmptyWarning();
			return;
		} 
		
		// checking if email field does not contain @ symbol.
		if (!enterEmail.contains("@")) {
			emailWrongFormat();
			return;
		}
		
		// checking if email registered are dupilcate and already exist in the database
		if (loginDAO.checkUserExist(enterEmail)) {
			duplicateEntry();
			return;
		}
		
		try {
			loginDAO.register(enterEmail, enterPassword, enterFullName, enterAuthLevel);
			sceneManager.switchScene(e, "SuccessfullyRegistered");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void cancel(ActionEvent e) {
		sceneManager.switchScene(e, "Login");
	}

	private void fieldEmptyWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText("One or more fields are empty, please check!");

		alert.showAndWait();
		return;
	}
	
	private void emailWrongFormat() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText("The email you entered has a wrong format, please check and enter again!");

		alert.showAndWait();
		return;
	}
	
	private void duplicateEntry() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText("The email you entered has already been registered, please log in");

		alert.showAndWait();
		return;
	}


}
