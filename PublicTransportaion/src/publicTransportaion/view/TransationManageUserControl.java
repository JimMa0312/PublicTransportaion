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
import publicTransportaion.model.User;
import publicTransportaion.model.en.GenderEnum;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.sql.SqlDeloy;

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
		// TODO Auto-generated constructor stub
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
		if (isHadThisUserFrom()) {
			createNewUser();
		} else {
			editUser();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initErrorMessage();
		users = FXCollections.observableArrayList();
		connectAndSelectUserInfor();
		choiceBoxGender.setItems(GenderEnum.getGenderList());
		choiceBoxLimits.setItems(Jurisdtion.getJurList());
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

	private void createNewUser() {
		if (CreateuserInformationSql()) {
			User user=new User();
			user.setUserId(textFieldId.getText());
		}
	}

	private void editUser() {

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
		}
	}

	private void connectAndSelectUserInfor() {
		SqlDeloy sqlDeloy = new SqlDeloy();
		try {
			Connection connection = sqlDeloy.getConnection();
			users.clear();
			Statement statement = connection.createStatement();
			String selectCommand = "Select * from admin_information";
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

				users.add(user);
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlDeloy.shotDownCon();
	}

	private boolean DeleteuserIformationWithSql(String id) {
		boolean isDeleted = false;
		SqlDeloy sqlDeloy = new SqlDeloy();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isDeleted;
	}

	private boolean isHadThisUserFrom() {
		boolean ishad = false;
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();
		String sql = "select COntrol_Id from admin_information where COntrol_Id='" + textFieldId + "'";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ishad = true;
				break;
			}
			resultSet.close();
			statement.close();
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ishad;
	}
	
	private boolean CreateuserInformationSql(){
		boolean isDone=false;
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		String createCommand="INSERT INTO admin_information (Control_Id,Control_PWD,Control_Limit,Tel,Name,Give_Name,gender) VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement PreStat=connection.prepareStatement(createCommand);
			PreStat.setString(1, textFieldId.getText());
			PreStat.setString(2, User.encrytpMD5PWD("111111"));
			PreStat.setInt(3, jurisdtion.getIndex());
			PreStat.setString(4, textFieldTel.getText());
			PreStat.setString(5, textFieldFirstName.getText());
			PreStat.setString(6, textFieldSecondName.getText());
			PreStat.setInt(7, genderEnum.getIndex());
			
			int row=PreStat.executeUpdate();
			if (row>0) {
				isDone=true;
			}
			PreStat.close();
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDone;
	}
	
	private boolean UpdateUserInformationSql(){
		boolean isDone=false;
		
		return isDone;
	}
}
