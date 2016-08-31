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

}
