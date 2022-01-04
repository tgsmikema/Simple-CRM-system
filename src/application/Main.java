package application;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
			//Parent root = FXMLLoader.load(getClass().getResource("ContactsHome.fxml"));
			Scene scene = new Scene(root,400,250);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		//ContactsDAO contactsDAO = new ContactsDAO();
		//System.out.println(contactsDAO.addNewContact("asb","ddd",null,"email@gmail.com",null,null,null,null,null,null,null,null,null,null,"Mike Ma",Timestamp.valueOf("2021-03-04 12:23:00"),null));


		//ContactsDAO contactsDAO = new ContactsDAO();
		//System.out.println(contactsDAO.modifyContactFromID(15,"asb","Gao",null,"email@gmail.com",null,null,null,null,null,null,null,null,null,null,"Mike Ma",Timestamp.valueOf("2021-03-04 12:23:00"),null));

		//TempDataDAO t = new TempDataDAO();
		//t.setCurrentContactID(5);
		//t.setCurrentUserID(6);
		//t.resetData();


		launch(args);
	}
}
