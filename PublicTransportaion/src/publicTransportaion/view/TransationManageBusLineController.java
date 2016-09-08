package publicTransportaion.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Cars;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.util.SingleLine;
import publicTransportaion.util.TImeUtil;
import publicTransportaion.util.TimeConverter;

public class TransationManageBusLineController implements ControlledStage,Initializable {
	@FXML
    private Label Bus_No_Label;
    @FXML
    private Label Start_Station_Label;
    @FXML
    private Label End_Station_Label;
    @FXML
    private Label Time_Lage_Label;
    @FXML
    private Label Time_Start_Label;
    @FXML
    private Label Time_End_Label;
    
    @FXML
    private TableView<Bus> Bus_Line_ListTable;
    @FXML
    private TableColumn<Bus, String> Bus_Line_Column;
    
    @SuppressWarnings("unused")
	private StageController myController;
    
    private ObservableList<Bus> busList=FXCollections.observableArrayList();
    
	private void showBusDetails(Bus bus) {
		if (bus == null) {
			Bus_No_Label.setText(null);
			Start_Station_Label.setText(null);
			End_Station_Label.setText(null);
			Time_Lage_Label.setText(null);
			Time_Start_Label.setText(null);
			Time_End_Label.setText(null);
		} else {
			Bus_No_Label.setText(bus.getBusNo());
			Time_Lage_Label.setText(bus.getTimeLag());
			showStartAndEndStation(bus);
			Time_Start_Label.setText(TimeConverter.format(bus.getTimeStart1()));
			Time_End_Label.setText(TimeConverter.format(bus.getTimeEnd1()));

		}
	}
	
	private void showStartAndEndStation(Bus bus){
		List<String> list=SingleLine.parse(bus.getLine());
		String startStation=list.get(0);
		String EndStation=list.get(list.size()-1);
		
		Start_Station_Label.setText(selectStation(startStation));
		End_Station_Label.setText(EndStation);
	}
	
	private String selectStation(String Id) {
		String stationName = null;
		SqlDeloy sqlDeloy=new SqlDeloy();
		Connection connection=sqlDeloy.getConnection();
		try {
			PreparedStatement pStmt=connection.prepareStatement("Select Station_Name from Station_information where Station_ID=?");
			pStmt.setString(1, Id);
			ResultSet rest=pStmt.executeQuery();
			while(rest.next()){
				stationName=rest.getString("Station_Name");
				System.out.println(stationName);
			}
			return stationName;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		connectAndSelectCarsInfor();
		Bus_Line_ListTable.setItems(busList);
		Bus_Line_Column.setCellValueFactory(cellData -> cellData.getValue().getBusNoProperty());

		showBusDetails(null);
    	
    	Bus_Line_ListTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBusDetails(newValue));
	}
	private void connectAndSelectCarsInfor() {
		// TODO Auto-generated method stub
		SqlDeloy sqlDeloy=new SqlDeloy();
    	Connection connection=sqlDeloy.getConnection();
    	busList.clear();
    	
    	try {
    		Statement stmt=connection.createStatement();
	    	String sql="select * from Bus_information";
	    	
	    	ResultSet resultSet=stmt.executeQuery(sql);
	    	while (resultSet.next()) {
				Bus bus=new Bus();
				
				bus.setBusNo(resultSet.getString("Bus_No"));
				bus.setTimeLag(resultSet.getString("timeLag_stirng"));
				bus.setStartTime(resultSet.getTime("timeStart"));
				bus.setTimeEnd(resultSet.getTime("timeEnd"));
			
				
				busList.add(bus);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
