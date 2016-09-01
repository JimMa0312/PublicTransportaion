package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditStationInformationController {
	 @FXML
	    private TextField Station_Name_TextField;
	    @FXML
	    private TextField Station_ID_TextField;
	    @FXML
	    private TextField Station_Address_TextField;
	    @FXML
	    private TextField Station_GPS_TextField;



	    private Stage dialogStage;

	    private boolean okClicked = false;

	   
	    @FXML
	    private void initialize() {
	    }

	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk() {
	       
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     * 
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (Station_Name_TextField.getText() == null || Station_Name_TextField.getText().length() == 0) {
	            errorMessage += "No valid Station_Name!\n"; 
	        }
	        if (Station_ID_TextField.getText() == null || Station_ID_TextField.getText().length() == 0) {
	            errorMessage += "No valid Station_ID!\n"; 
	        }
	        if (Station_Address_TextField.getText() == null || Station_Address_TextField.getText().length() == 0) {
	            errorMessage += "No valid Station_Address!\n"; 
	        }

	        if (Station_GPS_TextField.getText() == null || Station_GPS_TextField.getText().length() == 0) {
	            errorMessage += "No valid Station_GPS!\n"; 
	        } 

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {

	        	Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("Invalid Fields");
				alert.setHeaderText("Please correct invalid fields");
				alert.setContentText(errorMessage);
				alert.showAndWait();
				return false;
	        }
	    }

}
