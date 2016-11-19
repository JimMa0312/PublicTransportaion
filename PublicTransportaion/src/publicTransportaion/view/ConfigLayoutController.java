package publicTransportaion.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import publicTransportaion.model.DBConfig;

public class ConfigLayoutController implements Initializable, ControlledStage {
	
	@FXML
	private TextField uerNameTextField;
	@FXML
	private PasswordField userPassWordField;
	@FXML
	private TextField dataBaseTextField;
	@FXML
	private Label UserErrorMessageLabel;
	@FXML
	private Label PWDErrorMeesageLabel;
	@FXML
	private Label DBNameErrorMessageLabel;
	
	private StageController myController;

	public ConfigLayoutController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setStageController(StageController stageController) {
		myController=stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initErrorMessage();
		initErrorMessageTextFill();
	}
	
	@FXML
	private void handleOk(){
		String userName=uerNameTextField.getText();
		String PWD=userPassWordField.getText();
		String DBName=dataBaseTextField.getText();
		
		if (isInputVlid()) {
			DBConfig.setUserName(userName);
			DBConfig.setPWD(PWD);
			DBConfig.setDBName(DBName);
		}
	}
	
	private boolean isInputVlid(){
		boolean message=true;
		if (uerNameTextField.getText()==null&&uerNameTextField.getText().isEmpty()) {
			message = false;
		}
		if (userPassWordField.getText()==null&&userPassWordField.getText().isEmpty()) {
			message=false;
		}
		if (dataBaseTextField.getText()==null&&dataBaseTextField.getText().isEmpty()) {
			message=false;
		}
		return message;
	}
	
	private void initErrorMessage(){
		UserErrorMessageLabel.setText(null);
		PWDErrorMeesageLabel.setText(null);
		DBNameErrorMessageLabel.setText(null);
	}
	
	private void initErrorMessageTextFill(){
		UserErrorMessageLabel.setTextFill(Color.RED);
		PWDErrorMeesageLabel.setTextFill(Color.RED);
		DBNameErrorMessageLabel.setTextFill(Color.RED);
	}
	
	@FXML
	private void handleReset(){
		uerNameTextField.setText(null);
		userPassWordField.setText(null);
		dataBaseTextField.setText(null);
	}
}
