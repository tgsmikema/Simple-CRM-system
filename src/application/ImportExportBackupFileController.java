package application;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ImportExportBackupFileController {
	
	CSVImportAndExportUtil csvUtil = new CSVImportAndExportUtil();
	SceneManager sceneManager = new SceneManager();

    @FXML
    void exportFile(ActionEvent event) {
    	csvUtil.exportContactsToCSV();
    	csvUtil.exportLeadsToCSV();
    	csvUtil.exportTasksToCSV();
    	csvUtil.exportActivitiesToCSV();
    	sceneManager.switchScene(event, "SuccessfullyExport");
    	
    }

    @FXML
    void importFile(ActionEvent event) {
    	try {
			csvUtil.importAllData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	sceneManager.switchScene(event, "SuccessfullyImport");

    }
    
    @FXML
    void returnToDashboard(ActionEvent event) {
    	sceneManager.switchScene(event, "Dashboard");
    }

}
