package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Cars;
import publicTransportaion.model.Plane;
import publicTransportaion.model.en.PatternEnum;
import publicTransportaion.model.en.eLineLayer;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.util.Patterner;
import publicTransportaion.util.TImeUtil;

public class TransationManageBusDyanmic implements ControlledStage, Initializable {
	@SuppressWarnings("unused")
	private StageController myController;
	
	private ObservableList<Plane> Planes=FXCollections.observableArrayList();
	private ObservableList<Bus> Buses=FXCollections.observableArrayList();	
	private ObservableList<Cars> carses=FXCollections.observableArrayList();
	
	private eLineLayer lineLayer;
	
	@FXML
	private TextField carPosIdTextfield;
	@FXML
	private TextField busIdTextfield;
	@FXML
	private ChoiceBox<String> typeOfLineChoiceBox;
	@FXML
	private DatePicker WorkeDatePicker;
	@FXML
	private TextField hourTextField;
	@FXML
	private TextField minuteTextField;
	
	@FXML
	private Label errorBusIdLabel;
	@FXML
	private Label errorCarLisLabel;
	@FXML
	private Label errorTimeLabel;
	@FXML
	private Label errorlayerLabel;
	
	@FXML
	private TableView<Plane> tablePlanes;
	@FXML
	private TableColumn<Plane, String> tableColumnPlanesBusNo;
	@FXML
	private TableColumn<Plane, String> tableColumnPlanesLicense;
	
	@FXML
	private TableView<Bus> tableBus;
	@FXML
	private TableColumn<Bus, String> tableColumnBusNo;
	
	@FXML
	private TableView<Cars> tableCar;
	@FXML
	private TableColumn<Cars, String> tableColumnCarLicense;
	
	@FXML
	private void handlerCreatePlane(){
		if (isInputVlid()&&InsertIntoPlanes()) {
				Plane plane=new Plane();
				plane.setBusNo(busIdTextfield.getText());
				plane.setStationId("001");
				plane.setLicense_Plate(carPosIdTextfield.getText());
				plane.setEngine_start(enTime());
				plane.setCarOut_Date(WorkeDatePicker.getValue().toString());
				plane.setGPS("   ");
				plane.setLine_Layer(lineLayer.getBool());
				
				Planes.add(plane);
		}else{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("错误！！！");
			alert.setHeaderText("新建失败!");
			alert.showAndWait();
		}
	}
	
	private boolean isInputVlid(){
		boolean isVlid=true;
		if (busIdTextfield.getText()==null||busIdTextfield.getText().isEmpty()) {
			isVlid=false;
			errorBusIdLabel.setText("请选择公交线路号");
		}
		if (carPosIdTextfield.getText()==null||carPosIdTextfield.getText().isEmpty()) {
			errorCarLisLabel.setText("请选择车辆");
			isVlid=false;
		}
		if (lineLayer==null) {
			errorlayerLabel.setText("请选择线路方向");
			isVlid=false;
		}
		if (hourTextField.getText()==null||hourTextField.getText().isEmpty()||minuteTextField.getText()==null||minuteTextField.getText().isEmpty()) {
			errorTimeLabel.setText("请输入时间");
			isVlid=false;
		}
		if (Patterner.StringMatch(PatternEnum.OnlyNUM, hourTextField.getText())||Patterner.StringMatch(PatternEnum.OnlyNUM, hourTextField.getText())) {
			errorTimeLabel.setText("请输入正确的时间");
			isVlid=false;
		}
		
		return isVlid;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> layerList=FXCollections.observableArrayList("上行","下行");
		typeOfLineChoiceBox.setItems(layerList);
		
		typeOfLineChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable,oldValue,newValue)->{lineLayer=eLineLayer.getBoLineLayer(newValue.intValue()+1);});
		
		initDatePicker();
		initErrorMessage();
		Planes.clear();
		Buses.clear();
		carses.clear();
		initTextField(null);
		SelectPlanes();
		connectAndSelectBusInfor();
		connectAndSelectCarsInfor();
		
		tablePlanes.setItems(Planes);
		tableColumnPlanesBusNo.setCellValueFactory(cellData->cellData.getValue().getBusNoProperty());
		tableColumnPlanesLicense.setCellValueFactory(cellData->cellData.getValue().getLicense_PlateProperty());
		
		tablePlanes.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->initTextField(newValue));
		
		tableBus.setItems(Buses);
		tableColumnBusNo.setCellValueFactory(cellData->cellData.getValue().getBusNoProperty());
		tableBus.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			busIdTextfield.setText(newValue.getBusNo());
		});
		tableCar.setItems(carses);
		tableColumnCarLicense.setCellValueFactory(cellData->cellData.getValue().getLicensePlateProperty());
		tableCar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
			carPosIdTextfield.setText(newValue.getLicensePlate());
		});
		
		tablePlanes.getSelectionModel().selectedItemProperty();
	}
	
	private String enTime(){
		String[] TimeValue = {hourTextField.getText(),minuteTextField.getText()};
		
		return TImeUtil.parseString(TimeValue);
	}
	
	private void initErrorMessage(){
		errorBusIdLabel.setText("");
		errorCarLisLabel.setText("");
		errorlayerLabel.setText("");
		errorTimeLabel.setText("");
	}

	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
	}
	
	private void initDatePicker(){
		final Callback<DatePicker, DateCell> dayCellFactory=
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker){
				return new DateCell(){
					@Override
					public void updateItem(LocalDate item, boolean empty){
						super.updateItem(item, empty);
						
						if (item.isBefore(LocalDate.now())) {
							setDisable(true);
							setStyle("-fx-background-color:#ffc0cb;");
						}
					}
				};
			}
				};
		WorkeDatePicker.setDayCellFactory(dayCellFactory);
		WorkeDatePicker.setValue(LocalDate.now());
	}
	
	private void initTextField(Plane plane){
		if (plane==null) {
			carPosIdTextfield.setText("");
			busIdTextfield.setText("");
			hourTextField.setText("");
			minuteTextField.setText("");
			WorkeDatePicker.setValue(LocalDate.now());
			
			lineLayer=null;
		}else{
			carPosIdTextfield.setText(plane.getLicense_Plate());
			busIdTextfield.setText(plane.getBusNo());
			WorkeDatePicker.setValue(LocalDate.parse(plane.getCarOut_Date()));
			String[] time=TImeUtil.parseStringList(plane.getEngine_start());
			hourTextField.setText(time[0]);
			minuteTextField.setText(time[1]);
			
			lineLayer=plane.getLine_Layer()?eLineLayer.up:eLineLayer.down;
		}
	}
	
	private void SelectPlanes() {
		SqlDeloy sqlDeloy=new SqlDeloy();
		String SelectCommand="SELECT * FROM SID";
		
		try {
			Statement Stat=sqlDeloy.getConnection().createStatement();
			ResultSet resultSet=Stat.executeQuery(SelectCommand);
			while(resultSet.next()){
				Plane plane=new Plane();
				plane.setBusNo(resultSet.getString("Bus_No"));
				plane.setStationId(resultSet.getString("Station_ID"));
				plane.setLicense_Plate(resultSet.getString("License_Plate"));
				plane.setEngine_start(resultSet.getString("Engine_start"));
				plane.setGPS(resultSet.getString("GPS"));
				plane.setCarOut_Date(resultSet.getString("CarrOut_Date"));
				plane.setLine_Layer(eLineLayer.getBoLineLayer(resultSet.getInt("Line_Layer")).getBool());
				
				Planes.add(plane);
			}
			
			resultSet.close();
			Stat.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean InsertIntoPlanes(){
		boolean isDone=false;
		SqlDeloy sqlDeloy=new SqlDeloy();
		String insertIntoCommand="INSERT INTO SID (Bus_No,Station_ID,License_Plate,Engine_start,GPS,CarrOut_Date,Line_Layer) VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement Stat=sqlDeloy.getConnection().prepareStatement(insertIntoCommand);
			Stat.setString(1, busIdTextfield.getText());
			Stat.setString(2, "001");
			Stat.setString(3, carPosIdTextfield.getText());
			Stat.setString(4, enTime());
			Stat.setString(5, "  ");
			Stat.setString(6, WorkeDatePicker.getValue().toString());
			Stat.setBoolean(7, lineLayer.getBool());
			
			int row=Stat.executeUpdate();
			if (row>0) {
				isDone=true;
			}
			Stat.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDone;
	}
	
	private void connectAndSelectBusInfor() {
		SqlDeloy sqlDeloy=new SqlDeloy();
    	Connection connection=sqlDeloy.getConnection();
    	
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
				Buses.add(bus);
			}
	    	
	    	resultSet.close();
			stmt.close();
			sqlDeloy.shotDownCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void connectAndSelectCarsInfor() {
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();

		try {
			Statement stmt = connection.createStatement();
			String sql = "select * from Car_information";

			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				Cars car = new Cars();
				car.setLicensePlate(resultSet.getString("License_Plate"));
				car.setEingeId(resultSet.getString("Einge_id"));
				car.setFrameId(resultSet.getString("Frame_id"));
				car.setBusType(resultSet.getString("Bus_tyoe"));
				car.setCarPopulation(resultSet.getInt("Can_population"));
				car.setBusChair(resultSet.getInt("Bus_Chair"));
				carses.add(car);
			}

			resultSet.close();
			stmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
