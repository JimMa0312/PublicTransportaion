package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import publicTransportaion.MainApp;

public class OutLayerControl {
	
	private MainApp MainApp;
	
	public void setMainApp(MainApp mainApp) {
		MainApp = mainApp;
	}
	
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
}
