package publicTransportaion;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import publicTransportaion.model.DBConfig;
import publicTransportaion.model.DBconfigXml;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.view.OutLayerControl;
import publicTransportaion.view.ShowBusInforMationController;
import publicTransportaion.view.StageController;

public class MainApp extends Application {

	private static StageController stageController;
	private Stage PrimaryStage;
	private BorderPane rootLayout;
	private OutLayerControl control;

	private static String UserName;
	private static Jurisdtion jurisdtion;

	private static String path = "config.xml";
	private static File file;

	public static final String OutLayerId = "OutLayer";
	public static final String OutLayerRes = "view/OutLayer.fxml";

	public static final String ShowBusInforMationRes = "view/ShowBusInforMation.fxml";

	public static final String SignInId = "SignIn";
	public static final String SignInRes = "SignIn.fxml";

	public static final String TransationManangeId = "TransationManange";
	public static final String TransationManageRes = "TransationManage.fxml";

	public static final String TransationManage_BusDynamicId = "TransationManage-BusDynamic";
	public static final String TranstationManage_BusDynamicRes = "TransationManage-BusDynamic.fxml";

	public static final String TransationManage_BusLineId = "TransationManage-BusLine";
	public static final String TransationManage_BusLineRes = "TransationManage-BusLine.fxml";

	public static final String TransationManage_CarsId = "TransationManage-Cars";
	public static final String TransationManage_CarsRes = "TransationManage-Cars.fxml";

	public static final String TransationManage_StationId = "TransationManage-Station";
	public static final String TransationManage_StationRes = "TransationManage-Station.fxml";

	public static final String TransationManage_UserId = "TransationManage-User";
	public static final String TransationManage_UserRes = "TransationManage-User.fxml";

	public static final String ConfigLayout_stationId = "ConfigLayout";
	public static final String ConfigLayout_stationRes = "ConfigLayout.fxml";

	public static final String InitAdminUser_stationId = "InitAdminUser";
	public static final String initAdminUser_stationRes = "InitAdminUser.fxml";

	public static final String ChangePWD_stationId = "ChangePwd";
	public static final String ChangePWD_stationRes = "ChangePwd.fxml";

	@Override
	public void start(Stage primaryStage) {
		stageController = new StageController();
		this.PrimaryStage = primaryStage;
		this.PrimaryStage.setTitle("公交车查询系统");
		stageController.setPrimaryStage(OutLayerId, primaryStage);
		LoadXml();

		initRootLayout();
		ShowBusInformationOverView();

		stageController.loadStage(SignInId, SignInRes, StageStyle.UNDECORATED);
		stageController.loadStage(TransationManage_BusDynamicId, TranstationManage_BusDynamicRes);
		primaryStage.setOnCloseRequest(e->System.exit(0));
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

	public void setSignInProperty() {
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

	public static void showTranastionManage_UserView() {
		stageController.loadStage(TransationManage_UserId, TransationManage_UserRes);
		stageController.setStage(TransationManage_UserId);
	}

	public static void showConfigLayoutView() {
		stageController.loadStage(ConfigLayout_stationId, ConfigLayout_stationRes, StageStyle.UNDECORATED);
		stageController.setWaitStage(ConfigLayout_stationId);
	}

	public static void showInitAdminUserView() {
		stageController.loadStage(InitAdminUser_stationId, initAdminUser_stationRes, StageStyle.UNDECORATED);
		stageController.setWaitStage(InitAdminUser_stationId);
	}

	public static void showChangePasswordView() {
		stageController.loadStage(ChangePWD_stationId, ChangePWD_stationRes);
		stageController.setWaitStage(ChangePWD_stationId);
	}

	/*
	 * 保存数据库的加密配置文件
	 * 
	 * @param file
	 * 
	 * @return null
	 */
	public static void saveDBConfigToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(DBconfigXml.class);

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			DBconfigXml dbConfig = new DBconfigXml();
			dbConfig.LoadDBconfig();
			marshaller.marshal(dbConfig, file);
		} catch (JAXBException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cloud not save data to File:\n");
			alert.setContentText(file.getPath());
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 读取数据库加密配置文件
	 * 
	 * @param file return null
	 */
	public static void loadFileToDBConfig(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(DBconfigXml.class);

			Unmarshaller unmarshaller = context.createUnmarshaller();

			DBConfig.initDBConfig();
			DBconfigXml dbConfig = (DBconfigXml) unmarshaller.unmarshal(file);

			dbConfig.WriteDBconfig();
		} catch (JAXBException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cloud not load File to Object:\n");
			alert.setContentText(file.getPath() + "\n" + e.getMessage());
			alert.showAndWait();
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 保存为
	 * 
	 * @return null
	 */
	public static void SaveXml() {
		file = new File(path);
		saveDBConfigToFile(file);
	}

	public static void LoadXml() {
		file = new File(path);
		if (!file.exists()) {
			showConfigLayoutView();
		}
		loadFileToDBConfig(file);
		int i = 0;
		while (!isConnected() && i < 4) {
			i++;
			showConfigLayoutView();
		}
	}

	private static boolean isConnected() {
		boolean isConnecte = true;
		SqlDeloy sqlDeloy = null;
		try {
			sqlDeloy = new SqlDeloy();
		} catch (Exception e) {
			isConnecte = false;
		}
		sqlDeloy.shotDownCon();
		return isConnecte;
	}

	public static String getUserName() {
		return UserName;
	}

	public static void setUserName(String userName) {
		UserName = userName;
	}

	public static Jurisdtion getJurisdtion() {
		return jurisdtion;
	}

	public static void setJurisdtion(Jurisdtion jurisdtion) {
		MainApp.jurisdtion = jurisdtion;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
