package publicTransportaion.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cars {
	private final StringProperty licensePlate;//�����պ�
	private final StringProperty eingeId;//��������
	private final StringProperty frameId;//���ܱ��
	private final StringProperty busType;//����
	private final IntegerProperty busChair;//������λ
	private final IntegerProperty carPopulation;//������������

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
	
	public void setBusChair(int busChair) {
		this.busChair.set(busChair);
	}
	
	public int getBusChair() {
		return busChair.get();
	}
	
	public IntegerProperty getBusChairProperty() {
		return busChair;
	}
	
	public void setCarPopulation(String i) {
		this.carPopulation.set(Integer.parseInt(i));
	}
	
	public void setCarPopulation(int i) {
		this.carPopulation.set(i);
	}

	public int getCarPopulation() {
		return carPopulation.get();
	}
	
	public IntegerProperty getCarPopulationProperty() {
		return carPopulation;
	}
}
