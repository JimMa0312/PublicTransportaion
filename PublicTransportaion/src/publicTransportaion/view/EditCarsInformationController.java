package publicTransportaion.view;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import publicTransportaion.model.Cars;


public class EditCarsInformationController implements ControlledStage {

    @FXML
    private TextField License_Plate_TextField;
    @FXML
    private TextField Engine_id_TextField;
    @FXML
    private TextField Frame_id_TextField;
    @FXML
    private TextField Bus_type_TextField;
    @FXML
    private TextField Car_population_TextField;
    @FXML
    private TextField Bus_chair_TextField;



    private Stage dialogStage;
    private Cars cars;
    private boolean okClicked = false;
    
    @SuppressWarnings("unused")
	private StageController myController;

   
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setCars(Cars cars) {
        this.cars = cars;

        License_Plate_TextField.setText(cars.getLicensePlate());
        Engine_id_TextField.setText(cars.getEingeId());
        Frame_id_TextField.setText(cars.getFrameId());
        Bus_type_TextField.setText(cars.getBusType());
        Car_population_TextField.setText(Integer.toString(cars.getCarPopulation()));
        Bus_chair_TextField.setText(Integer.toString(cars.getBusChair()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	if (isInputValid()) {
            cars.setLicensePlate(License_Plate_TextField.getText());
            cars.setEingeId(Engine_id_TextField.getText());
            cars.setFrameId(Frame_id_TextField.getText());
            cars.setBusType(Bus_type_TextField.getText());
            cars.setCarPopulation(Car_population_TextField.getText());
            cars.setBusChair(Bus_chair_TextField.getText());

            okClicked = true;
            dialogStage.close();
        }
       
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    //测试车牌照格式是否正确
    public boolean CheckLicense_Plate(String patString){
    	String re="/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/";
    	Pattern pattern = Pattern.compile(re,Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(patString);
    	
    	return matcher.matches();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (License_Plate_TextField.getText() == null || License_Plate_TextField.getText().length() == 0) {
            errorMessage += "No valid License_Plate!\n"; 
        }
        if (!CheckLicense_Plate(License_Plate_TextField.getText())) {
			errorMessage+="车牌号格式错误！\n";
		}
        if (Engine_id_TextField.getText() == null || Engine_id_TextField.getText().length() == 0) {
            errorMessage += "No valid Engine_id!\n"; 
        }
        if (Frame_id_TextField.getText() == null || Frame_id_TextField.getText().length() == 0) {
            errorMessage += "No valid Frame_id!\n"; 
        }

        if (Bus_type_TextField.getText() == null || Bus_type_TextField.getText().length() == 0) {
            errorMessage += "No valid Bus_type!\n"; 
        } 

        if (Car_population_TextField.getText() == null || Car_population_TextField.getText().length() == 0) {
            errorMessage += "No valid Can_population!\n"; 
        }else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(Car_population_TextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Can_population (must be an integer)!\n"; 
            }
        }

        if (Bus_chair_TextField.getText() == null || Bus_chair_TextField.getText().length() == 0) {
            errorMessage += "No valid Bus_chair!\n";
        }else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(Bus_chair_TextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Bus_chair (must be an integer)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
        }
    }

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		myController=stageController;
	}
}