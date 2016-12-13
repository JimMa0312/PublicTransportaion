package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import publicTransportaion.MainApp;
import publicTransportaion.model.User;
import publicTransportaion.model.en.GenderEnum;
import publicTransportaion.model.en.HintEnum;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.model.en.PatternEnum;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.util.Patterner;

public class TransationManageUserControl implements ControlledStage, Initializable {
	@SuppressWarnings("unused")
	private StageController myController;

	private ObservableList<User> users;

	@FXML
	private TableView<User> tableViewusers;
	@FXML
	private TableColumn<User, String> tableColumnUsersName;
	@FXML
	private TableColumn<User, String> tableColumnUserId;

	@FXML
	private TextField textFieldId;
	@FXML
	private TextField textFieldFirstName;
	@FXML
	private TextField textFieldSecondName;
	@FXML
	private TextField textFieldTel;
	@FXML
	private ChoiceBox<String> choiceBoxGender;
	@FXML
	private ChoiceBox<String> choiceBoxLimits;

	@FXML
	private Label labelErrorId;
	@FXML
	private Label labelErrorFirstName;
	@FXML
	private Label labelErrorSecondName;
	@FXML
	private Label labelErrorTel;

	private GenderEnum genderEnum;
	private Jurisdtion jurisdtion;

	public TransationManageUserControl() {
	}

	@FXML
	private void handleDeleteUsers() {
		int selectedIndex = tableViewusers.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			User user = users.get(selectedIndex);
			if (DeleteuserIformationWithSql(user.getUserId())) {
				tableViewusers.getItems().remove(selectedIndex);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("删除成功！");
				alert.setHeaderText("删除成功!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("数据库操作错误");
				alert.setHeaderText("数据库操作失误");
				alert.setContentText("数据库连接失败，或删除失败。\n请关闭界面稍后重试");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("删除错误");
			alert.setHeaderText("删除失败");
			alert.setContentText("请选择删除的对象");
			alert.showAndWait();
		}
	}

	@FXML
	private void handleSaveUser() {
		if (inInputValid()) {
			if (!isHadThisUserFrom()) {
				createNewUser();
			} else {
				editUser();
			}
		}
	}

	@FXML
	private void handleChangePassWord() {
		if (tableViewusers.getSelectionModel().getSelectedIndex() >= 0) {
			ChangePasswordControl.setUser(users.get(tableViewusers.getSelectionModel().getSelectedIndex()));

			MainApp.showChangePasswordView();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initErrorMessage();
		users = FXCollections.observableArrayList();
		choiceBoxGender.setItems(GenderEnum.getGenderList());
		initJur();
		connectAndSelectUserInfor();
		showUserDetails(null);

		tableViewusers.setItems(users);
		tableColumnUserId.setCellValueFactory(cellData -> cellData.getValue().getUserIdProperty());
		tableColumnUsersName.setCellValueFactory(cellData -> cellData.getValue().getSecondNameProperty());

		tableViewusers.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showUserDetails(newValue));

		choiceBoxGender.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			genderEnum = GenderEnum.valueOf(newValue.intValue() + 1);
			System.out.println(genderEnum);
		});

		choiceBoxLimits.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			jurisdtion = Jurisdtion.getJur(newValue.intValue() + 1);
			System.out.println(jurisdtion);
		});
	}

	@Override
	public void setStageController(StageController stageController) {
		myController = stageController;
	}

	private boolean inInputValid() {
		boolean isInputvalid = true;
		if (textFieldId.getText() == null || textFieldId.getText().isEmpty()) {
			isInputvalid = false;
			labelErrorId.setText(HintEnum.UserNameEmpty.getName());
		}
		if (textFieldId.getText().length() < 6 || textFieldId.getText().length() > 16) {
			isInputvalid = false;
			labelErrorId.setText(HintEnum.ErrorStringLegth.getName());
		}
		if (textFieldFirstName.getText() == null || textFieldFirstName.getText().isEmpty()) {
			isInputvalid = false;
			labelErrorFirstName.setText(HintEnum.EmptyFirstName.getName());
		}
		if (textFieldSecondName.getText() == null || textFieldSecondName.getText().isEmpty()) {
			isInputvalid = false;
			labelErrorSecondName.setText(HintEnum.EmptySecondName.getName());
		}
		if (textFieldTel.getText() == null || textFieldTel.getText().isEmpty()) {
			isInputvalid = false;
			labelErrorTel.setText(HintEnum.EmptyTel.getName());
		}
		if (!Patterner.StringMatch(PatternEnum.ChinsesTelStyle, textFieldTel.getText())) {
			isInputvalid = false;
			labelErrorTel.setText(HintEnum.UnCatchTel.getName());
		}
		return isInputvalid;
	}

	private void createNewUser() {
		if (MainApp.getJurisdtion().equals(Jurisdtion.admin) && CreateuserInformationSql()) {
			User user = new User();
			user.setUserId(textFieldId.getText());
			user.setPwd(User.encrytpMD5PWD("111111"));
			user.setFirstName(textFieldFirstName.getText());
			user.setSecondName(textFieldSecondName.getText());
			user.setTel(textFieldTel.getText());
			user.setControlLimit(jurisdtion);
			user.setGarde(genderEnum);

			users.add(user);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("提示！！");
			alert.setHeaderText("账号新建成功！");
			alert.setContentText("初始密码为\'111111\'\n请登录该账户，修改密码。");
			alert.showAndWait();
			initErrorMessage();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("错误!!");
			alert.setHeaderText("账号创建失败!");
			alert.setContentText("权限不够或者网络问题，请联系超级管理员");
			alert.showAndWait();
		}
	}

	private void editUser() {
		User user = users.get(tableViewusers.getSelectionModel().getFocusedIndex());
		if (UpdateUserInformationSql(user.getUserId())) {
			user.setUserId(textFieldId.getText());
			user.setFirstName(textFieldFirstName.getText());
			user.setSecondName(textFieldSecondName.getText());
			user.setTel(textFieldTel.getText());
			user.setControlLimit(jurisdtion);
			user.setGarde(genderEnum);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("提示！！");
			alert.setHeaderText("账号修改成功！");
			alert.showAndWait();
			initErrorMessage();
		}
	}

	private void initErrorMessage() {
		labelErrorId.setText("");
		labelErrorFirstName.setText("");
		labelErrorSecondName.setText("");
		labelErrorTel.setText("");
	}

	private void showUserDetails(User user) {
		if (user == null) {
			textFieldId.setText("");
			textFieldFirstName.setText("");
			textFieldSecondName.setText("");
			textFieldTel.setText("");
		} else {
			textFieldId.setText(user.getUserId());
			textFieldFirstName.setText(user.getFirstName());
			textFieldSecondName.setText(user.getSecondName());
			textFieldTel.setText(user.getTel());
			jurisdtion = user.getControlLimit();
			genderEnum = user.getGarde();
		}
	}

	private void connectAndSelectUserInfor() {
		try {
			SqlDeloy sqlDeloy = new SqlDeloy();
			Connection connection = sqlDeloy.getConnection();
			users.clear();
			Statement statement = connection.createStatement();
			String selectCommand = "Select * from admin_information";
			if (!MainApp.getJurisdtion().equals(Jurisdtion.admin)) {
				selectCommand += " where COntrol_Id='" + MainApp.getUserName() + "'";
			}
			ResultSet resultSet = statement.executeQuery(selectCommand);

			while (resultSet.next()) {
				User user = new User();
				System.out.println(resultSet.getString("COntrol_Id"));
				user.setUserId(resultSet.getString("COntrol_Id"));
				user.setPwd(resultSet.getString("Control_PWD"));
				user.setFirstName(resultSet.getString("Name"));
				user.setSecondName(resultSet.getString("Give_Name"));
				user.setControlLimit(Jurisdtion.getJur(resultSet.getInt("Control_Limit")));
				user.setGarde(GenderEnum.valueOf(resultSet.getInt("gender")));
				user.setTel(resultSet.getString("Tel"));

				users.add(user);
			}

			resultSet.close();
			statement.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean DeleteuserIformationWithSql(String id) {
		boolean isDeleted = false;
		try {
			SqlDeloy sqlDeloy = new SqlDeloy();
			Connection connection = sqlDeloy.getConnection();
			String deleteCommand = "Delete from admin_information where COntrol_Id=?";
			PreparedStatement pStmt = connection.prepareStatement(deleteCommand);
			pStmt.setString(1, id);
			int rtn = pStmt.executeUpdate();
			System.out.println(rtn);
			isDeleted = (rtn == 0) ? false : true;

			pStmt.close();
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	private boolean isHadThisUserFrom() {
		boolean ishad = false;
		try {
			SqlDeloy sqlDeloy = new SqlDeloy();
			Connection connection = sqlDeloy.getConnection();
			String sql = "select COntrol_Id from admin_information where COntrol_Id='" + textFieldId.getText() + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ishad = true;
				break;
			}
			resultSet.close();
			statement.close();
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ishad;
	}

	private boolean CreateuserInformationSql() {
		boolean isDone = false;
		try {
			SqlDeloy sqlDeloy = new SqlDeloy();
			Connection connection = sqlDeloy.getConnection();
			String createCommand = "INSERT INTO admin_information (Control_Id,Control_PWD,Control_Limit,Tel,Name,Give_Name,gender) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement PreStat = connection.prepareStatement(createCommand);
			PreStat.setString(1, textFieldId.getText());
			PreStat.setString(2, User.encrytpMD5PWD("111111"));
			PreStat.setInt(3, jurisdtion.getIndex());
			PreStat.setString(4, textFieldTel.getText());
			PreStat.setString(5, textFieldFirstName.getText());
			PreStat.setString(6, textFieldSecondName.getText());
			PreStat.setInt(7, genderEnum.getIndex());

			int row = PreStat.executeUpdate();
			if (row > 0) {
				isDone = true;
			}
			PreStat.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDone;
	}

	private boolean UpdateUserInformationSql(String ControlId) {
		boolean isDone = false;
		String upDateCommand = "UPDATE admin_information SET Control_Limit = ? ,Tel = ? ,Name = ? ,Give_Name = ? ,gender = ? WHERE COntrol_Id = ?";

		try {
			SqlDeloy sqlDeloy = new SqlDeloy();
			Connection connection = sqlDeloy.getConnection();
			PreparedStatement preStat = connection.prepareStatement(upDateCommand);
			preStat.setInt(1, jurisdtion.getIndex());
			preStat.setString(2, textFieldTel.getText());
			preStat.setString(3, textFieldFirstName.getText());
			preStat.setString(4, textFieldSecondName.getText());
			preStat.setInt(5, genderEnum.getIndex());
			preStat.setString(6, ControlId);
			int row = preStat.executeUpdate();
			if (row > 0) {
				isDone = true;
			}
			preStat.close();
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDone;
	}

	private void initJur() {
		switch (MainApp.getJurisdtion()) {
		case admin:
			choiceBoxLimits.setItems(Jurisdtion.getJurList());
			break;
		case manage:
			choiceBoxLimits.setItems(
					FXCollections.observableArrayList(Jurisdtion.manage.getName(), Jurisdtion.nomal.getName()));
			break;
		case nomal:
			textFieldId.setDisable(true);
			choiceBoxLimits.setDisable(true);
		}
	}
}
