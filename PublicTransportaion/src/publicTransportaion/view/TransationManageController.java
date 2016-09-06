package publicTransportaion.view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Cars;
import publicTransportaion.model.Station;

public class TransationManageController implements ControlledStage {
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
    private Label Bus_No_Label;
    @FXML
    private Label Start_Station_Label;
    @FXML
    private Label End_Station_Label;
    @FXML
    private Label Route_Selection_Label;
    @FXML
    private Label Time_Start_Label;
    @FXML
    private Label Time_End_Label;
    
    @FXML
    private Label Station_Name_Label;
    @FXML
    private Label Station_ID_Label;
    @FXML
    private Label Station_Address_Label;
    
    
    @FXML
    private TableView<Cars> CarsTable;
    @FXML
    private TableColumn<Cars, String> License_Plate_Column;
    
    @FXML
    private TableView<Bus> Bus_Line_ListTable;
    @FXML
    private TableColumn<Bus, String> Bus_Line_Column;
 
    @FXML
    private TableView<Station> stationsTable;
    @FXML
    private TableColumn<Station, String> Station_ID_Column;
    @FXML
    private TableColumn<Station, String> Station_Name_Column;
    
    private StageController myController;
    
	   
    public void initializeCars(){
    	License_Plate_Column.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());
    	
    	showCarsDetails(null);
    	
    	CarsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarsDetails(newValue));
    }
    
	private void showCarsDetails(Cars cars) {
		// TODO Auto-generated method stub
		return;
	}
	
	public void initializeBus(){
//		Bus_Line_Column.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());
    	
    	showCarsDetails(null);
    	
    	Bus_Line_ListTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBusDetails(newValue));
    }
	private void showBusDetails(Bus bus) {
		return;
	}
	
	public void initializeStation(){
		Station_ID_Column.setCellValueFactory(cellData -> cellData.getValue().getStationIDProperty());
		Station_Name_Column.setCellValueFactory(cellData -> cellData.getValue().getStationNameProperty());
		
    	showStationDetails(null);
    	
    	stationsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStationDetails(newValue));
    }
    
	private void showStationDetails(Station station) {
		return;
	}

	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
	}

}
