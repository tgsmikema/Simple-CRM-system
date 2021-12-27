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
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		launch(args);

		String URL = "jdbc:mysql://127.0.0.1:3306/sql_store";
		String uname = "root";
		String pass = "masiqi93";
		String query = "SELECT * FROM customers";

		Connection con = DriverManager.getConnection(URL, uname, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			String first_name = rs.getString("first_name");
			System.out.println(first_name);
		}

		st.close();
		con.close();


	}
}
