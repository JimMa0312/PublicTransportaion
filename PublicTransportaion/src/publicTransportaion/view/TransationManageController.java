package publicTransportaion.view;

<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransationManageController {
	@FXML
	private Label Bus_id;			//���ƺ�
	@FXML
	private Label Einge_id;			//���������
	@FXML
	private Label Frame_id;			//���ܱ��
	@FXML
	private Label Bus_type;			//����
	@FXML
	private Label Bus_Chair;		//����
	@FXML
	private Label Can_population;	//��������
	//�����ǡ�������ѡ��ҳ�ı�ǩ
	@FXML
	private Label Bus_No;			//���߱��
	@FXML
	private Label Start_point;		//���
	@FXML
	private Label End_point;		//�յ�
	@FXML
	private Label Stream;			//������·��
	@FXML
	private Label Time_start;		//��෢��ʱ��
	@FXML
	private Label Time_End;			//ĩ�෢��ʱ��
	//�����ǡ����ߡ�ѡ��ҳ�ı�ǩ
	@FXML
	private Label Station_Name;		//վ����
	@FXML
	private Label Station_ID;		//վ����
	@FXML
	private Label Station_Address;	//վ��λ��
	//�����ǡ�վ�㡱��ǩҳ�ı�ǩ
	@FXML
	private TextField neirong;	//������
	@FXML
	private Button Search;		//�����ҡ���ť
	@FXML
	private Label bus_id;		//���ƺ�
	@FXML
	private Label bus_no;		//���߱��
	@FXML
	private Label start_time;	//����ʱ��
	@FXML
	private Label execute_date;	//ִ������
	@FXML
	private Label Use_Line; 	//ʹ����·
	@FXML
	private Label position_information; //λ����Ϣ
	//�����ǡ�������̬��ƻ���ѡ��ҳ�ı�ǩ
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
