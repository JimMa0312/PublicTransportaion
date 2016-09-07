package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.model.Bus;

public class TransationManageBusLineController implements ControlledStage {
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
    private TableView<Bus> Bus_Line_ListTable;
    @FXML
    private TableColumn<Bus, String> Bus_Line_Column;
    
    private StageController myController;
    
    public void initializeBus(){
//		Bus_Line_Column.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());
    	
    	showBusDetails(null);
    	
    	Bus_Line_ListTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBusDetails(newValue));
    }
	private void showBusDetails(Bus bus) {
		return;
	}
	
	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }

}
