package publicTransportaion.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import publicTransportaion.MainApp;

public class OutLayerControl implements ControlledStage, Initializable {
	
	private StageController myController;
	
	
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
//		Stage stage=new Stage();
//		try {
//			FXMLLoader loader=new FXMLLoader();
//			loader.setLocation(getClass().getResource(MainApp.SignInRes));
//			AnchorPane pane=(AnchorPane)loader.load();
//			
//			Scene scene=new Scene(pane);
//			stage.setTitle("登陆");
//			stage.setScene(scene);
//			
//			SignInController controller=new SignInController();
//			controller.setStage(stage);
//			
//			stage.showAndWait();
//		} catch (IOException e) {
//			// TODO: handle exception\
//			e.printStackTrace();
//		}
		
		//myController.setWaitStage(MainApp.OutLayerId);
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
