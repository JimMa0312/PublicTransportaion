package publicTransportaion.model;

import java.sql.Time;
import java.time.LocalTime;

import com.sun.media.jfxmedia.control.VideoDataBuffer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bus {

	private StringProperty Bus_No;
	//private ObjectProperty<LocalTime> timeStart;
	//private ObjectProperty<LocalTime> timeEnd;
//	private ObjectProperty<Time>timeStart;
//	private ObjectProperty<Time> timeEnd;
	private StringProperty timeLag_stirng;
	
	public Bus() {
		// TODO Auto-generated constructor stub
		this(null);
	}
	
	public Bus(String bus_No){
		this.Bus_No=new SimpleStringProperty(bus_No);
		//this.timeStart=new SimpleObjectProperty<LocalTime>(LocalTime.of(0, 0));
		//this.timeEnd=new SimpleObjectProperty<LocalTime>(LocalTime.of(0, 0));
		//this.timeLage=new SimpleObjectProperty<LocalTime>(LocalTime.of(0, 0));
		this.timeLag_stirng=new SimpleStringProperty();
	}
	
	public void setBusNo(String busNo) {
		this.Bus_No.set(busNo);
	}
	
	public String getBusNo() {
		return Bus_No.get();
	}
	
	public StringProperty getBusNoProperty() {
		return Bus_No;
	}
	
/*	public void setStartTime(LocalTime timeStart) {
		this.timeStart.set(timeStart);
	}
	
	public LocalTime getTimeStart() {
		return timeStart.get();
	}
	
	public ObjectProperty<LocalTime> getTimeStartProperty() {
		return timeStart;
	}
	
	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd.set(timeEnd);
	}
	public LocalTime getTimeEnd() {
		return timeEnd.get();
	}
	
	public ObjectProperty<LocalTime> getTimeEndProperty() {
		return timeEnd;
	}
*/					//！！注意：Time类型的数据无法转为LocalTime类型，暂时屏蔽掉以上代码
/*	public void setTimeStart(Time t)
	{
		this.timeStart.set(t);
	}
	public ObjectProperty<Time> getTimeStart()
	{
		return this.timeStart;
	}
	public void setTimeEnd(Time t)
	{
		this.timeEnd.set(t);
	}
	public ObjectProperty<Time> getTimeEnd()
	{
		return this.timeEnd;
	}
	*/
	
	public void setTimeLag(String timeLag){
		this.timeLag_stirng.set(timeLag);
	}
	
	public String getTimeLag(){
		return this.timeLag_stirng.get();
	}
	
	public StringProperty getStringTimeLagProperty(){
		return timeLag_stirng;
	}
}
