package publicTransportaion.view;

import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.omg.CosNaming.IstringHelper;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import publicTransportaion.MainApp;
import publicTransportaion.model.User;
import publicTransportaion.model.en.HintEnum;
import publicTransportaion.sql.SqlDeloy;
import sun.applet.Main;

public class ChangePasswordControl implements Initializable, ControlledStage {

	private StageController myController;

	@FXML
	private PasswordField passwordFieldOldPWD;
	@FXML
	private PasswordField passwordFieldNewPWD;
	@FXML
	private PasswordField passwordFieldCheckPWD;
	@FXML
	private Label labelErrorOldPWD;
	@FXML
	private Label labelErrorNewPWD;
	@FXML
	private Label labelErrorCheckPWD;

	private static User user;

	public ChangePasswordControl() {
	}

	@FXML
	private void handleExitChPWD() {
		user = null;
		myController.shutDownStage(MainApp.ChangePWD_stationId);
	}

	@FXML
	private void handleChangePwd() {
		if (user != null && inInputValid()) {
			if (updatePwdWithSQL()) {
				user.setPwd(User.encrytpMD5PWD(passwordFieldNewPWD.getText()));

				user = null;

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("提示！！");
				alert.setHeaderText("密码更改成功！！");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("错误！！");
				alert.setHeaderText("密码更改失败！！");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("错误！！");
			alert.setHeaderText("密码更改失败！！");
			alert.showAndWait();
		}

		handleExitChPWD();
	}

	@Override
	public void setStageController(StageController stageController) {
		myController = stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTextfield();
		initErrorMessage();
	}

	private void initErrorMessage() {
		labelErrorCheckPWD.setText("");
		labelErrorNewPWD.setText("");
		labelErrorOldPWD.setText("");
	}

	private void initTextfield() {
		passwordFieldCheckPWD.setText("");
		passwordFieldNewPWD.setText("");
		passwordFieldOldPWD.setText("");
	}

	private boolean inInputValid() {
		boolean isInputvalid = true;
		initErrorMessage();
		if (passwordFieldOldPWD.getText() == null || passwordFieldOldPWD.getText().isEmpty()) {
			isInputvalid = false;
			labelErrorOldPWD.setText(HintEnum.PwdEmpty.getName());
			if (user.getPwd().equals(User.encrytpMD5PWD(passwordFieldOldPWD.getText()))) {
				if (passwordFieldNewPWD.getText() == null || passwordFieldNewPWD.getText().isEmpty()) {
					isInputvalid = false;
					labelErrorNewPWD.setText(HintEnum.PwdEmpty.getName());
				}
				if (passwordFieldCheckPWD.getText() == null || passwordFieldCheckPWD.getText().isEmpty()) {
					isInputvalid = false;
					labelErrorCheckPWD.setText(HintEnum.PwdEmpty.getName());
				}
				if (!passwordFieldNewPWD.getText().equals(passwordFieldNewPWD.getText())) {
					isInputvalid = false;
					labelErrorCheckPWD.setText(HintEnum.ChPwdEmpty.getName());
				}
			} else {
				isInputvalid = false;
				labelErrorOldPWD.setText(HintEnum.ErrorPWD.getName());
			}
		}
		return isInputvalid;
	}

	private boolean updatePwdWithSQL() {
		boolean isDone = false;
		String updateCommand = "UPDATE admin_information SET Control_PWD = ? WHERE COntrol_Id = ?";

		try {
			SqlDeloy sqlDeloy;
			sqlDeloy = new SqlDeloy();
			Connection connection = new SqlDeloy().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateCommand);
			preparedStatement.setString(1, User.encrytpMD5PWD(passwordFieldNewPWD.getText()));
			preparedStatement.setString(2, user.getUserId());

			int row = preparedStatement.executeUpdate();

			if (row > 0) {
				isDone = true;
			}

			preparedStatement.close();
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDone;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		ChangePasswordControl.user = user;
	}
}
