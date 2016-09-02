package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignInController {
	@FXML
    private TextField Control_Id_TextField;
    @FXML
    private PasswordField Control_PWD_PasswordField;


    private Stage dialogStage;

    private boolean okClicked = false;
    
    private String user;
    private String password;
    
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
       if(isInputValid()){
    	   if(Control_Id_TextField.getText()==user&&Control_PWD_PasswordField.getText()==password)
    	   {
           	Alert alert=new Alert(AlertType.CONFIRMATION);
   			alert.setTitle("消息提示");
   			alert.setHeaderText("登陆状态");
   			alert.setContentText("您已登录成功！");
   			alert.showAndWait();
    		   //此处需要添加登陆成功的提示窗并弹出管理页面
    		   dialogStage.close();
    	   }
    	   else {
              	Alert alert=new Alert(AlertType.CONFIRMATION);
       			alert.setTitle("消息提示");
       			alert.setHeaderText("登陆状态");
       			alert.setContentText("用户名或密码错误！");
       			alert.showAndWait();
    	   }
       }
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (Control_Id_TextField.getText() == null || Control_Id_TextField.getText().length() == 0) {
            errorMessage += "ID cant't be empty!\n"; 
        }
        if (Control_PWD_PasswordField.getText() == null || Control_PWD_PasswordField.getText().length() == 0) {
            errorMessage += "Password can't be empty!\n"; 
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {

        	Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
        }
    }
    
}
