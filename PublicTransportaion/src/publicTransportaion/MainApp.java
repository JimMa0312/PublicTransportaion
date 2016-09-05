package publicTransportaion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.view.OutLayerControl;
import publicTransportaion.view.ShowBusInforMationController;
import publicTransportaion.view.SignInController;
import publicTransportaion.view.StageController;
import publicTransportaion.view.TransationManageController;

public class MainApp extends Application {
	
	private StageController stageController;
	private Stage PrimaryStage;
	private BorderPane rootLayout;
	
	public static String OutLayerId="OutLayer";
	public static String OutLayerRes="view/OutLayer.fxml";
	

	@Override
	public void start(Stage primaryStage) {
		stageController=new StageController();
		this.PrimaryStage=primaryStage;
		this.PrimaryStage.setTitle("公交车查询系统");
		
		initRootLayout();
		ShowBusInformationOverView();
		
		stageController.addStage(OutLayerId, primaryStage);
	}
	
	private void initRootLayout() {
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(OutLayerRes));
			rootLayout=(BorderPane) loader.load();
			
			OutLayerControl control=new OutLayerControl();
			control.setMainApp(this);
			
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
			loader.setLocation(MainApp.class.getResource("view/ShowBusInforMation.fxml"));
			AnchorPane busInformationDialog=(AnchorPane) loader.load();
			
			ShowBusInforMationController controller=new ShowBusInforMationController();
			controller.setMainApp(this);
			
			rootLayout.setCenter(busInformationDialog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 有bug，进程无法创建
	 */
	public boolean showSignInView() {
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SignIn.fxml"));
			AnchorPane page=(AnchorPane)loader.load();
			
			Stage stage=new Stage();
			stage.setTitle("登陆");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(PrimaryStage);
			Scene scene=new Scene(page);
			stage.setScene(scene);
			
			SignInController controller=new SignInController();
			controller.setDialogStage(stage);
			
			stage.showAndWait();
			
			
			return controller.isOkClicked();

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
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
