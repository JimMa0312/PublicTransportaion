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
import publicTransportaion.view.StageController;
import publicTransportaion.view.TransationManageController;

public class MainApp extends Application {

	private static StageController stageController;
	private Stage PrimaryStage;
	private BorderPane rootLayout;

	public static final String OutLayerId = "OutLayer";
	public static final String OutLayerRes = "view/OutLayer.fxml";

	public static final String ShowBusInforMationRes = "view/ShowBusInforMation.fxml";

	public static final String SignInId = "SignIn";
	public static final String SignInRes = "SignIn.fxml";

	public static final String TransationManangeId = "TransationManange";
	public static final String TransationManageRes = "TransationManage.fxml";
	
	public static String EditBusLineInformationId="EditBusLineInformation";
	public static String EditBusLineInformationRes="EditBusLineInformation.fxml";

	@Override
	public void start(Stage primaryStage) {
		stageController = new StageController();
		this.PrimaryStage = primaryStage;
		this.PrimaryStage.setTitle("公交车查询系统");
		stageController.setPrimaryStage(OutLayerId, primaryStage);

		initRootLayout();
		ShowBusInformationOverView();

		stageController.loadStage(SignInId, SignInRes, StageStyle.UNDECORATED);
		stageController.loadStage(TransationManangeId, TransationManageRes);
		stageController.loadStage(EditBusLineInformationId, EditBusLineInformationRes, StageStyle.UNDECORATED);

	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(OutLayerRes));
			rootLayout = (BorderPane) loader.load();

			@SuppressWarnings("unused")
			OutLayerControl control = new OutLayerControl();

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
	
	public static void showSignInView() {
		stageController.setStage(SignInId);
	}

	public static void showTranstationManageView() {
		stageController.setStage(TransationManangeId);
	}

	public static void main(String[] args) {

//		SqlDeloy testsqlDeloy = new SqlDeloy();
//		testsqlDeloy.shotDownCon();

		launch(args);
	}
}
