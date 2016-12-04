package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

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
import publicTransportaion.model.en.PatternEnum;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.util.Patterner;

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
			License_Plate_TextField.setText("");
			Engine_id_TextField.setText("");
			Frame_id_TextField.setText("");
			Bus_type_TextField.setText("");
			Car_population_TextField.setText("");
			Bus_chair_TextField.setText("");
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
		Frame_id_error.setTextFill(Color.RED);
		Bus_type_error.setTextFill(Color.RED);
		bus_chair_error.setTextFill(Color.RED);
		Car_population_error.setTextFill(Color.RED);
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
		initErrorMessageTextFill();
		initErrorMessage();

		showCarsDetails(null);

		CarsTable.setItems(carsList);
		License_Plate_Column.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());

		CarsTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCarsDetails(newValue));
	}

	@FXML
	private void handleNewCars() {
		Cars newCar = new Cars();
		if (isInputVlid()) {
			initErrorMessage();
			newCar.setLicensePlate(License_Plate_TextField.getText());
			newCar.setEingeId(Engine_id_TextField.getText());
			newCar.setFrameId(Frame_id_TextField.getText());
			newCar.setBusType(Bus_type_TextField.getText());
			newCar.setBusChair(Bus_chair_TextField.getText());
			newCar.setCarPopulation(Car_population_TextField.getText());
			if (InsertIntoSql(newCar)) {
				carsList.add(newCar);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("数据库上传错误");
				alert.setHeaderText("上聚酷新建信息错误");
				alert.setContentText("数据可能重复");
			}
		}
	}

	@FXML
	private void handleEditCars() {
		Cars editCar = CarsTable.getSelectionModel().getSelectedItem();
		if (isInputVlid()) {
			initErrorMessage();
			if (UpDateSql()) {
				editCar.setLicensePlate(License_Plate_TextField.getText());
				editCar.setEingeId(Engine_id_TextField.getText());
				editCar.setFrameId(Frame_id_TextField.getText());
				editCar.setBusType(Bus_type_TextField.getText());
				editCar.setBusChair(Bus_chair_TextField.getText());
				editCar.setCarPopulation(Car_population_TextField.getText());
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("数据库上传错误");
				alert.setHeaderText("上聚酷新建信息错误");
				alert.setContentText("数据可能重复");
			}
		}
	}

	@FXML
	private void handleDeleteCars() {
		int selectedIndex = CarsTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Cars cars = carsList.get(selectedIndex);

			if (DeleteCarsInformationfromSql(cars.getLicensePlate())) {
				CarsTable.getItems().remove(selectedIndex);
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

	private boolean isInputVlid() {
		boolean message = true;
		if (License_Plate_TextField.getText() == null || License_Plate_TextField.getText().isEmpty()) {
			License_Plate_Error.setText("请输入车牌照号");
			message = false;
		}
		if (!Patterner.StringMatch(PatternEnum.vehicleNoStyle, License_Plate_TextField.getText())) {
			License_Plate_Error.setText("请输入正确的车牌照号");
			message = false;
		}
		if (IsCommedwhiLP()) {
			License_Plate_Error.setText("输入的车牌照重复请重新输入");
			message = false;
		}
		if (Engine_id_TextField.getText().isEmpty() || Engine_id_TextField.getText() == null) {
			Engine_id_error.setText("请输入发动机号");
			message = false;
		}
		if (Frame_id_TextField.getText().isEmpty() || Frame_id_TextField.getText() == null) {
			Frame_id_error.setText("请输入车架编号");
			message = false;
		}
		if (Bus_type_TextField.getText().isEmpty() || Bus_type_TextField.getText() == null) {
			Bus_type_error.setText("请输入车辆类型");
			message = false;
		}
		if (Car_population_TextField.getText().isEmpty() || Car_population_TextField.getText() == null) {
			Car_population_error.setText("请输入核载人数");
			message = false;
		}
		if (Bus_chair_TextField.getText().isEmpty() || Bus_chair_TextField.getText() == null) {
			bus_chair_error.setText("请输入车座数量");
			message = false;
		}

		return message;
	}

	private boolean IsCommedwhiLP() {
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();

		try {
			PreparedStatement pStmt = connection
					.prepareStatement("Select License_Plate as num from Car_information where License_Plate=?");
			pStmt.setString(1, License_Plate_TextField.getText());
			ResultSet res = pStmt.executeQuery();
			boolean isComLP;
			if (res.next()) {
				isComLP = true;
			} else {
				isComLP = false;
			}

			res.close();
			pStmt.close();
			sqlDeloy.shotDownCon();
			return isComLP;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	private boolean InsertIntoSql(Cars cars) {
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();

		try {
			PreparedStatement pStmt = connection.prepareStatement(
					"INSERT INTO Car_information (License_Plate,Einge_id,Frame_id,Bus_tyoe,Can_population,Bus_Chair) VALUES (?,?,?,?,?,?)");
			pStmt.setString(1, cars.getLicensePlate());
			pStmt.setString(2, cars.getEingeId());
			pStmt.setString(3, cars.getFrameId());
			pStmt.setString(4, cars.getBusType());
			pStmt.setInt(5, cars.getBusChair());
			pStmt.setInt(6, cars.getCarPopulation());
			int row = pStmt.executeUpdate();
			if (row > 0) {
				return true;
			}
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private boolean UpDateSql() {
		SqlDeloy sqlDeloy = new SqlDeloy();
		Connection connection = sqlDeloy.getConnection();

		try {
			PreparedStatement pStmt = connection.prepareStatement(
					"UPDATE Car_information SET License_Plate=?,Einge_id=?,Frame_id=?,Bus_tyoe=?,Can_population=?,Bus_Chair =? where License_Plate=?");
			pStmt.setString(1, License_Plate_TextField.getText());
			pStmt.setString(2, Engine_id_TextField.getText());
			pStmt.setString(3, Frame_id_TextField.getText());
			pStmt.setString(4, Bus_type_TextField.getText());
			pStmt.setInt(5, Integer.parseInt(Car_population_TextField.getText()));
			pStmt.setInt(6, Integer.parseInt(Bus_chair_TextField.getText()));
			pStmt.setString(7, License_Plate_TextField.getText());
			int row = pStmt.executeUpdate();
			if (row > 0) {
				return true;
			}
			pStmt.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
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
			connection.close();
			sqlDeloy.shotDownCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isOk;
	}
}
