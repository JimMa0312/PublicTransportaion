package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.zip.InflaterInputStream;

import com.sun.crypto.provider.RC2Parameters;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import publicTransportaion.MainApp;
import publicTransportaion.sql.SqlDeloy;

public class SignInController implements ControlledStage {
	@FXML
    private TextField Control_Id_TextField;
    @FXML
    private PasswordField Control_PWD_PasswordField;
    @FXML
    private Label returnMessage;

    private boolean okClicked = false;
    
    private String user;
    private String password;
    
    private StageController myController;
    private Stage stage;
    
    
    @FXML
    private void initialize() {
    	returnMessage.setText(null);
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
    	
    	user="sa";
    	password="1230";
    	
       if(isInputValid()){
    	   if(NTLM())
    	   {
           	returnMessage.setText("登陆成功");
           	returnMessage.setTextFill(Color.GREEN);
   			
   			okClicked=true;
    		myController.shutDownStage(MainApp.SignInId);
    		Control_Id_TextField.setText(null);
    		Control_PWD_PasswordField.setText(null);
    		returnMessage.setText(null);
   			MainApp.showTranstationManageView();
    		
    	   }
    	   else {
              	returnMessage.setText("账号或密码错误！");
              	returnMessage.setTextFill(Color.RED);
    	   }
       }
    }
    
    private boolean NTLM(){
    	SqlDeloy sqlDeloy=new SqlDeloy();
    	Connection connection=sqlDeloy.getConnection();
    	
    	
    	String sql="select Control_PWD from [dbo].[admin_information] where COntrol_Id='"+Control_Id_TextField.getText()+"'";
    	PreparedStatement pstmt;
    	
    	try {
			pstmt=(PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet=pstmt.executeQuery();
			int col=resultSet.getMetaData().getColumnCount();
			if (col<1||col>1) {
				return false;
			}
			String TRUEpwd=resultSet.getString("Control_PWD");
			
			if (TRUEpwd.equals(Control_PWD_PasswordField.getText())) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return false;
    	
    }
    
    @FXML
    private void handleCancel() {
    	myController.shutDownStage(MainApp.SignInId);
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        boolean isInputvalid=true;

        if (Control_Id_TextField.getText() == null || Control_Id_TextField.getText().length() == 0) {
            returnMessage.setText("请输入账户");
            returnMessage.setTextFill(Color.RED);
            isInputvalid=false;
        }
        if (Control_PWD_PasswordField.getText() == null || Control_PWD_PasswordField.getText().length() == 0) {
            returnMessage.setText("请输入密码");
            returnMessage.setTextFill(Color.RED);
            isInputvalid=false;
        }
        
        return isInputvalid;
        
    }

	@Override
	public void setStageController(StageController stageController) {
		// TODO Auto-generated method stub
		this.myController=stageController;
	}
	
	public void setStage(Stage stage){
		this.stage=stage;
	}
    
	public void goToMain() {
		myController.setStage(MainApp.SignInId);
	}
}
