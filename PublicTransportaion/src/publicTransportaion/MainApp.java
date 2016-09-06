package publicTransportaion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.view.OutLayerControl;
import publicTransportaion.view.ShowBusInforMationController;
import publicTransportaion.view.SignInController;
import publicTransportaion.view.StageController;
import publicTransportaion.view.TransationManageController;

public class MainApp extends Application {
	
	private static StageController stageController;
	private Stage PrimaryStage;
	private BorderPane rootLayout;
	
	public static String OutLayerId="OutLayer";
	public static String OutLayerRes="view/OutLayer.fxml";
	
	public static String ShowBusInforMationRes="view/ShowBusInforMation.fxml";
	
	public static String SignInId="SignIn";
	public static String SignInRes="SignIn.fxml";
	

	@Override
	public void start(Stage primaryStage) {
		stageController=new StageController();
		this.PrimaryStage=primaryStage;
		this.PrimaryStage.setTitle("公交车查询系统");
		stageController.setPrimaryStage(OutLayerId, primaryStage);
		
		
		initRootLayout();
		ShowBusInformationOverView();
		
		stageController.loadStage(SignInId, SignInRes);
		

	}
	
	private void initRootLayout() {
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(OutLayerRes));
			rootLayout=(BorderPane) loader.load();
			
			OutLayerControl control=new OutLayerControl();
			
			Scene scene=new Scene(rootLayout);
			PrimaryStage.setScene(scene);
			PrimaryStage.show();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void ShowBusInformationOverView(){
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(ShowBusInforMationRes));
			AnchorPane busInformationDialog=(AnchorPane) loader.load();
			
			ShowBusInforMationController controller=new ShowBusInforMationController();
			controller.setMainApp(this);
			
			rootLayout.setCenter(busInformationDialog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void showSignInView(){
		stageController.setWaitStage(SignInId);
	}

	
	public void showTranstationManage(){
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/TransationManage.fxml"));
			AnchorPane page=(AnchorPane)loader.load();
			
			Stage dialogStage=new Stage();
			dialogStage.setTitle("公交车管理系统");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(PrimaryStage);
			
			TransationManageController controller=new TransationManageController();
			controller.setMainApp(this);
			
			Scene scene=new Scene(page);
			dialogStage.setScene(scene);
			
			dialogStage.showAndWait();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		SqlDeloy testsqlDeloy=new SqlDeloy();
		testsqlDeloy.shotDownCon();
		
		launch(args);
	}
}
