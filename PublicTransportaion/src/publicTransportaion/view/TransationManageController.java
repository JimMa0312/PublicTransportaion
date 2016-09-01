package publicTransportaion.view;

<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransationManageController {
	@FXML
	private Label Bus_id;			//车牌号
	@FXML
	private Label Einge_id;			//发动机编号
	@FXML
	private Label Frame_id;			//车架编号
	@FXML
	private Label Bus_type;			//车型
	@FXML
	private Label Bus_Chair;		//车座
	@FXML
	private Label Can_population;	//核载人数
	//以上是“车辆”选项页的标签
	@FXML
	private Label Bus_No;			//班线编号
	@FXML
	private Label Start_point;		//起点
	@FXML
	private Label End_point;		//终点
	@FXML
	private Label Stream;			//上下行路线
	@FXML
	private Label Time_start;		//早班发车时间
	@FXML
	private Label Time_End;			//末班发车时间
	//以上是“班线”选项页的标签
	@FXML
	private Label Station_Name;		//站点名
	@FXML
	private Label Station_ID;		//站点编号
	@FXML
	private Label Station_Address;	//站点位置
	//以上是“站点”标签页的标签
	@FXML
	private TextField neirong;	//搜索框
	@FXML
	private Button Search;		//“查找”按钮
	@FXML
	private Label bus_id;		//车牌号
	@FXML
	private Label bus_no;		//班线编号
	@FXML
	private Label start_time;	//发车时间
	@FXML
	private Label execute_date;	//执行日期
	@FXML
	private Label Use_Line; 	//使用线路
	@FXML
	private Label position_information; //位置信息
	//以上是“发车动态与计划”选项页的标签
=======


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import publicTransportaion.MainApp;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Cars;
import publicTransportaion.model.Station;

public class TransationManageController {
	@FXML
    private Label License_Plate_Label;
    @FXML
    private Label Engine_id_Label;
    @FXML
    private Label Frame_id_Label;
    @FXML
    private Label Bus_type_Label;
    @FXML
    private Label Can_population_Label;
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
    
    
    
    private MainApp mainApp ;
	   
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return;
	}
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;      
    }

>>>>>>> branch 'master' of https://github.com/JimMa0312/PublicTransportaion
}
