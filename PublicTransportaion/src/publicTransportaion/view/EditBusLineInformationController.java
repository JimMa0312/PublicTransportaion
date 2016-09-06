package publicTransportaion.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import publicTransportaion.MainApp;

public class EditBusLineInformationController implements ControlledStage, Initializable {
	@FXML
	private TextField Bus_id;
	@FXML
	private TextField Time_lag;
	@FXML
	private HBox Time_start;
	@FXML
	private HBox Time_end;
	
	private StageController myController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		this.myController=stageController;
	}
	
	public void goToMain(){
		myController.setStage(MainApp.EditBusLineInformationId);
	}
}
