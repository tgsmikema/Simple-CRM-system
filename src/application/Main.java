package application;

import java.sql.Connection;
import java.sql.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,400,250);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		
		//LoginDAO loginDAO = new LoginDAO();
		//loginDAO.register("707722327@qq.com", "123123", "Hi There", 1);
		//Login login = new Login();
		//login = loginDAO.getLogin("abc@gmail.com");
		
		//login.getEmail();
		
		launch(args);
	}
}
