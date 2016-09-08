package publicTransportaion.view;


import java.awt.Frame;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import publicTransportaion.model.Cars;
import publicTransportaion.sql.SqlDeloy;

public class TransationManageCarsController implements ControlledStage, Initializable {
	@FXML
	private TextField License_Plate_TextField;
	@FXML
	private TextField Engine_id_TextField;
	@FXML
	private TextField Frame_id_TextField;
	@FXML
	private TextField Bus_type_TextField;
	@FXML
	private TextField Car_population_TextField;
	@FXML
	private TextField Bus_chair_TextField;

	@FXML
	private TableView<Cars> CarsTable;
	@FXML
	private TableColumn<Cars, String> License_Plate_Column;
	
	@FXML
	private Label License_Plate_Error;
	@FXML
	private Label Engine_id_error;
	@FXML
	private Label Frame_id_error;
	@FXML
	private Label Bus_type_error;
	@FXML
	private Label Car_population_error;
	@FXML
	private Label bus_chair_error;

	@SuppressWarnings("unused")
	private StageController myController;

	private ObservableList<Cars> carsList = FXCollections.observableArrayList();

	private void showCarsDetails(Cars cars) {
		if (cars == null) {
			License_Plate_TextField.setText(null);
			Engine_id_TextField.setText(null);
			Frame_id_TextField.setText(null);
			Bus_type_TextField.setText(null);
			Car_population_TextField.setText(null);
			Bus_chair_TextField.setText(null);
		} else {
			License_Plate_TextField.setText(cars.getLicensePlate());
			Engine_id_TextField.setText(cars.getEingeId());
			Frame_id_TextField.setText(cars.getFrameId());
			Bus_type_TextField.setText(cars.getBusType());
			Car_population_TextField.setText(Integer.toString(cars.getCarPopulation()));
			Bus_chair_TextField.setText(Integer.toString(cars.getBusChair()));

		}
	}
	
	private void initErrorMessage() {
		License_Plate_Error.setText(null);
		Engine_id_error.setText(null);
		Frame_id_error.setText(null);
		Bus_type_error.setText(null);
		bus_chair_error.setText(null);
		Car_population_error.setText(null);
	}
	
	private void initErrorMessageTextFill() {
		License_Plate_Error.setTextFill(Color.RED);
		Engine_id_error.setTextFill(Color.RED);
	}

	@Override
	public void setStageController(StageController stageController) {
		this.myController = stageController;
	}

	private void connectAndSelectCarsInfor() {
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();
		carsList.clear();

		try {
			Statement stmt = connection.createStatement();
			String sql = "select * from Car_information";

			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				Cars car = new Cars();
				car.setLicensePlate(resultSet.getString("License_Plate"));
				car.setEingeId(resultSet.getString("Einge_id"));
				car.setFrameId(resultSet.getString("Frame_id"));
				car.setBusType(resultSet.getString("Bus_type"));
				car.setCarPopulation(resultSet.getInt("Can_population"));
				car.setBusChair(resultSet.getInt("Bus_Chair"));
				carsList.add(car);
			}

			resultSet.close();
			stmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		connectAndSelectCarsInfor();

		CarsTable.setItems(carsList);
		License_Plate_Column.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());

		showCarsDetails(null);

		CarsTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCarsDetails(newValue));
	}

	@FXML
	private void handleDeleteCars() {
		int selectedIndex = CarsTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex>=0) {
			Cars cars = carsList.get(selectedIndex);

			if (DeleteCarsInformationfromSql(cars.getLicensePlate())) {
				CarsTable.getItems().remove(selectedIndex);
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

	private boolean DeleteCarsInformationfromSql(String Id) {
		boolean isOk = false;
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();
		try {
			PreparedStatement pStmt = connection.prepareStatement("Delete from Car_information where License_Plate=?");
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
	
	private void getCarsInformation(Cars cars){
		cars.setLicensePlate(License_Plate_TextField.getText());
		
		
	}
	
	@FXML
	private void handleNewCars(){
		Cars newCar=new Cars();
		
	}
	
	private boolean isLicensePlateVlid() {
		String vehicleNoStyle="^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$";
		java.util.regex.Pattern pattern=java.util.regex.Pattern.compile(vehicleNoStyle);
		Matcher matcher=pattern.matcher(License_Plate_TextField.getText());
		if (matcher.matches()) {
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isInputVlid(){
		String message=null;
		if (License_Plate_TextField.getText().isEmpty()) {
			message+="请输入车牌照号\n";
		}
		if (!isLicensePlateVlid()) {
			message+="请输入正确的车牌照号\n";
		}
		if (Engine_id_TextField.getText().isEmpty()) {
			message+="请输入发动机号\n";
		}
		if (Frame_id_TextField.getText().isEmpty()) {
			message+="请输入车架编号\n";
		}
		if (Bus_type_TextField.getText().isEmpty()) {
			message+="请输入车辆类型\n";
		}
		if (Car_population_TextField.getText().isEmpty()) {
			message+="请输入核载人数\n";
		}
		if (Bus_chair_TextField.getText().isEmpty()) {
			message+="请输入车座数量\n";
		}
		
		if (message==null) {
			return true;
		}else{
			return false;
		}
		
	}
}
