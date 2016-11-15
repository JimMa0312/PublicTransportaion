package publicTransportaion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import publicTransportaion.model.Cars;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.view.OutLayerControl;
import publicTransportaion.view.ShowBusInforMationController;
import publicTransportaion.view.StageController;

public class MainApp extends Application {

	private static StageController stageController;
	private Stage PrimaryStage;
	private BorderPane rootLayout;
	private OutLayerControl control;

	public static final String OutLayerId = "OutLayer";
	public static final String OutLayerRes = "view/OutLayer.fxml";

	public static final String ShowBusInforMationRes = "view/ShowBusInforMation.fxml";

	public static final String SignInId = "SignIn";
	public static final String SignInRes = "SignIn.fxml";

	public static final String TransationManangeId = "TransationManange";
	public static final String TransationManageRes = "TransationManage.fxml";
	
	
	public static final String TransationManage_BusDynamicId="TransationManage-BusDynamic";
	public static final String TranstationManage_BusDynamicRes="TransationManage-BusDynamic.fxml";
	
	public static final String TransationManage_BusLineId="TransationManage-BusLine";
	public static final String TransationManage_BusLineRes="TransationManage-BusLine.fxml";
	
	public static final String TransationManage_CarsId="TransationManage-Cars";
	public static final String TransationManage_CarsRes="TransationManage-Cars.fxml";
	
	public static final String TransationManage_StationId="TransationManage-Station";
	public static final String TransationManage_StationRes="TransationManage-Station.fxml";
	

	@Override
	public void start(Stage primaryStage) {
		stageController = new StageController();
		this.PrimaryStage = primaryStage;
		this.PrimaryStage.setTitle("公交车查询系统");
		stageController.setPrimaryStage(OutLayerId, primaryStage);

		initRootLayout();
		ShowBusInformationOverView();

		stageController.loadStage(SignInId, SignInRes, StageStyle.UNDECORATED);
		stageController.loadStage(TransationManage_BusDynamicId, TranstationManage_BusDynamicRes);
		

	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(OutLayerRes));
			rootLayout = (BorderPane) loader.load();

			this.control = new OutLayerControl();

			Scene scene = new Scene(rootLayout);
			PrimaryStage.setScene(scene);
			PrimaryStage.show();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void ShowBusInformationOverView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(ShowBusInforMationRes));
			AnchorPane busInformationDialog = (AnchorPane) loader.load();

			ShowBusInforMationController controller = new ShowBusInforMationController();
			controller.setMainApp(this);

			rootLayout.setCenter(busInformationDialog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setSignInProperty(){
		OutLayerControl.setSignin(true);
	}
	
	public static void showSignInView() {
		stageController.setStage(SignInId);
	}
	
	
	public static void showTransationManage_BusDyanmicView() {
		stageController.setStage(TransationManage_BusDynamicId);
	}
	
	public static void showTranastionManage_BusLineView() {
		stageController.loadStage(TransationManage_BusLineId, TransationManage_BusLineRes);
		stageController.setStage(TransationManage_BusLineId);
	}
	
	public static void showTransationManage_CarsView() {
		stageController.loadStage(TransationManage_CarsId, TransationManage_CarsRes);
		stageController.setStage(TransationManage_CarsId);
	}
	
	public static void showTransationManage_StationView() {
		stageController.loadStage(TransationManage_StationId, TransationManage_StationRes);
		stageController.setStage(TransationManage_StationId);
	}

//	public static void showTranstationManageView() {
//		stageController.setStage(TransationManangeId);
//	}

	public static void main(String[] args) {

		SqlDeloy testsqlDeloy = new SqlDeloy();
		testsqlDeloy.shotDownCon();

		launch(args);
	}
}
