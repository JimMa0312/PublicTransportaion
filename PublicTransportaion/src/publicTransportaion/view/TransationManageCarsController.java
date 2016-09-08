package publicTransportaion.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.model.Cars;
import publicTransportaion.sql.SqlDeloy;

public class TransationManageCarsController implements ControlledStage {
	@FXML
    private Label License_Plate_Label;
    @FXML
    private Label Engine_id_Label;
    @FXML
    private Label Frame_id_Label;
    @FXML
    private Label Bus_type_Label;
    @FXML
    private Label Car_population_Label;
    @FXML
    private Label Bus_chair_Label;
    
    @FXML
    private TableView<Cars> CarsTable;
    @FXML
    private TableColumn<Cars, String> License_Plate_Column;
    
    @SuppressWarnings("unused")
	private StageController myController;
    
    private ObservableList<Cars> carsList=FXCollections.observableArrayList();
    
    public void initializeCars(){
    	connectAndSelectCarsInfor();
    	License_Plate_Column.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());
    	
    	showCarsDetails(null);
    	
    	CarsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarsDetails(newValue));
    }
    private void showCarsDetails(Cars cars) {
		if (cars==null) {
			
		}
	}
    
    @Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }
    
	private void connectAndSelectCarsInfor(){
    	SqlDeloy sqlDeloy=new SqlDeloy();
    	Connection connection=sqlDeloy.getConnection();
    	carsList.clear();
    	
    	try {
			Statement stmt=connection.createStatement();
	    	String sql="select * from Car_information";
	    	
	    	ResultSet resultSet=stmt.executeQuery(sql);
	    	while (resultSet.next()) {
				Cars car=new Cars();
				car.setLicensePlate(resultSet.getString("License_Plate"));
				car.setEingeId(resultSet.getString("Einge_id"));
				car.setFrameId(resultSet.getString("Frame_id"));
				car.setBusType(resultSet.getString("Bus_type"));
				car.setCarPopulation(resultSet.getInt("Can_population"));
				car.setBusChair(resultSet.getInt("Bus_Chair"));
				
				carsList.add(car);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
