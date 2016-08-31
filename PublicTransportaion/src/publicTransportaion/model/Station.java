package publicTransportaion.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Station {
	
	private final StringProperty stationName;
	private final StringProperty stationID;
	private final StringProperty stationAddress;
	private final StringProperty stationGPS;

	public Station() {
		// TODO Auto-generated constructor stub
		this(null);
	}
	
	public Station(String stationName){
		this.stationName=new SimpleStringProperty(stationName);
		this.stationID=new SimpleStringProperty(null);
		this.stationAddress=new SimpleStringProperty(null);
		this.stationGPS=new SimpleStringProperty(null);
	}
	
	public void setStationName(String stationName) {
		this.stationName.set(stationName);
	}
	
	public String getStationName() {
		return stationName.get();
	}
	
	public StringProperty getStationNameProperty() {
		return stationName;
	}
	
	public void setStationID(String stationId) {
		this.stationID.set(stationId);
	}

	public String getStationID() {
		return stationID.get();
	}
	
	public StringProperty getStationIDProperty() {
		return stationID;
	}
	
	public void setStationAddress(String stationAddress) {
		this.stationAddress.set(stationAddress);
	}
	
	public String getStationAddress() {
		return stationAddress.get();
	}
	
	public StringProperty getStationAddressProperty() {
		return stationAddress;
	}
	
	public void setStationGPS(String GPSValue) {
		this.stationGPS.set(GPSValue);
	}
	public String getStationGPS() {
		return stationGPS.get();
	}
	
	public StringProperty getStationGPSProperty() {
		return stationGPS;
	}
}
