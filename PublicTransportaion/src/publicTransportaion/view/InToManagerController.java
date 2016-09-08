package publicTransportaion.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import publicTransportaion.MainApp;

public class InToManagerController implements ControlledStage, Initializable {
	
	private StageController myController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		this.myController=stageController;
	}
	
	@FXML
	private void handleClose(){
		myController.shutDownStage(MainApp.InToManagerViewId);
	}
	
	@FXML
	private void handleShowCarManager(){
		MainApp.showTransationManage_CarsView();
	}
	
	@FXML
	private void handleShowBusLineManager(){
		MainApp.showTranastionManage_BusLineView();
	}
	
	@FXML
	private void handleShowStationManager(){
		MainApp.showTransationManage_StationView();
	}
	
	@FXML
	private void handleShowBusDyanmicManager(){
		MainApp.showTransationManage_BusDyanmicView();
	}

}
