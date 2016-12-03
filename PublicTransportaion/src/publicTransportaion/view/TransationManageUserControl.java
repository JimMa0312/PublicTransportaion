package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import publicTransportaion.MainApp;
import publicTransportaion.model.User;
import publicTransportaion.model.en.GenderEnum;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.sql.SqlDeloy;
import sun.applet.Main;

public class TransationManageUserControl implements ControlledStage,Initializable {
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

	public TransationManageUserControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBoxGender.setItems(GenderEnum.getGenderList());
		choiceBoxLimits.setItems(Jurisdtion.getJurList());
		users=FXCollections.observableArrayList();
		
		connectAndSelectUserInfor();
	}

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		myController=stageController;	
	}
	
	private void showUserDetails(User user) {
		if (user==null) {
			
		} else {

		}
	}
	
	private void connectAndSelectUserInfor(){
		
		try {
			SqlDeloy sqlDeloy=new SqlDeloy();
			Connection connection=sqlDeloy.getConnection();
			users.clear();
			Statement statement=connection.createStatement();
			String selectCommand="Select * from Bus_information";
			ResultSet resultSet=statement.executeQuery(selectCommand);
			
			while(resultSet.next()){
				User user=new User();
				user.setUserId(resultSet.getString("COntrol_Id"));
				user.setPwd(resultSet.getString("Control_PWD"));
				user.setFirstName(resultSet.getString("Name"));
				user.setSecondName(resultSet.getString("Give_Name"));
				user.setControlLimit(Jurisdtion.getJur(resultSet.getInt("Control_Limit")));
				user.setGarde(GenderEnum.valueOf(resultSet.getInt("gender")));
			}
			
			resultSet.close();
			statement.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
