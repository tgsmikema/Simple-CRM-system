package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML private Button submitButton;
	@FXML private Button registerButton;
	@FXML private TextField email;
	@FXML private PasswordField password;

	private SceneManager sceneManager = new SceneManager();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	public void submit(ActionEvent e) throws SQLException {

		// new instance of login and loginDAO
		LoginDAO loginDAO = new LoginDAO();
		Login login = new Login();

		// get user input email and password
		String enterEmail = email.getText().toString();
		String enterPassword = password.getText().toString();

		// checking for empty fields of email and/or password, show alert if detected.
		if (enterEmail.equals("") || enterPassword.equals("")) {
			userOrPassEmpty();
			return;
		} 

		// checking that if user is exist in the database
		if (loginDAO.checkUserExist(enterEmail) == false) {
			userNotFound();
			return;
		}

		// injecting Login type field after executing mysql query
		login = loginDAO.getLogin(enterEmail);

		// checking if password the user entered is incorrect
		if (!login.getPassword().equals(enterPassword)) {
			wrongPassword();
			password.clear();
			return;
			// if everything entered correctly then proceed to the next step - dashboard
		} else {
			//sceneManager.switchScene(e, "DashBoard");
			try {
		           FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard.fxml"));
		           Parent root = loader.load();
		   
		           //The following both lines are the only addition we need to pass the arguments
		           DashBoardController dashBoardController = loader.getController();
		           dashBoardController.setUserIdLabelText(Integer.toString(login.getUser_id()));
		           dashBoardController.setUserNameLabelText(login.getFull_name());
		   
		           Stage stage = new Stage();
		           stage.setScene(new Scene(root));
		           stage.setTitle("Layout2 + Controller2");
		           stage.show();
		   
		       } catch (IOException e1) {
		           e1.printStackTrace();
		       }
		}

	}

	public void register(ActionEvent e) {
		sceneManager.switchScene(e, "Register");
	}

	private void userOrPassEmpty() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText("Email and/or password fields are empty");

		alert.showAndWait();
		return;
	}

	private void userNotFound() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText("User Not Found!");

		alert.showAndWait();
		return;
	}
	
	private void wrongPassword() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText("You have entered the wrong password!");

		alert.showAndWait();
		return;
	}


}
