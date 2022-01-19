package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DatabaseSettingController implements Initializable {

    @FXML
    private TextField db_name;

    @FXML
    private TextField password;

    @FXML
    private TextField server_name;

    @FXML
    private TextField user_name;
    
    @FXML
    private Label current_url;

    @FXML
    private Label current_user;

    
    SceneManager sm = new SceneManager();
    
    
    @FXML
    private void save(ActionEvent e) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("jdbc:mysql://");
    	sb.append(server_name.getText());
    	sb.append(":3306/");
    	sb.append(db_name.getText());
    	
    	DataBaseConnectionCredentials.URL = sb.toString();
    	
    	DataBaseConnectionCredentials.username = user_name.getText();
    	DataBaseConnectionCredentials.password = password.getText();
    	sm.switchScene(e, "Login");
    	//System.out.println(sb);
    	
    	
    }
    
    @FXML
    private void cancel(ActionEvent e) {
    	sm.switchScene(e, "Login");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		current_url.setText(DataBaseConnectionCredentials.URL);
		current_user.setText(DataBaseConnectionCredentials.username);
		
	}
    
}
