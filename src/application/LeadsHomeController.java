package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LeadsHomeController implements Initializable {

    @FXML
    private Button add_new_lead;

    @FXML
    private TableColumn<?, ?> address_line_1_c;

    @FXML
    private TableColumn<?, ?> address_line_2_c;

    @FXML
    private TableColumn<?, ?> assigned_to_c;

    @FXML
    private TableColumn<?, ?> city_c;

    @FXML
    private TableColumn<?, ?> company_c;

    @FXML
    private TableColumn<?, ?> contact_created_by_c;

    @FXML
    private TableColumn<?, ?> contact_created_date_and_time_c;

    @FXML
    private TableColumn<?, ?> contact_id_c;

    @FXML
    private TableColumn<?, ?> contact_source_c;

    @FXML
    private TableColumn<?, ?> country_c;

    @FXML
    private Button dashboard;

    @FXML
    private Button delete_lead;

    @FXML
    private TableColumn<?, ?> description_c;

    @FXML
    private TableColumn<?, ?> email_c;

    @FXML
    private TableColumn<?, ?> fax_c;

    @FXML
    private TableColumn<?, ?> first_name_c;

    @FXML
    private TableColumn<?, ?> if_lost_reasons_c;

    @FXML
    private TableColumn<?, ?> industry_c;

    @FXML
    private TableColumn<?, ?> job_title_c;

    @FXML
    private TableColumn<?, ?> last_name_c;

    @FXML
    private TableColumn<?, ?> lead_created_by_c;

    @FXML
    private TableColumn<?, ?> lead_created_date_c;

    @FXML
    private TableColumn<?, ?> lead_source_c;

    @FXML
    private TableColumn<?, ?> lead_status_c;

    @FXML
    private TableColumn<?, ?> phone_or_mobile_c;

    @FXML
    private Button search_lead;

    @FXML
    private TableColumn<?, ?> state_or_county_c;

    @FXML
    private TableView<?> table_view;

    @FXML
    private Button view_details;

    @FXML
    void deleteSelectedLead(ActionEvent event) {

    }

    @FXML
    void modifyContact(ActionEvent event) {

    }

    @FXML
    void newLead(ActionEvent event) {

    }

    @FXML
    void returnToDashBoard(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		//hide unnecessary columns
		this.hideUnnecessaryColumns(null);
		
	}
	
	@FXML
	public void hideUnnecessaryColumns(ActionEvent event) {
		fax_c.setVisible(false);
		address_line_1_c.setVisible(false);
		address_line_2_c.setVisible(false);
		city_c.setVisible(false);
		state_or_county_c.setVisible(false);
		country_c.setVisible(false);
		description_c.setVisible(false);
		industry_c.setVisible(false);
		company_c.setVisible(false);
		job_title_c.setVisible(false);
		contact_created_by_c.setVisible(false);
		contact_created_date_and_time_c.setVisible(false);
		contact_source_c.setVisible(false);
	}
	
	@FXML
	public void showUnnecessaryColumns(ActionEvent event) {
		fax_c.setVisible(true);
		address_line_1_c.setVisible(true);
		address_line_2_c.setVisible(true);
		city_c.setVisible(true);
		state_or_county_c.setVisible(true);
		country_c.setVisible(true);
		description_c.setVisible(true);
		industry_c.setVisible(true);
		company_c.setVisible(true);
		job_title_c.setVisible(true);
		contact_created_by_c.setVisible(true);
		contact_created_date_and_time_c.setVisible(true);
		contact_source_c.setVisible(true);

	}

}
