package publicTransportaion.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sun.security.util.Password;

public class initAdminUserControl implements Initializable,ControlledStage {
	private StageController myController;
	
	@FXML
	private TextField textFieldUserName;
	@FXML
	private PasswordField textFieldUserPwd;
	@FXML
	private PasswordField textFieldCheckUserPwd;
	
	@FXML
	private Label labelErrorUserName;
	@FXML
	private Label labelErrorUserPwd;
	@FXML
	private Label labelErrorCheckUserPwd;
	
	@FXML
	private void handleCreateAdminUser(){
		
	}
	
	private void initErrorMessageLabel(){
		labelErrorCheckUserPwd.setText("");
		labelErrorUserName.setText("");
		labelErrorCheckUserPwd.setText("");
	}

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		myController=stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initErrorMessageLabel();
	}

}
