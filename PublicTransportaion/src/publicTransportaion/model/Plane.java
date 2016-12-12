package publicTransportaion.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plane {

	private StringProperty busNo;
	private StringProperty stationId;
	private StringProperty License_Plate;
	private StringProperty Engine_start;
	private StringProperty GPS;
	private StringProperty CarOut_Date;
	private BooleanProperty Line_Layer;
	public Plane() {
		busNo=new SimpleStringProperty();
		stationId=new SimpleStringProperty();
		License_Plate=new SimpleStringProperty();
		Engine_start=new SimpleStringProperty();
		GPS=new SimpleStringProperty();
		CarOut_Date=new SimpleStringProperty();
		Line_Layer=new SimpleBooleanProperty();
	}
	public StringProperty getBusNoProperty() {
		return busNo;
	}
	public void setBusNoProperty(StringProperty busNo) {
		this.busNo = busNo;
	}
	public StringProperty getStationIdProperty() {
		return stationId;
	}
	public void setStationIdProperty(StringProperty stationId) {
		this.stationId = stationId;
	}
	public StringProperty getLicense_PlateProperty() {
		return License_Plate;
	}
	public void setLicense_PlateProperty(StringProperty license_Plate) {
		License_Plate = license_Plate;
	}
	public StringProperty getEngine_startProperty() {
		return Engine_start;
	}
	public void setEngine_startProperty(StringProperty engine_start) {
		Engine_start = engine_start;
	}
	public StringProperty getGPSProperty() {
		return GPS;
	}
	public void setGPSProperty(StringProperty gPS) {
		GPS = gPS;
	}
	public StringProperty getCarOut_DateProperty() {
		return CarOut_Date;
	}
	public void setCarOut_DateProperty(StringProperty carOut_Date) {
		CarOut_Date = carOut_Date;
	}
	public BooleanProperty getLine_LayerProperty() {
		return Line_Layer;
	}
	public void setLine_LayerProperty(BooleanProperty line_Layer) {
		Line_Layer = line_Layer;
	}
	
	public String getBusNo() {
		return busNo.get();
	}
	
	public void setBusNo(String busNo) {
		this.busNo.set(busNo);
	}
	
	public String getStationId() {
		return stationId.get();
	}
	
	public void setStationId(String stationId) {
		this.stationId.set(stationId);
	}
	public String getLicense_Plate() {
		return License_Plate.get();
	}
	public void setLicense_Plate(String license_Plate) {
		License_Plate.set(license_Plate);
	}
	public String getEngine_start() {
		return Engine_start.get();
	}
	public void setEngine_start(String engine_start) {
		Engine_start.set(engine_start);;
	}
	public String getCarOut_Date() {
		return CarOut_Date.get();
	}
	public void setCarOut_Date(String carOut_Date) {
		CarOut_Date.set(carOut_Date);;
	}
	public Boolean getLine_Layer() {
		return Line_Layer.get();
	}
	public void setLine_Layer(boolean line_Layer) {
		Line_Layer.set(line_Layer);
	}
	public String getGPS() {
		return GPS.get();
	}
	public void setGPS(String gPS) {
		GPS.set(gPS);
	}	
	
	
}
