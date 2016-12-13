package publicTransportaion.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import publicTransportaion.MainApp;
import publicTransportaion.model.User;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.sql.SqlDeloy;

public class SignInController implements ControlledStage {
	@FXML
	private TextField Control_Id_TextField;
	@FXML
	private PasswordField Control_PWD_PasswordField;
	@FXML
	private Label returnMessage;

	private StageController myController;

	@FXML
	private void initialize() {
		returnMessage.setText(null);
	}

	@FXML
	private void handleOk() {

		if (isInputValid()) {
			if (NTLM()) {
				returnMessage.setText("登陆成功");
				returnMessage.setTextFill(Color.GREEN);
				initTextField();
				myController.shutDownStage(MainApp.SignInId);

				OutLayerControl.setSignin(true);
			} else {
				returnMessage.setText("账号或密码错误！");
				returnMessage.setTextFill(Color.RED);
			}
		}
	}

	private boolean NTLM() {
		boolean isOk = false;
		String sql = "select Control_PWD,COntrol_Id,Control_Limit from admin_information where Control_Id='"
				+ Control_Id_TextField.getText() + "'";

		try {
			SqlDeloy sqlDeloy = new SqlDeloy();
			Statement stmt = sqlDeloy.getConnection().createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				if (resultSet.getString("Control_PWD")
						.equals(User.encrytpMD5PWD(Control_PWD_PasswordField.getText()))) {
					System.out.println("Success!");
					OutLayerControl.setSignin(true);
					MainApp.setUserName(resultSet.getString("COntrol_Id"));
					MainApp.setJurisdtion(Jurisdtion.getJur(resultSet.getInt("Control_Limit")));
					System.out.println(MainApp.getJurisdtion());
					isOk = true;
				}
			}
			resultSet.close();
			stmt.close();
			sqlDeloy.shotDownCon();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOk;
	}

	@FXML
	private void handleCancel() {
		initTextField();
		myController.shutDownStage(MainApp.SignInId);
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		boolean isInputvalid = true;

		if (Control_Id_TextField.getText() == null || Control_Id_TextField.getText().length() == 0) {
			returnMessage.setText("请输入账户");
			returnMessage.setTextFill(Color.RED);
			isInputvalid = false;
		}
		if (Control_PWD_PasswordField.getText() == null || Control_PWD_PasswordField.getText().length() == 0) {
			returnMessage.setText("请输入密码");
			returnMessage.setTextFill(Color.RED);
			isInputvalid = false;
		}

		return isInputvalid;

	}

	private void initTextField() {
		Control_Id_TextField.setText(null);
		Control_PWD_PasswordField.setText(null);
		returnMessage.setText(null);
	}

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		this.myController = stageController;
	}

	public void goToMain() {
		myController.setStage(MainApp.SignInId);
	}
}
