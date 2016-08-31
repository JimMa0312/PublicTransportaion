package publicTransportaion.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cars {
	private final StringProperty licensePlate;//车牌照号
	private final StringProperty eingeId;//发动机号
	private final StringProperty frameId;//车架编号
	private final StringProperty busType;//车型
	private final IntegerProperty busChair;//车辆座位
	private final IntegerProperty carPopulation;//车辆核载人数

	public Cars() {
		// TODO Auto-generated constructor stub
		this(null);
	}
	
	public Cars(String licensePlate){
		this.licensePlate=new SimpleStringProperty(licensePlate);
		this.eingeId=new SimpleStringProperty(null);
		this.frameId=new SimpleStringProperty(null);
		this.busType=new SimpleStringProperty(null);
		this.busChair=new SimpleIntegerProperty(0);
		this.carPopulation=new SimpleIntegerProperty(0);
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate.set(licensePlate);
	}
	
	public String getLicensePlate() {
		return licensePlate.get();
	}
	
	public StringProperty getLicensePlateProperty() {
		return licensePlate;
	}
	
	public void setEingeId(String eingeId) {
		this.eingeId.set(eingeId);
	}
	
	public String getEingeId() {
		return eingeId.get();
	}
	
	public StringProperty getEingeIdProperty() {
		return eingeId;
	}
	
	public void setFrameId(String frameId) {
		this.frameId.set(frameId);
	}
	
	public String getFrameId() {
		return frameId.get();
	}
	public StringProperty getFrameIdProperty() {
		return frameId;
	}
	
	public void setBusType(String busType) {
		this.busType.set(busType);
	}
	public String getBusType() {
		return busType.get();
	}
	
	public StringProperty getBusTypeProperty() {
		return busType;
	}
	
	public void setBusChair(String busChair) {
		this.busChair.set(Integer.parseInt(busChair));
	}
	
	public int getBusChair() {
		return busChair.get();
	}
	
	public IntegerProperty getBusChairProperty() {
		return busChair;
	}
	
	public void setCarPopulation(String carPopulation) {
		this.carPopulation.set(Integer.parseInt(carPopulation));
	}

	public int getCarPopulation() {
		return carPopulation.get();
	}
	
	public IntegerProperty getCarPopulationProperty() {
		return carPopulation;
	}
}
