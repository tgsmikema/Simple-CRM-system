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

		//ContactsDAO contactsDAO = new ContactsDAO();
		//System.out.println(contactsDAO.addNewContact("asb","ddd",null,"email@gmail.com",null,null,null,null,null,null,null,null,null,null,"Mike Ma",Timestamp.valueOf("2021-03-04 12:23:00"),null));


		//ContactsDAO contactsDAO = new ContactsDAO();
		//System.out.println(contactsDAO.modifyContactFromID(15,"asb","Gao",null,"email@gmail.com",null,null,null,null,null,null,null,null,null,null,"Mike Ma",Timestamp.valueOf("2021-03-04 12:23:00"),null));

		//TempDataDAO t = new TempDataDAO();
		//t.setCurrentContactID(5);
		//t.setCurrentUserID(6);
		//t.resetData();
		
		
		/*
		Tasks contacts = new Tasks();
		
		Method m[] = (contacts.getClass().getDeclaredMethods());
		
		for (int i = 0; i < m.length; i++) {
            System.out.println(m[i].getName());
            System.out.println(m[i].getReturnType().getSimpleName());
			System.out.println();
		}
		
		System.out.println("-----------------------------------------------------------");
		
		Field f[] = (contacts.getClass().getDeclaredFields());
		
		for (int i = 0; i < f.length; i++) {
			System.out.print(f[i].getType().getSimpleName());
			System.out.print(" ");
            System.out.println(f[i].getName()+";");
            
		}
		*/
		
		
		Contacts contacts = new Contacts();
		Tasks tasks = new Tasks();
		Leads leads = new Leads();
		LeadsDAO leadsDAO = new LeadsDAO();
		LeadsHybridContacts l = new LeadsHybridContacts();
		PrintingFunctionModuleManagement pm = new PrintingFunctionModuleManagement();
		//Leads contacts = new Leads();
		
		//pm.printDataClassGettersAndSetters(contacts);
		//pm.printDataClassCreateHybrid(contacts,leads);
		
		//pm.printDAOClassHeaderStructure(tasks);
		//pm.printDAOClassGetAllDataIntoArrayList(leads);
		//pm.printDAOClassesGetSingleDataFromID(leads);
		//pm.printDAOClassCheckDataEntryExistInDB(leads, "contact_id");
		//pm.printDAOClassDeleteDataEntryFromID(leads);
		//pm.printDAOClassAddNewDataEntry(leads,"contact_id","lead_status,lead_created_by,lead_created_date_and_time");
		//pm.printDAOClassModifyDataFromID(leads,"lead_status,lead_created_by,lead_created_date_and_time");
		//pm.printDAOClassGetAllHybridData(l,leads,contacts);
		
		//pm.printHomeControllerInitialize(l,leadsDAO);
	
		//launch(args);
	}
}
