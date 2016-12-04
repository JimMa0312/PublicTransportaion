package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import publicTransportaion.MainApp;
import publicTransportaion.model.User;
import publicTransportaion.model.en.GenderEnum;
import publicTransportaion.model.en.HintEnum;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.sql.SqlDeloy;
import sun.applet.Main;
import sun.security.util.Password;

public class initAdminUserControl implements Initializable,ControlledStage {
	private StageController myController;
	
	@FXML
	private TextField textFieldUserName;
	@FXML
	private PasswordField textFieldUserPwd;
	@FXML
	private PasswordField textFieldCheckUserPwd;
	
	@FXML
	private Label labelErrorUserName;
	@FXML
	private Label labelErrorUserPwd;
	@FXML
	private Label labelErrorCheckUserPwd;
	
	@FXML
	private void handleCreateAdminUser(){
		if (checkLegal()) {
			initErrorMessageLabel();
			if (inSertIntoUserAdmin()) {
				initAllTextField();
				myController.shutDownStage(MainApp.InitAdminUser_stationId);
			}
		}
	}
	
	private boolean checkLegal(){
		boolean isLegal=true;
		initErrorMessageLabel();
		if (textFieldUserName.getText().isEmpty()||textFieldUserName.getText()==null) {
			isLegal=false;
			labelErrorUserName.setText(HintEnum.UserNameEmpty.getName());
		}
		if (textFieldUserName.getText().length()<6||textFieldUserName.getText().length()>16) {
			isLegal=false;
			labelErrorUserName.setText("用户名"+HintEnum.ErrorStringLegth.getName());
		}
		if (textFieldUserPwd.getText().isEmpty()||textFieldUserPwd.getText()==null) {
			isLegal=false;
			labelErrorUserPwd.setText(HintEnum.PwdEmpty.getName());
		}
		if (textFieldUserPwd.getText().length()<6||textFieldUserPwd.getText().length()>16) {
			isLegal=false;
			labelErrorUserPwd.setText("密码"+HintEnum.ErrorStringLegth.getName());
		}
		if (textFieldUserPwd.getText().equals(textFieldUserName.getText())) {
			isLegal=false;
			labelErrorUserPwd.setText(HintEnum.CommeBetweenUserNameAndPWD.getName());
		}
		if (textFieldCheckUserPwd.getText().isEmpty()||textFieldCheckUserPwd.getText()==null) {
			isLegal=false;
			labelErrorCheckUserPwd.setText(HintEnum.PwdEmpty.getName());
		}
		if (!textFieldCheckUserPwd.getText().equals(textFieldUserPwd.getText())) {
			isLegal=false;
			labelErrorCheckUserPwd.setText(HintEnum.ChPwdEmpty.getName());
		}
		
		return isLegal;
	}
	
	private boolean inSertIntoUserAdmin(){
		boolean isSuccess=false;
		User user=new User();
		user.setUserId(textFieldUserName.getText());
		user.setPwd(textFieldUserPwd.getText());
		
		user.setPwd(user.encrytpMD5PWD(user.getPwd()));
		
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		
		try {
			PreparedStatement pStatement=connection.prepareStatement(
					"INSERT INTO admin_information (Control_Id,Control_PWD,Control_Limit,Tel,Name,Give_Name,gender)"
					+ "VALUES(?,?,?,?,?,?,?)");
			pStatement.setString(1, user.getUserId());
			pStatement.setString(2, user.getPwd());
			pStatement.setInt(3, Jurisdtion.getJurValue(user.getControlLimit()));
			pStatement.setString(4, user.getTel());
			pStatement.setString(5, user.getFirstName());
			pStatement.setString(6, user.getSecondName());
			pStatement.setInt(7, user.getGarde().getIndex());
			
			int row=pStatement.executeUpdate();
			if (row>0) {
				isSuccess=true;
			}
			
			pStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			sqlDeloy.shotDownCon();
			return isSuccess;
	}
	
	private void initErrorMessageLabel(){
		labelErrorCheckUserPwd.setText("");
		labelErrorUserName.setText("");
		labelErrorUserPwd.setText("");
	}
	
	private void initAllTextField(){
		textFieldUserName.setText("");
		textFieldUserPwd.setText("");
		textFieldCheckUserPwd.setText("");
	}

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		myController=stageController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initErrorMessageLabel();
	}

}
