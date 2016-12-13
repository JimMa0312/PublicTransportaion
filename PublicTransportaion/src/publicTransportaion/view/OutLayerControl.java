package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import publicTransportaion.MainApp;
import publicTransportaion.model.Cars;
import publicTransportaion.model.Station;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.sql.SqlDeloy;
import sun.applet.Main;

public class OutLayerControl implements ControlledStage, Initializable {

	private StageController myController;
	private static BooleanProperty isSignIn = new SimpleBooleanProperty(false);

	@FXML
	private MenuItem signInMenuItem;
	@FXML
	private MenuItem signOutMenuItem;
	@FXML
	private MenuItem CarsManageMenuItem;
	@FXML
	private MenuItem StationManageMenuItem;
	@FXML
	private MenuItem BusLineManageMenuItem;
	@FXML
	private MenuItem BusDyanmicMenuItem;
	@FXML
	private MenuItem UserMessageMenuItem;

	@FXML
	private void handleExite() {
		System.exit(0);
	}

	@FXML
	private void ShowAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("关于");
		alert.setHeaderText("开发小组：黑石开发");
		alert.setContentText("项目组长：马玉琛\n项目成员：王瀚、甘孟坤、王婷\n\n软件版本：A0.5.1");
		alert.showAndWait();
	}

	@FXML
	private void ShowSignIn() {
		MainApp.showSignInView();
	}

	@FXML
	private void HandleSignOut() {
		System.out.println("Singout seccess");
		isSignIn.set(false);
	}

	@FXML
	private void handleShowCarManager() {
		MainApp.showTransationManage_CarsView();
	}

	@FXML
	private void handleShowBusLineManager() {
		MainApp.showTranastionManage_BusLineView();
	}

	@FXML
	private void handleShowStationManager() {
		MainApp.showTransationManage_StationView();
	}

	@FXML
	private void handleShowBusDyanmicManager() {
		MainApp.showTransationManage_BusDyanmicView();
	}

	@FXML
	private void handleShowUserManager() {
		MainApp.showTranastionManage_UserView();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (isNullWithUser()) {
			MainApp.showInitAdminUserView();
		}
		isSignIn.addListener(ov -> {
			if (isSignIn.getValue()) {
				signInMenuItem.setDisable(true);
				signOutMenuItem.setDisable(false);
				CarsManageMenuItem.setDisable(false);
				StationManageMenuItem.setDisable(false);
				BusLineManageMenuItem.setDisable(false);
				BusDyanmicMenuItem.setDisable(false);
				UserMessageMenuItem.setDisable(false);
			} else {
				signInMenuItem.setDisable(false);
				signOutMenuItem.setDisable(true);
				CarsManageMenuItem.setDisable(true);
				StationManageMenuItem.setDisable(true);
				BusLineManageMenuItem.setDisable(true);
				BusDyanmicMenuItem.setDisable(true);
				UserMessageMenuItem.setDisable(true);
			}
		});
	}

	@Override
	public void setStageController(StageController stageController) {
		myController = stageController;
	}

	public void goToMain() {
		myController.setStage(MainApp.OutLayerId);
	}

	public static void setSignin(boolean signin) {
		isSignIn.set(signin);
	}

	private boolean isNullWithUser() {
		boolean isHaveUser = false;

		try {
			SqlDeloy sqlDeloy = new SqlDeloy();
			Statement statement = sqlDeloy.getConnection().createStatement();

			String sql = "select count(*) as num from admin_information";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("num"));
				if (resultSet.getInt("num") == 0) {
					isHaveUser = true;
				}
			}
			resultSet.close();
			statement.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isHaveUser;
	}
}
