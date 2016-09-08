package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.model.Station;
import publicTransportaion.sql.SqlDeloy;

public class TransationManageStationController implements ControlledStage,Initializable{
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
    
    @SuppressWarnings("unused")
	private StageController myController;
    
    
    private ObservableList<Station> stationsList=FXCollections.observableArrayList();
    
	private void showStationDetails(Station station) {
		if (station==null) {
			Station_Name_Label.setText(null);
			Station_ID_Label.setText(null);
			Station_Address_Label.setText(null);
			
		}else{
			Station_Name_Label.setText(station.getStationName());
			Station_ID_Label.setText(station.getStationID());
			Station_Address_Label.setText(station.getStationAddress());
			
			
		}
	}
	
	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	showStationDetails(null);
		connectAndSelectStationInfor();

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

}
