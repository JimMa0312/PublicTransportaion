package publicTransportaion.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import publicTransportaion.MainApp;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Station;
import publicTransportaion.sql.SqlDeloy;

public class ShowBusInforMationController {
	@FXML
	private Label bus_no;
	@FXML
	private Label position_information;
	@FXML
	private Label next_station;
	@FXML
	private Label how_many_station;
	@FXML
	private TableView<Bus>bustable;
	@FXML
	private TableColumn<Bus,String>firstcolumn;
	@FXML
	private TableColumn<Bus,String>secondcolumn;
	@FXML
	private Label e1;
	@FXML
	private Label e2;
	//
	@FXML
	private TextField neirong1;
	@FXML
	private TableView<Station>stationtable;
	@FXML
	private TableColumn<Station,String>zhanming;
	@FXML
	private TableColumn<Station, String>dizhi;
	@FXML
	private TextField neirong2;
	private ObservableList<Bus> busdata = FXCollections.observableArrayList();
	private ObservableList<Station> stationdata = FXCollections.observableArrayList();
	//创建顺序表
	
	private String Bus_No;
	private String Station_Name;
	@SuppressWarnings("unused")
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize() throws SQLException {
		ShowBusInfor(null);
		//
		e1.setText("");
		e2.setText("");
		SqlDeloy s1 = new SqlDeloy();
		String sql1="select * from Station_information";
		Connection c1=s1.getConnection();
		PreparedStatement ps1=c1.prepareStatement(sql1);
		ResultSet result1=ps1.executeQuery();
		if(result1.next()){
			System.out.println("站点编号：\t站名：\t\t地址：\t\tGPS："); 
			do
			{
			Station station=new Station();
			station.setStationName(result1.getString(2));
			station.setStationAddress(result1.getString(3));
			stationdata.add(station);		//将Station的匿名对象添加入ObservableList
			stationtable.setItems(getStationData());
			}while(result1.next());
		}
		//
		//
		SqlDeloy s2 = new SqlDeloy();
		getneirong2();
		String sql2="select * from Bus_information";
		Connection c2=s2.getConnection();
		PreparedStatement ps2=c2.prepareStatement(sql2);
		ResultSet result2=ps2.executeQuery();
		//查询到符合条件的记录
		if(result2.next()){
			e2.setText("");
			System.out.println("班线编号：\t早班发车时间：\t\t晚班发车时间：\t\t发车间隔："); 
			do
			{
			Bus bus=new Bus();
			bus.setBusNo(result2.getString(1));
			bus.setTimeLag(result2.getString(4));
			busdata.add(bus);		//将bus的匿名对象添加入ObservableList
			bustable.setItems(getBusData());
			}while(result2.next());
		}
		//
		firstcolumn.setCellValueFactory(cellData -> cellData.getValue().getBusNoProperty());
		secondcolumn.setCellValueFactory(cellData -> cellData.getValue().getStringTimeLagProperty());
		zhanming.setCellValueFactory(cellData -> cellData.getValue().getStationNameProperty());
		dizhi.setCellValueFactory(cellData -> cellData.getValue().getStationAddressProperty());
		//将搜索结果框中的数据进行初始化
		bustable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
					try {
						ShowBusInfor(newValue);
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				});
	}

	private void ShowBusInfor(Bus bus) throws SQLException {
		if(bus==null)
		{
			bus_no.setText(null);
			position_information.setText(null);
			next_station.setText(null);
			how_many_station.setText(null);
		}
		else{
			bus_no.setText(bus.getBusNo());
			//此处往下必须通过链接数据库动态获取数据
			SqlDeloy s3 = new SqlDeloy();
			String sql3="select GPS from SID where Bus_No=?";
			Connection c1=s3.getConnection();
			PreparedStatement ps3=c1.prepareStatement(sql3);
			ps3.setString(1,bus.getBusNo());
			ResultSet result3=ps3.executeQuery();
			if(result3.next()){ 
				position_information.setText(result3.getString(1));
			}	
			else{
				position_information.setText(null);
			}
			result3.close();
			ps3.close();
			c1.close();
		}		
	}
	@FXML
	private void Search2() throws SQLException
	{
		SqlDeloy s2 = new SqlDeloy();
		getneirong2();
		String sql="select * from Bus_information where Bus_No like ?";
		Connection c2=s2.getConnection();
		PreparedStatement ps=c2.prepareStatement(sql);
		ps.setString(1, Bus_No+"%");
		ResultSet result=ps.executeQuery();
		//查询到符合条件的记录
		if(result.next()){
			busdata.clear();
			e2.setText("");
			System.out.println("班线编号：\t早班发车时间：\t\t晚班发车时间：\t\t发车间隔："); 
			do
			{
			Bus bus=new Bus();
			System.out.print(result.getString(1) + "\t\t");
			bus.setBusNo(result.getString(1));
			System.out.print(result.getString(2) + "\t");
			//bus.setTimeStart(result.getTime(2));
			System.out.print(result.getString(3)+"\t");
			//bus.setTimeEnd(result.getTime(3));
			System.out.println(result.getString(4)+"分钟");
			bus.setTimeLag(result.getString(4));
			busdata.add(bus);		//将bus的匿名对象添加入ObservableList
			bustable.setItems(getBusData());
			}while(result.next());
		}
		else{
			busdata.clear();
			e2.setText("抱歉，没有查询到相关班线。");
			System.out.println("抱歉，没有查询到相关信息。");
		}
		ps.close();
		s2.shotDownCon();
	}
	private void getneirong2()
	{
		Bus_No=neirong2.getText();
	}
    public ObservableList<Bus> getBusData() {
        return busdata;
    }
   @FXML
   private void Search1() throws SQLException
   {
		SqlDeloy s1 = new SqlDeloy();
		getneirong1();
		String sql="select * from Station_information where Station_Name like ?";
		Connection c1=s1.getConnection();
		PreparedStatement ps=c1.prepareStatement(sql);
		ps.setString(1,Station_Name+"%");
		ResultSet result=ps.executeQuery();
		//查询到符合条件的记录数	
		if(result.next()){
			//此处要先对表格进行刷新
			stationdata.clear();
			e1.setText("");
			System.out.println("站点编号：\t站名：\t\t地址：\t\tGPS："); 
			do
			{
			Station station=new Station();
			System.out.print(result.getString(1) + "\t");
			System.out.print(result.getString(2) + "\t\t");
			station.setStationName(result.getString(2));
			System.out.print(result.getString(3)+"\t");
			station.setStationAddress(result.getString(3));
			System.out.println(result.getString(4));
			stationdata.add(station);		//将Station的匿名对象添加入ObservableList
			stationtable.setItems(getStationData());
			}while(result.next());
		}
		else{
			stationdata.clear();
			e1.setText("抱歉，没有查询到相关站点。");
			System.out.println("抱歉，没有查询到相关信息。");
		}
		ps.close();
		s1.shotDownCon();
   }
	private void getneirong1()
	{
		Station_Name=neirong1.getText();
	}
   public ObservableList<Station> getStationData() {
       return stationdata;
   }
   
}
