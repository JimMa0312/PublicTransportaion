package publicTransportaion.model;

import java.sql.Time;
import java.time.*;
import publicTransportaion.model.Line;

import com.sun.media.jfxmedia.control.VideoDataBuffer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bus {

	private StringProperty Bus_No;
	private ObjectProperty<Time> timeStart;
	private ObjectProperty<Time> timeEnd;
	private StringProperty timeLag_stirng;
	private SimpleStringProperty line;
	public Bus() {
		// TODO Auto-generated constructor stub
		this(null);
	}
	
	public Bus(String bus_No){
		this.Bus_No=new SimpleStringProperty(bus_No);
		this.timeStart=new SimpleObjectProperty<Time>();
		this.timeEnd=new SimpleObjectProperty<Time>();
		//this.timeLage=new SimpleObjectProperty<LocalTime>(LocalTime.of(0, 0));
		this.timeLag_stirng=new SimpleStringProperty();
		this.line = new SimpleStringProperty();
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
	
	public void setStartTime(Time time) {
		this.timeStart.set(time);
	}
	
	public Time getTimeStart1() {
		return timeStart.get();
	}
	
	public ObjectProperty<Time> getTimeStartProperty() {
		return timeStart;
	}
	
	public void setTimeEnd(Time timeEnd) {
		this.timeEnd.set(timeEnd);
	}
	public Time getTimeEnd1() {
		return timeEnd.get();
	}
	
	public ObjectProperty<Time> getTimeEndProperty() {
		return timeEnd;
	}
					//！！注意：Time类型的数据无法转为LocalTime类型，暂时屏蔽掉以上代码
/*	public void setTimeStart(LocalTime t)
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
	}*/
	
	
	public void setTimeLag(String timeLag){
		this.timeLag_stirng.set(timeLag);
	}
	
	public String getTimeLag(){
		return this.timeLag_stirng.get();
	}
	
	public StringProperty getStringTimeLagProperty(){
		return timeLag_stirng;
	}

	

	public void setLine(String line) {
		this.line.set(line);
	}
	public String getLine() {
		return this.line.get();
	}
	public StringProperty getStringLineProperty() {
		return line;
	}
	
}
