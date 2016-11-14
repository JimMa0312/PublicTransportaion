package publicTransportaion.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import publicTransportaion.MainApp;

public class OutLayerControl implements ControlledStage, Initializable {
	
	private StageController myController;
	private BooleanProperty isSignIn=new SimpleBooleanProperty(false);
	
	
	@FXML
	private void initialize(){
		
	}
	@FXML
	private void handleExite(){
		System.exit(0);
	}
	
	@FXML
	private void ShowAbout(){
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("关于");
		alert.setHeaderText("开发小组：黑耀开发");
		alert.setContentText("项目组长：马玉琛\n项目成员：王瀚、甘孟坤、何佳东\n\n软件版本：DateV 0.5.0");
		alert.showAndWait();
	}
	@FXML
	private void ShowSignIn(){
		MainApp.showSignInView();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		myController=stageController;
	}
	
	public void goToMain(){
		myController.setStage(MainApp.OutLayerId);
	}
}
