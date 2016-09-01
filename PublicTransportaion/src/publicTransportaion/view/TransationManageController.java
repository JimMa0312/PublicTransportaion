package publicTransportaion.view;

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
}
