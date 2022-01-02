package application;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContactsHomeController implements Initializable {


	//injectable tableview

	@FXML
	private TableView<Contacts> table_view;
	
    @FXML
    private TableColumn<Contacts, String> address_line_1_c;

    @FXML
    private TableColumn<Contacts, String> address_line_2_c;

    @FXML
    private TableColumn<Contacts, String> city_c;

    @FXML
    private TableColumn<Contacts, String> company_c;

    @FXML
    private TableColumn<Contacts, Integer> contact_id_c;

    @FXML
    private TableColumn<Contacts, String> contact_source_c;

    @FXML
    private TableColumn<Contacts, String> created_by_c;

    @FXML
    private TableColumn<Contacts, Timestamp> created_date_and_time_c;

    @FXML
    private TableColumn<Contacts, String> description_c;

    @FXML
    private TableColumn<Contacts, String> email_c;

    @FXML
    private TableColumn<Contacts, String> fax_c;

    @FXML
    private TableColumn<Contacts, String> first_name_c;

    @FXML
    private TableColumn<Contacts, String> industry_c;

    @FXML
    private TableColumn<Contacts, String> job_title_c;

    @FXML
    private TableColumn<Contacts, String> last_name_c;

    @FXML
    private TableColumn<Contacts, String> phone_or_mobile_c;

    @FXML
    private TableColumn<Contacts, String> state_or_county_c;
    
    @FXML
    private TableColumn<Contacts,String> country_c;

    // injectable buttons
    @FXML
    private Button view_details;
    
    @FXML
    private Button add_new_contacts;
    
    @FXML
    private Button search_contacts;
    
    @FXML
    private Button dashboard;
    
    // new sceneManager instance
    private SceneManager sceneManager = new SceneManager();
    
    private ArrayList<Contacts> contactsArray;
    private ObservableList<Contacts> contactsObserve;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// initiate contactsDAO type;
		ContactsDAO contactsDAO = new ContactsDAO();
		// new arraylist for contacts data
		contactsArray = new ArrayList<>();
		// inject all contacts data from MySQL thru ContactsDAO methods into Arraylist
		contactsArray = contactsDAO.getAllContacts();
		// inject all contacts data Arraylist into Observable arraylist
		contactsObserve = FXCollections.observableArrayList(contactsArray);
		
		// display observable list items into tableview
		this.contact_id_c.setCellValueFactory((new PropertyValueFactory<Contacts,Integer>("contact_id")));
		this.first_name_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("first_name")));
		this.last_name_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("last_name")));
		this.phone_or_mobile_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("phone_or_mobile")));
		this.email_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("email")));
		this.fax_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("fax")));
		this.address_line_1_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("address_line_1")));
		this.address_line_2_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("address_line_2")));
		this.city_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("city")));
		this.state_or_county_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("state_or_county")));
		this.country_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("country")));
		this.description_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("description")));
		this.industry_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("industry")));
		this.company_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("company")));
		this.job_title_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("job_title")));
		this.created_by_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("created_by")));
		this.created_date_and_time_c.setCellValueFactory((new PropertyValueFactory<Contacts,Timestamp>("created_date_and_time")));
		this.contact_source_c.setCellValueFactory((new PropertyValueFactory<Contacts,String>("contact_source")));
		this.table_view.setItems(contactsObserve);
		
		//table_view.getSelectionModel().setCellSelectionEnabled(true);
		ObservableList<Contacts> selectedContact = table_view.getSelectionModel().getSelectedItems();
		
		selectedContact.addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<? extends Object> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}
	
	public void returnToDashBoard(ActionEvent e) {
		
	}
    
    
    

}
