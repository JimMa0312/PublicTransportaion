package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import publicTransportaion.model.Bus;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.util.SingleLine;
import publicTransportaion.util.TImeUtil;
import publicTransportaion.util.TimeConverter;

public class TransationManageBusLineController implements ControlledStage,Initializable {
	@FXML
    private TextField Bus_No_TextField;
    @FXML
    private TextField Start_Station_TextField;
    @FXML
    private TextField End_Station_TextField;
    @FXML
    private TextField Time_Lage_TextField;
    @FXML
    private TextField Time_Start_TextField;
    @FXML
    private TextField Time_End_TextField;
    
    @FXML
    private TableView<Bus> Bus_Line_ListTable;
    @FXML
    private TableColumn<Bus, String> Bus_Line_Column;
    
    @FXML
	private Label Bus_No_Error;
	@FXML
	private Label Start_Station_error;
	@FXML
	private Label End_Station_error;
	@FXML
	private Label Time_Lage_error;
	@FXML
	private Label Time_Start_error;
	@FXML
	private Label Time_End_error;
    
    @SuppressWarnings("unused")
	private StageController myController;
    
    private ObservableList<Bus> busList=FXCollections.observableArrayList();
   
    
	private void showBusDetails(Bus bus) {
		if (bus == null) {
			Bus_No_TextField.setText("");
			Start_Station_TextField.setText("");
			End_Station_TextField.setText("");
			Time_Lage_TextField.setText("");
			Time_Start_TextField.setText("");
			Time_End_TextField.setText("");
		} else {
			Bus_No_TextField.setText(bus.getBusNo());
			showStartAndEndStation(bus);
			
			Time_Lage_TextField.setText(bus.getTimeLag());
			Time_Start_TextField.setText(TimeConverter.format(bus.getTimeStart1()));
			Time_End_TextField.setText(TimeConverter.format(bus.getTimeEnd1()));

		}
	}
	
	private void showStartAndEndStation(Bus bus){
		List<String> list=SingleLine.parse(bus.getLine());
		String startStation=list.get(0);
		String EndStation=list.get(list.size()-1);
		
		Start_Station_TextField.setText(selectStation(startStation));
		End_Station_TextField.setText(EndStation);
	}
	
	private String  setStartStation(Bus bus) {
		List<String> list=SingleLine.parse(bus.getLine());
		String startStation=list.get(0);
		return startStation;
	}
	
	private String setEndStation(Bus bus) {
		List<String> list=SingleLine.parse(bus.getLine());
		String EndStation=list.get(list.size()-1);
		return EndStation;
		
	}
	
	private String selectStation(String Id) {
		String stationName = null;
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		try {
			PreparedStatement pStmt=connection.prepareStatement("Select Station_Name from Station_information where Station_ID=?");
			pStmt.setString(1, Id);
			ResultSet rest=pStmt.executeQuery();
			while(rest.next()){
				stationName=rest.getString("Station_Name");
				System.out.println(stationName);
			}
			return stationName;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void initErrorMessage() {
		Bus_No_Error.setText(null);
		Start_Station_error.setText(null);
		End_Station_error.setText(null);
		Time_Lage_error.setText(null);
		Time_Start_error.setText(null);
		Time_End_error.setText(null);
	}
	
	private void initErrorMessageTextFill() {
		Bus_No_Error.setTextFill(javafx.scene.paint.Color.RED);
		Start_Station_error.setTextFill(javafx.scene.paint.Color.RED);
		End_Station_error.setTextFill(javafx.scene.paint.Color.RED);
		Time_Lage_error.setTextFill(javafx.scene.paint.Color.RED);
		Time_Start_error.setTextFill(javafx.scene.paint.Color.RED);
		Time_End_error.setTextFill(javafx.scene.paint.Color.RED);
	}
	
	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		connectAndSelectBusInfor();
		initErrorMessageTextFill();
		initErrorMessage();
		
		showBusDetails(null);
		
		Bus_Line_ListTable.setItems(busList);
		Bus_Line_Column.setCellValueFactory(cellData -> cellData.getValue().getBusNoProperty());
    	Bus_Line_ListTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBusDetails(newValue));
	}
	private void connectAndSelectBusInfor() {
		// TODO Auto-generated method stub
		SqlDeloy sqlDeloy=new SqlDeloy();
    	Connection connection=sqlDeloy.getConnection();
    	busList.clear();
    	
    	try {
    		Statement stmt=connection.createStatement();
	    	String sql="select * from Bus_information";
	    	
	    	ResultSet resultSet=stmt.executeQuery(sql);
	    	while (resultSet.next()) {
				Bus bus=new Bus();
				
				bus.setBusNo(resultSet.getString("Bus_No"));
				bus.setLine(resultSet.getNString(this.setStartStation(bus)));
				bus.setLine(resultSet.getNString(this.setEndStation(bus)));
				bus.setTimeLag(resultSet.getString("timeLag_stirng"));
				bus.setStartTime(resultSet.getTime("timeStart"));
				bus.setTimeEnd(resultSet.getTime("timeEnd"));
			
				
				busList.add(bus);
			}
	    	
	    	resultSet.close();
			stmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleDeleteBusLine(){
		int selectedIndex = Bus_Line_ListTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex>=0){
			Bus bus = busList.get(selectedIndex);
			
			if(DeleteBusInformationfromSql(bus.getBusNo())){
				Bus_Line_ListTable.getItems().remove(selectedIndex);
			}else{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("数据库操作错误");
				alert.setHeaderText("数据库操作失误");
				alert.setContentText("数据库连接失败，或删除失败。\n请关闭界面稍后重试");
				alert.showAndWait();
			}
		}else{
			Alert alert=new Alert(AlertType.WARNING);
			alert.setTitle("删除错误");
			alert.setHeaderText("删除失败");
			alert.setContentText("请选择删除的对象");
			alert.showAndWait();
		}
	}
	
	private boolean DeleteBusInformationfromSql(String Id) {
		boolean isOk = false;
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();
		try {
			PreparedStatement pStmt = connection.prepareStatement("Delete from Bus_information where Bus_No=?");
			pStmt.setString(1, Id);
			int rtn = pStmt.executeUpdate();
			System.out.println(rtn);
			isOk = (rtn == 0) ? false : true;
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return isOk;
		
	}
	
	@FXML
	private void handleNewBusLine(){
		Bus newBus = new Bus();
		if (isInputVlid()) {
			initErrorMessage();
			newBus.setBusNo(Bus_No_TextField.getText());
			newBus.setLine(Start_Station_TextField.getText());
			newBus.setLine(End_Station_TextField.getText());
			newBus.setTimeLag(Time_Lage_TextField.getText());
		//	newBus.setStartTime((Time_Start_TextField.getText()));
		//	newBus.setTimeEnd(Time_End_TextField.getText());
			
			if(InsertIntoSql(newBus)){
				busList.add(newBus);
			}
			
		} else {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("数据库上传错误");
			alert.setHeaderText("数据库新建信息错误");
			alert.setContentText("数据可能重复");

		}
	}
	
	@FXML
	private void handleEditBusLine(){
		Bus editbus = Bus_Line_ListTable.getSelectionModel().getSelectedItem();
		if(isInputVlid()){
			initErrorMessage();
			
			if(UpDateSql()){
				editbus.setBusNo(Bus_No_TextField.getText());
				editbus.setLine(Start_Station_TextField.getText());
				editbus.setLine(End_Station_TextField.getText());
				editbus.setTimeLag(Time_Lage_TextField.getText());
			//	editbus.setStartTime((Time_Start_TextField.getText()).toString());
			//	editbus.setTimeEnd(Time_End_TextField.getText());
			}else{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("数据库上传错误");
				alert.setHeaderText("数据库新建信息错误");
				alert.setContentText("数据可能重复");
			}
		}
	}
	
	private boolean isInputVlid(){
		boolean message=true;
		if(Bus_No_TextField.getText()==null && Bus_No_TextField.getText().isEmpty()){
			Bus_No_Error.setText("请输入公交班线编号");
			message = false;
		}
		if(Start_Station_TextField.getText()==null && Start_Station_TextField.getText().isEmpty()){
			Start_Station_error.setText("请输入始发站");
			message = false;
		}
		if (End_Station_TextField.getText()==null && End_Station_TextField.getText().isEmpty()) {
			End_Station_error.setText("请输入终点站");
			message = false;
		}
		if (Time_Lage_TextField.getText()==null&&Time_Lage_TextField.getText().isEmpty()) {
			Time_Lage_error.setText("请输入发车间隔");
			message = false;
		}
		if (Time_Start_TextField.getText()==null && Time_Start_TextField.getText().isEmpty()) {
			Time_Start_error.setText("请输入发车时间");
			message = false;
		}
		if (Time_End_TextField.getText()==null && Time_End_TextField.getText().isEmpty()) {
			Time_End_error.setText("请输入下班时间");
			message = false;
		}
		
		return message;
		
	}
	
	private boolean InsertIntoSql(Bus bus){
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		
		try {
			PreparedStatement pStmt=connection.prepareStatement("INSERT INTO Bus_information (Bus_No,Time_Start,Time_End,Time_Lag) VALUES (?,?,?,?)");
			pStmt.setString(1, bus.getBusNo());
			pStmt.setString(2, TimeConverter.format(bus.getTimeStart1()));
			pStmt.setString(3, TimeConverter.format(bus.getTimeEnd1()) );
			pStmt.setString(4, bus.getTimeLag());
			
			int row = pStmt.executeUpdate();
			if(row>0)
				return true;
			
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return false;
		
	}
	private boolean UpDateSql(){
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		
		try {
			PreparedStatement pStmt=connection.prepareStatement(
					"UPDATE Bus_information SET Bus_No=?,Time_Start=?,Time_End=?,Time_Lag=? where Bus_No=?");
			pStmt.setString(1, Bus_No_TextField.getText());
			pStmt.setString(2, Time_Start_TextField.getText());
			pStmt.setString(3, Time_End_TextField.getText() );
			pStmt.setString(4, Time_Lage_TextField.getText());
			int row = pStmt.executeUpdate();
			if(row>0)
				return true;
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}	
		return false;
		
	}

}
