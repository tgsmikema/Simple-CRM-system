package application;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.*;
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

		Contacts contacts = new Contacts();
		Tasks tasks = new Tasks();
		//Leads leads = new Leads();
		//LeadsDAO leadsDAO = new LeadsDAO();
		//TasksDAO tasksDAO = new TasksDAO();
		//TasksHybridContacts th = new TasksHybridContacts();
		//LeadsHybridContacts l = new LeadsHybridContacts();
		//PrintingFunctionModuleManagement pm = new PrintingFunctionModuleManagement();
		//Leads contacts = new Leads();
		//pm.printGetTextFXML(tasks);
		
		//pm.printDataClassGettersAndSetters(tasks);
		//pm.printDataClassCreateHybrid(tasks,contacts);
		
		//pm.printDAOClassHeaderStructure(tasks);
		//pm.printDAOClassGetAllDataIntoArrayList(tasks);
		//pm.printDAOClassesGetSingleDataFromID(tasks);
		//pm.printDAOClassCheckDataEntryExistInDB(tasks, "task_id");
		//pm.printDAOClassDeleteDataEntryFromID(tasks);
		
		
		//pm.printDAOClassAddNewDataEntry(tasks,"task_id","task_id,contact_id,task_type,task_created_by,task_created_date_and_time");
		//pm.printDAOClassModifyDataFromID(tasks,"task_type,task_created_by,task_created_date_and_time");
		//pm.printDAOClassGetAllHybridData(th,tasks,contacts);
		
		//pm.printHomeControllerInitialize(th,tasksDAO);
		
		
	
		launch(args);
	}
}
