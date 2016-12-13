package publicTransportaion.model;

import java.sql.Time;
import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bus {

	private StringProperty Bus_No;
	private StringProperty timeStart;
	private StringProperty timeEnd;
	private StringProperty timeLag_stirng;

	public Bus() {
		this(null);
	}

	public Bus(String bus_No) {
		this.Bus_No = new SimpleStringProperty(bus_No);
		this.timeStart = new SimpleStringProperty();
		this.timeEnd = new SimpleStringProperty();
		this.timeLag_stirng = new SimpleStringProperty();
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

	public void setStartTime(String time) {
		this.timeStart.set(time);
	}

	public String getTimeStart1() {
		return timeStart.get();
	}

	public StringProperty getTimeStartProperty() {
		return timeStart;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd.set(timeEnd);
	}

	public String getTimeEnd1() {
		return timeEnd.get();
	}

	public StringProperty getTimeEndProperty() {
		return timeEnd;
	}
	// ！！注意：Time类型的数据无法转为LocalTime类型，暂时屏蔽掉以上代码
	/*
	 * public void setTimeStart(LocalTime t) { this.timeStart.set(t); } public
	 * ObjectProperty<Time> getTimeStart() { return this.timeStart; } public
	 * void setTimeEnd(Time t) { this.timeEnd.set(t); } public
	 * ObjectProperty<Time> getTimeEnd() { return this.timeEnd; }
	 */

	public void setTimeLag(String timeLag) {
		this.timeLag_stirng.set(timeLag);
	}

	public String getTimeLag() {
		return this.timeLag_stirng.get();
	}

	public StringProperty getStringTimeLagProperty() {
		return timeLag_stirng;
	}

}
