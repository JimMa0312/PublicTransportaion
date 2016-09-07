package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.model.Cars;

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
    
    @Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }

}
