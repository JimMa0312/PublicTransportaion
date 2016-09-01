package publicTransportaion.model;

import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bus {

	private StringProperty Bus_No;
	private ObjectProperty<LocalTime> timeStart;
	private ObjectProperty<LocalTime> timeEnd;
	private ObjectProperty<LocalTime> timeLage;
	
	public Bus() {
		// TODO Auto-generated constructor stub
		this(null);
	}
	
	public Bus(String bus_No){
		this.Bus_No=new SimpleStringProperty(bus_No);
		this.timeStart=new SimpleObjectProperty<LocalTime>(LocalTime.of(0, 0));
		this.timeEnd=new SimpleObjectProperty<LocalTime>(LocalTime.of(0, 0));
		this.timeLage=new SimpleObjectProperty<LocalTime>(LocalTime.of(0, 0));
	}
	
	public void setBusNo(String subNo) {
		this.Bus_No.set(subNo);
	}
	
	public String getBusNo() {
		return Bus_No.get();
	}
	
	public StringProperty getBusNoProperty() {
		return Bus_No;
	}
	
	public void setStartTime(LocalTime timeStart) {
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
	
	public void setTimeLag(LocalTime timeLag) {
		this.timeLage.set(timeLag);
	}
	
	public LocalTime getTimeLag() {
		return timeLage.get();
	}
	
	public ObjectProperty<LocalTime> getTimeLagProperty() {
		return timeLage;
	}

}
