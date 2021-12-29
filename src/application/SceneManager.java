package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	private Stage stage;
	private Scene scene;
	
	public void switchScene(ActionEvent e, String sceneName) {
		try {
			String path = String.format("%s.fxml",sceneName);
			
			Parent root = FXMLLoader.load(getClass().getResource(path));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show(); 
			
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
}