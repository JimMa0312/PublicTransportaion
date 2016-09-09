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
import javafx.scene.paint.Color;
import publicTransportaion.model.Cars;
import publicTransportaion.model.Station;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.util.GPS;

public class TransationManageStationController implements ControlledStage,Initializable{
	@FXML
    private Label Station_Name_error;
    @FXML
    private Label Station_ID_error;
    @FXML
    private Label Station_Address_error;
    @FXML
    private Label Station_GPS_error;
    
    @FXML 
    private TextField Station_Name_TextFeild;
    @FXML
    private TextField Station_ID_TextFeild;
    @FXML
    private TextField Station_Address_TextFeild;
    @FXML
    private TextField Station_GPS_X_TextFeild;
    @FXML
    private TextField Station_GPS_Y_TextFeild;
    
	
	@FXML
    private TableView<Station> stationsTable;
    @FXML
    private TableColumn<Station, String> Station_ID_Column;
    @FXML
    private TableColumn<Station, String> Station_Name_Column;
    
    @SuppressWarnings("unused")
	private StageController myController;
    
    
    private ObservableList<Station> stationsList=FXCollections.observableArrayList();
    private double[] gps=new double[2];
    
	private void showStationDetails(Station station) {
		if (station==null) {
			Station_Name_TextFeild.setText("");
			Station_ID_TextFeild.setText("");
			Station_Address_TextFeild.setText("");
			Station_GPS_X_TextFeild.setText("");
			Station_GPS_Y_TextFeild.setText("");
		}else{		
			Station_Name_TextFeild.setText(station.getStationName());
			Station_ID_TextFeild.setText(station.getStationID());
			Station_Address_TextFeild.setText(station.getStationAddress());
			Station_GPS_X_TextFeild.setText(Double.toString(station.getStationGPSX()));
			Station_GPS_Y_TextFeild.setText(Double.toString(station.getStationGPSY()));
		}
	}
	
	private void initErrorMessage() {
		Station_Name_error.setText("");
		Station_ID_error.setText("");
		Station_Address_error.setText("");
		Station_GPS_error.setText("");
	}
	
	private void initErrorMessageTextFill() {
		Station_Name_error.setTextFill(Color.RED);
		Station_ID_error.setTextFill(Color.RED);
		Station_Address_error.setTextFill(Color.RED);
		Station_GPS_error.setTextFill(Color.RED);
	}
	
	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initErrorMessage();
		initErrorMessageTextFill();
		connectAndSelectStationInfor();
    	showStationDetails(null);

		stationsTable.setItems(stationsList);
		Station_ID_Column.setCellValueFactory(cellData -> cellData.getValue().getStationIDProperty());
		Station_Name_Column.setCellValueFactory(cellData -> cellData.getValue().getStationNameProperty());
    	
    	stationsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStationDetails(newValue));
	}

	private void connectAndSelectStationInfor() {
		// TODO Auto-generated method stub
		SqlDeloy sqlDeloy=new SqlDeloy();
    	Connection connection=sqlDeloy.getConnection();
    	stationsList.clear();
    	
    	try {
    		Statement stmt=connection.createStatement();
	    	String sql="select * from Station_information";
	    	
	    	ResultSet resultSet=stmt.executeQuery(sql);
	    	while (resultSet.next()) {
				Station station=new Station();
				station.setStationName(resultSet.getString("Station_Name"));
				station.setStationID(resultSet.getString("Station_ID"));
				station.setStationAddress(resultSet.getString("Station_Address"));
				
				stationsList.add(station);
			}
	    	
	    	resultSet.close();
	    	stmt.close();
	    	sqlDeloy.shotDownCon();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void setGPSValue(){
		gps[0]=Double.parseDouble(Station_GPS_X_TextFeild.getText());
		gps[1]=Double.parseDouble(Station_GPS_Y_TextFeild.getText());
	}
	
	@FXML
	private void handleNewStation(){
		Station newStation=new Station();
		if (isInputVlid()) {
			initErrorMessage();
			newStation.setStationName(Station_Name_TextFeild.getText());
			newStation.setStationID(Station_ID_TextFeild.getText());
			newStation.setStationAddress(Station_Address_TextFeild.getText());
			setGPSValue();
			newStation.setStationGPS(GPS.parseString(gps));
			if (InsertIntoSql(newStation)) {
				stationsList.add(newStation);
			}else{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("数据库上传错误");
				alert.setHeaderText("上聚酷新建信息错误");
				alert.setContentText("数据可能重复");
			}
		}
	}
	
	@FXML
	private void handleEditCars(){
		Station editStation=stationsTable.getSelectionModel().getSelectedItem();
		setGPSValue();
		if (isInputVlid()) {
			initErrorMessage();
			editStation.setStationName(Station_Name_TextFeild.getText());
			editStation.setStationID(Station_ID_TextFeild.getText());
			editStation.setStationAddress(Station_Address_TextFeild.getText());
			editStation.setStationGPS(GPS.parseString(gps));
			if (UpDateSql()) {
			}else{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("数据库上传错误");
				alert.setHeaderText("上聚酷新建信息错误");
				alert.setContentText("数据可能重复");
			}
		}
	}
	
	@FXML
	private void handleDeleteCars() {
		int selectedIndex = stationsTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex>=0) {
			Station station = stationsList.get(selectedIndex);

			if (DeleteStationInformationfromSql(station.getStationID())) {
				stationsTable.getItems().remove(selectedIndex);
			} else {
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
	
	private boolean isInputVlid(){
		boolean message=true;
		if (Station_Name_TextFeild.getText().isEmpty() || Station_Name_TextFeild.getText()==null) {
			Station_Name_error.setText("请输入站点名称");
			message=false;
		}
		if (Station_ID_TextFeild.getText().isEmpty()||Station_ID_TextFeild.getText()==null) {
			Station_ID_error.setText("请输入站点编号");
			message=false;
		}
		if (Station_Address_TextFeild.getText().isEmpty()||Station_Address_TextFeild.getText()==null) {
			Station_Address_error.setText("请输入站点地址");
			message=false;
		}
		if (Station_GPS_X_TextFeild.getText().isEmpty()||Station_GPS_X_TextFeild.getText()==null
				||Station_GPS_Y_TextFeild.getText().isEmpty()||Station_GPS_Y_TextFeild.getText()==null) {
			Station_GPS_error.setText("请输入站点GPS");
			message=false;
		}
		
		return message;
	}
	
	private boolean DeleteStationInformationfromSql(String Id) {
		boolean isOk = false;
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();
		try {
			PreparedStatement pStmt = connection.prepareStatement("DELETE FROM Station_information WHERE Station_ID=?");
			pStmt.setString(1, Id);
			int rtn = pStmt.executeUpdate();
			System.out.println(rtn);
			isOk = (rtn == 0) ? false : true;

			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isOk;
	}
	
	private boolean InsertIntoSql(Station station) {
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		
		try {
			PreparedStatement pStmt=connection.prepareStatement("INSERT INTO Station_information (Station_ID,Station_Name,Station_Address,Station_GPS) VALUES (?,?,?,?)");
			pStmt.setString(1, station.getStationID());
			pStmt.setString(2, station.getStationName());
			pStmt.setString(3, station.getStationAddress());
			pStmt.setString(4, station.getStationGPS());
			int row=pStmt.executeUpdate();
			if (row>0) {
				System.out.println("成功");
				return true;
			}
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("失败");
			return false;
		}
		System.out.println("失败");
		return false;
	}
	
	private boolean UpDateSql() {
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		
		try {
			PreparedStatement pStmt=connection.prepareStatement("UPDATE Station_information SET Station_ID=?,Station_Name=?,Station_Address=?,Station_GPS=? WHERE Station_ID=?");
			pStmt.setString(1, Station_ID_TextFeild.getText());
			pStmt.setString(2, Station_Name_TextFeild.getText());
			pStmt.setString(3, Station_Address_TextFeild.getText());
			pStmt.setString(4, GPS.parseString(gps));
			pStmt.setString(5, Station_ID_TextFeild.getText());
			int row=pStmt.executeUpdate();
			if (row>0) {
				System.out.println("成功");
				return true;
			}
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("失败");
			return false;
		}
		System.out.println("失败");
		return false;
	}

}
