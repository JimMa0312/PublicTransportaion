package publicTransportaion.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import publicTransportaion.util.GPS;

public class Station {
	
	private final StringProperty stationName;
	private final StringProperty stationID;
	private final StringProperty stationAddress;
	private final DoubleProperty StationGPSX;
	private final DoubleProperty StationGPSY;

	public Station() {
		// TODO Auto-generated constructor stub
		this(null);
	}
	
	public Station(String stationName){
		this.stationName=new SimpleStringProperty(stationName);
		this.stationID=new SimpleStringProperty(null);
		this.stationAddress=new SimpleStringProperty(null);
		this.StationGPSX=new SimpleDoubleProperty(0);
		this.StationGPSY=new SimpleDoubleProperty(0);
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
	
	public void setStationGPSX(String X){
		this.StationGPSX.set(Double.parseDouble(X));
	}
	public void setStationGPSX(double X){
		this.StationGPSX.set(X);
	}
	
	public double getStationGPSX(){
		return StationGPSX.get();
	}
	
	public DoubleProperty getStationGPSXProperty(){
		return StationGPSX;
	}
	
	public void setStationGPSY(String Y){
		this.StationGPSY.set(Double.parseDouble(Y));
	}
	public void setStationGPSY(double Y){
		this.StationGPSY.set(Y);
	}
	
	public double getStationGPSY(){
		return StationGPSY.get();
	}
	
	public DoubleProperty getStationGPSYProperty(){
		return StationGPSY;
	}
}
