package publicTransportaion.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import publicTransportaion.MainApp;
import publicTransportaion.model.DBConfig;

public class ConfigLayoutController implements Initializable, ControlledStage {

	@FXML
	private TextField uerNameTextField;
	@FXML
	private PasswordField userPassWordField;
	@FXML
	private TextField dataBaseTextField;
	@FXML
	private CheckBox CreateDataBaseCheckBox;
	@FXML
	private Label UserErrorMessageLabel;
	@FXML
	private Label PWDErrorMeesageLabel;
	@FXML
	private Label DBNameErrorMessageLabel;

	private StageController myController;

	public ConfigLayoutController() {
	}

	@Override
	public void setStageController(StageController stageController) {
		myController = stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initErrorMessage();
		initErrorMessageTextFill();
	}

	@FXML
	private void handleOk() {
		String userName = uerNameTextField.getText();
		String PWD = userPassWordField.getText();
		String DBName = dataBaseTextField.getText();

		if (isInputVlid()) {
			initErrorMessage();
			DBConfig.initDBConfig();
			DBConfig.setuserName(userName);
			DBConfig.setPWD(PWD);
			DBConfig.setDBname(DBName);

			MainApp.SaveXml();
			myController.shutDownStage(MainApp.ConfigLayout_stationId);
		}
	}

	private boolean isInputVlid() {
		boolean message = true;
		if (uerNameTextField.getText() == null && uerNameTextField.getText().isEmpty()) {
			UserErrorMessageLabel.setText("请输入数据库用户名");
			message = false;
		}
		if (userPassWordField.getText() == null && userPassWordField.getText().isEmpty()) {
			PWDErrorMeesageLabel.setText("请输入数据库口令");
			message = false;
		}
		if (dataBaseTextField.getText() == null && dataBaseTextField.getText().isEmpty()) {
			DBNameErrorMessageLabel.setText("请输入数据库名");
			message = false;
		}
		return message;
	}

	private void initErrorMessage() {
		UserErrorMessageLabel.setText(null);
		PWDErrorMeesageLabel.setText(null);
		DBNameErrorMessageLabel.setText(null);
	}

	private void initErrorMessageTextFill() {
		UserErrorMessageLabel.setTextFill(Color.RED);
		PWDErrorMeesageLabel.setTextFill(Color.RED);
		DBNameErrorMessageLabel.setTextFill(Color.RED);
	}

	@FXML
	private void handleReset() {
		uerNameTextField.setText(null);
		userPassWordField.setText(null);
		dataBaseTextField.setText(null);
	}
}
