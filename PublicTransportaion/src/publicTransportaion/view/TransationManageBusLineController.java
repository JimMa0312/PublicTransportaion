package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Station;
import publicTransportaion.model.en.PatternEnum;
import publicTransportaion.model.en.eSqlCommand;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.util.Patterner;
import publicTransportaion.util.StationsConverter;
import publicTransportaion.util.TImeUtil;

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
    private TextField Time_Start_Hour_TextField;
    @FXML
    private TextField Time_Start_Mintue_TextField;
    @FXML
    private TextField Time_End__Hour_TextField;
    @FXML
    private TextField Time_End__Mintue_TextField;
    
    
    
    @FXML
    private TableView<Bus> Bus_Line_ListTable;
    @FXML
    private TableColumn<Bus, String> Bus_Line_Column;
    
    @FXML
    private TableView<Station> tableViewSelectedStation;
    @FXML
    private TableColumn<Station, String> tableColumnSelctionStation;
    
    @FXML
    private TableView<Station> tableViewUnSelectedStation;
    @FXML
    private TableColumn<Station, String> tableColumnUnSelection;
    
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
    
    private ObservableList<Station> selectedStations=FXCollections.observableArrayList();
    
    private ObservableList<Station> unselectedStation=FXCollections.observableArrayList();
    
    private Map<String, Station> UnselectStationMap=new HashMap<String,Station>();
   
    
	private void showBusDetails(Bus bus) {
		if (bus == null) {
			selectedStations.clear();
			Bus_No_TextField.setText("");
			Start_Station_TextField.setText("");
			End_Station_TextField.setText("");
			Time_Lage_TextField.setText("");
			Time_Start_Hour_TextField.setText("");
			Time_Start_Mintue_TextField.setText("");
			Time_End__Hour_TextField.setText("");
			Time_End__Mintue_TextField.setText("");
		} else {
			selectedStations.clear();
			getStationsWithSql(bus.getBusNo());
			Bus_No_TextField.setText(bus.getBusNo());			
			Time_Lage_TextField.setText(bus.getTimeLag());
			String[] startTime=TImeUtil.parseStringList(bus.getTimeStart1());
			Time_Start_Hour_TextField.setText(startTime[0]);
			Time_Start_Mintue_TextField.setText(startTime[1]);
			String[] endTime=TImeUtil.parseStringList(bus.getTimeEnd1());
			Time_End__Hour_TextField.setText(endTime[0]);
			Time_End__Mintue_TextField.setText(endTime[1]);
			setTextStartStation();
			setTextEndStation();
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
		Bus_No_Error.setTextFill(Color.RED);
		Start_Station_error.setTextFill(Color.RED);
		End_Station_error.setTextFill(Color.RED);
		Time_Lage_error.setTextFill(Color.RED);
		Time_Start_error.setTextFill(Color.RED);
		Time_End_error.setTextFill(Color.RED);
	}
	
	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }
	
	private void connectAndSelectBusInfor() {
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
				bus.setTimeLag(resultSet.getString("Time_Lag"));
				bus.setStartTime(resultSet.getString("Time_Start"));
				bus.setTimeEnd(resultSet.getString("Time_End"));		
				busList.add(bus);
			}
	    	
	    	resultSet.close();
			stmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connectAndSelectBusInfor();
		initErrorMessageTextFill();
		initErrorMessage();
		
		showBusDetails(null);
		
		Bus_Line_ListTable.setItems(busList);
		Bus_Line_Column.setCellValueFactory(cellData -> cellData.getValue().getBusNoProperty());
    	Bus_Line_ListTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {showBusDetails(newValue);});
    	initTableUnselected();
    	initTableSelected();
	}
	
	private void initTableUnselected(){
		connectionStationTabel();
		tableViewUnSelectedStation.setItems(unselectedStation);
		tableColumnUnSelection.setCellValueFactory(cellData->cellData.getValue().getStationNameProperty());
	}
	
	private void initTableSelected(){
		tableViewSelectedStation.setItems(selectedStations);
		tableColumnSelctionStation.setCellValueFactory(cellData->cellData.getValue().getStationNameProperty());
	}
	
	@FXML
	private void handleNewBusLine(){
		Bus newBus = new Bus();
		if (isInputVlid()) {
			initErrorMessage();
			newBus.setBusNo(Bus_No_TextField.getText());
			newBus.setTimeLag(Time_Lage_TextField.getText());
			newBus.setStartTime(enStartTime());
			newBus.setTimeEnd(enEndTime());
			
			if(InsertIntoSql(newBus)&&InsertIntoRoute(newBus.getBusNo())){
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
			
			if(UpDateSql()&&UpDateRoute()){
				editbus.setBusNo(Bus_No_TextField.getText());
				editbus.setTimeLag(Time_Lage_TextField.getText());
				editbus.setStartTime(enStartTime());
				editbus.setTimeEnd(enEndTime());
			}else{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("数据库上传错误");
				alert.setHeaderText("数据库新建信息错误");
				alert.setContentText("数据可能重复");
			}
		}
	}
	
	@FXML
	private void handleDeleteBusLine(){
		int selectedIndex = Bus_Line_ListTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex>=0){			
			if(DeleteRoute()&&DeleteBusInformationfromSql()){
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
	
	private String enStartTime(){
		String[] startTime={Time_Start_Hour_TextField.getText(), Time_Start_Mintue_TextField.getText()};
		
		return TImeUtil.parseString(startTime);
	}
	
	private String enEndTime(){
		String[] endTime={
				Time_End__Hour_TextField.getText(),
				Time_End__Mintue_TextField.getText()
		};
		
		return TImeUtil.parseString(endTime);
	}
	
	@FXML
	private void handleAddStation(){
		selectedStations.add(unselectedStation.get(tableViewUnSelectedStation.getSelectionModel().getSelectedIndex()));
		setTextEndStation();
		setTextStartStation();
	}
	
	@FXML
	private void handleRemoveStation(){
		selectedStations.remove(selectedStations.get(tableViewSelectedStation.getSelectionModel().getSelectedIndex()));
		setTextEndStation();
		setTextStartStation();
	}
	
	@FXML
	private void handleUp(){
		int index=tableViewSelectedStation.getSelectionModel().getSelectedIndex();
		if (index>0) {
			selectedStations.add(index-1, selectedStations.get(index));
			selectedStations.remove(index+1);
			setTextEndStation();
			setTextStartStation();
		}
	}
	
	@FXML
	private void handleDown(){
		int index=tableViewSelectedStation.getSelectionModel().getSelectedIndex();
		if (index<selectedStations.size()) {
			selectedStations.add(index+2,selectedStations.get(index));
			selectedStations.remove(index);
			setTextEndStation();
			setTextStartStation();
		}
	}
	
	@FXML
	private void handleClear(){
		selectedStations.clear();
		Start_Station_TextField.setText("");
		End_Station_TextField.setText("");
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
		if (Time_Start_Hour_TextField.getText()==null && Time_Start_Hour_TextField.getText().isEmpty()&&Time_Start_Mintue_TextField.getText()==null&&Time_Start_Mintue_TextField.getText().isEmpty()) {
			Time_Start_error.setText("请输入发车时间");
			message = false;
		}
		if (!Patterner.StringMatch(PatternEnum.OnlyNUM, Time_Start_Hour_TextField.getText())&&!Patterner.StringMatch(PatternEnum.OnlyNUM, Time_Start_Mintue_TextField.getText())) {
			Time_Start_error.setText("请正确输入发车时间");
			message = false;
		}
		if (Time_End__Hour_TextField.getText()==null && Time_End__Hour_TextField.getText().isEmpty()&&Time_End__Mintue_TextField.getText()==null&&Time_End__Mintue_TextField.getText().isEmpty()) {
			Time_End_error.setText("请输入末班时间");
			message = false;
		}
		if (!Patterner.StringMatch(PatternEnum.OnlyNUM, Time_End__Hour_TextField.getText())&&!Patterner.StringMatch(PatternEnum.OnlyNUM, Time_End__Mintue_TextField.getText())) {
			Time_End_error.setText("请输入末班时间");
			message = false;
		}
		if (selectedStations.size()==0) {
			message=false;
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("错误!!");
			alert.setHeaderText("站点列表为空");
			alert.setContentText("请添加站点");
			alert.showAndWait();
		}
		return message;
		
	}
	
	private boolean InsertIntoSql(Bus bus){
		boolean isDone=false;
		SqlDeloy sqlDeloy=new SqlDeloy();
		
		try {
			PreparedStatement pStmt=sqlDeloy.getConnection().prepareStatement(
					"INSERT INTO Bus_information (Bus_No,Time_Start,Time_End,Time_Lag) VALUES (?,?,?,?)");
			pStmt.setString(1, bus.getBusNo());
			pStmt.setString(2, bus.getTimeStart1());
			pStmt.setString(3, bus.getTimeEnd1());
			pStmt.setInt(4, Integer.parseInt(bus.getTimeLag()));
			
			int row = pStmt.executeUpdate();
			if(row>0)
				isDone=true;
			
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return isDone;
	}
	
	private Boolean InsertIntoRoute(String busNo){
		boolean isDone=false;
		SqlDeloy sqlDeloy=new SqlDeloy();
		try {
			PreparedStatement pStmt=sqlDeloy.getConnection().prepareStatement(
					"INSERT INTO Route_Planning (Bus_No,UpStream,DownStream) VALUES(?,?,?)");
			pStmt.setString(1, busNo);
			pStmt.setString(2, StationsConverter.parseString(selectedStations));
			FXCollections.reverse(selectedStations);
			pStmt.setString(3, StationsConverter.parseString(selectedStations));
			FXCollections.reverse(selectedStations);
			
			int row=pStmt.executeUpdate();
			if (row>0) {
				isDone=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDone;
	}
	
	
	private boolean UpDateSql(){
		boolean isDone=false;
		SqlDeloy sqlDeloy=new SqlDeloy();
		
		try {
			PreparedStatement pStmt=sqlDeloy.getConnection().prepareStatement(
					"UPDATE Bus_information SET Bus_No=?,Time_Start=?,Time_End=?,Time_Lag=? where Bus_No=?");
			pStmt.setString(1, enStartTime());
			pStmt.setString(2, enEndTime());
			pStmt.setInt(3, Integer.parseInt(busList.get(Bus_Line_ListTable.getSelectionModel().getFocusedIndex()).getBusNo()));
			int row = pStmt.executeUpdate();
			if(row>0)
				isDone=true;
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return isDone;
		
	}
	
	private boolean UpDateRoute(){
		boolean isDone=false;
		SqlDeloy sqlDeloy=new SqlDeloy();
		try {
			PreparedStatement pStmt=sqlDeloy.getConnection().prepareStatement("UPDATE Route_Planning SET UpStream = ?,DownStream = ? WHERE Bus_No=?");
			pStmt.setString(1, StationsConverter.parseString(selectedStations));
			FXCollections.reverse(selectedStations);
			pStmt.setString(2, StationsConverter.parseString(selectedStations));
			FXCollections.reverse(selectedStations);
			pStmt.setInt(3, Integer.parseInt(busList.get(Bus_Line_ListTable.getSelectionModel().getFocusedIndex()).getBusNo()));
			
			int row=pStmt.executeUpdate();
			if (row>0) {
				isDone=true;
			}
			
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isDone;
	}
	
	
	private boolean DeleteBusInformationfromSql() {
		boolean isOk = false;
		SqlDeloy sqlDeloy = new SqlDeloy();
		try {
			PreparedStatement pStmt = sqlDeloy.getConnection().prepareStatement(
					"Delete from Bus_information where Bus_No=?");
			pStmt.setString(1, busList.get(Bus_Line_ListTable.getSelectionModel().getFocusedIndex()).getBusNo());
			int rtn = pStmt.executeUpdate();
			System.out.println(rtn);
			isOk = (rtn == 0) ? false : true;
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return isOk;
		
	}
	
	private boolean DeleteRoute(){
		boolean isDone=false;
		SqlDeloy sqlDeloy=new SqlDeloy();
		
		try {
			PreparedStatement pStmt=sqlDeloy.getConnection().prepareStatement("DELETE FROM Route_Planning WHERE Bus_No=?");
			pStmt.setString(1, busList.get(Bus_Line_ListTable.getSelectionModel().getFocusedIndex()).getBusNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isDone;
	}
	
	private boolean getStationsWithSql(String Busid){
		boolean isDone=false;
		String selectStations = "SELECT UpStream FROM Route_Planning where Bus_No='"+Busid+"'";
		try {
			SqlDeloy sqlDeloy=new SqlDeloy();
			Connection connection=sqlDeloy.getConnection();
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(selectStations);
			while(resultSet.next()){
				String[] stationStringArray=StationsConverter.parseStringList(resultSet.getString("UpStream"));
				StationEnArrayList(stationStringArray);			
			}
		} catch (SQLException e) {
		}
		
		return isDone;
	}
	private void setTextStartStation() {
		Start_Station_TextField.setText(selectedStations.get(0).getStationName());
	}
	
	private void setTextEndStation() {
		End_Station_TextField.setText(selectedStations.get(selectedStations.size()-1).getStationName());
	}
	
	private boolean connectionStationTabel(){
		boolean isDone=false;
		
		try {
			unselectedStation.clear();
			SqlDeloy sqlDeloy=new SqlDeloy();
			Connection connection=sqlDeloy.getConnection();
			Statement statement=connection.createStatement();
			
			ResultSet resultSet=statement.executeQuery(eSqlCommand.SelectStation.getName());
			while(resultSet.next()){
				Station station=new Station();
				station.setStationName(resultSet.getString("Station_Name"));
				station.setStationID(resultSet.getString("Station_ID"));
				station.setStationAddress(resultSet.getString("Station_Address"));
				station.setStationGPSX(resultSet.getFloat("Station_GPS_X"));
				station.setStationGPSY(resultSet.getFloat("Station_GPS_Y"));
				unselectedStation.add(station);
				UnselectStationMap.put(station.getStationID(), station);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return isDone;
	}
	
	private void StationEnArrayList(String[] stations){
		for (int i = 0; i < stations.length; i++) {
			selectedStations.add(UnselectStationMap.get(stations[i]));
		}
	}
}
