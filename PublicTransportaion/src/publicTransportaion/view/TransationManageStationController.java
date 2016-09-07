package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.model.Station;

public class TransationManageStationController implements ControlledStage{
	@FXML
    private Label Station_Name_Label;
    @FXML
    private Label Station_ID_Label;
    @FXML
    private Label Station_Address_Label;
	
	@FXML
    private TableView<Station> stationsTable;
    @FXML
    private TableColumn<Station, String> Station_ID_Column;
    @FXML
    private TableColumn<Station, String> Station_Name_Column;
    
    private StageController myController;
    
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
