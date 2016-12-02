package publicTransportaion.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import publicTransportaion.model.en.GenderEnum;
import publicTransportaion.model.en.Jurisdtion;
import publicTransportaion.safety.Coder;

public class User {
	
	private StringProperty UserId;
	private StringProperty FirstName;
	private StringProperty SecondName;
	private StringProperty PassWord;
	private StringProperty TEL;
	private ObjectProperty<Jurisdtion> ControlLimit;
	private ObjectProperty<GenderEnum> garde;
	
	

	public User() {
		UserId=new SimpleStringProperty();
		PassWord=new SimpleStringProperty();
		TEL=new SimpleStringProperty("111111");
		FirstName=new SimpleStringProperty();
		SecondName=new SimpleStringProperty();
		ControlLimit=new SimpleObjectProperty<Jurisdtion>(Jurisdtion.admin);
		garde=new SimpleObjectProperty<GenderEnum>(GenderEnum.sir);
	}

	public StringProperty getUserIdProperty() {
		return UserId;
	}

	public void setUserIdProperty(StringProperty userId) {
		UserId = userId;
	}

	public StringProperty getFirstNameProperty() {
		return FirstName;
	}

	public void setFirstNameProperty(StringProperty firstName) {
		FirstName = firstName;
	}

	public StringProperty getSecondNameProperty() {
		return SecondName;
	}

	public void setSecondNameProperty(StringProperty secondName) {
		SecondName = secondName;
	}
	
	public StringProperty getPassWordProperty() {
		return PassWord;
	}

	public void setPassWordProperty(StringProperty passWord) {
		PassWord = passWord;
	}

	public StringProperty getTELProperty() {
		return TEL;
	}

	public void setTELProperty(StringProperty tEL) {
		TEL = tEL;
	}

	public ObjectProperty<Jurisdtion> getControlLimitProperty() {
		return ControlLimit;
	}

	public void setControlLimitProperty(ObjectProperty<Jurisdtion> controlLimit) {
		ControlLimit = controlLimit;
	}

	public ObjectProperty<GenderEnum> getGardeProperty() {
		return garde;
	}

	public void setGardeProperty(ObjectProperty<GenderEnum> garde) {
		this.garde = garde;
	}
	
	public String getUserId() {
		return UserId.get();
	}
	
	public void setUserId(String userId) {
		this.UserId.set(userId);
	}
	
	public String getFirstName() {
		return FirstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.FirstName.set(firstName);
	}
	
	public String getSecondName() {
		return SecondName.get();
	}
	
	public void setSecondName(String secondName){
		this.SecondName.set(secondName);
	}
	
	public String getPwd() {
		return PassWord.get();
	}
	
	public void setPwd(String passWord) {
		this.PassWord.set(passWord);
	}
	
	public String getTel() {
		return TEL.get();
	}
	
	public void setTel(String Tel) {
		this.TEL.set(Tel);
	}
	
	public Jurisdtion getControlLimit() {
		return ControlLimit.get();
	}
	
	public void getControlLimit(Jurisdtion controlLimit) {
		this.ControlLimit.set(controlLimit);
	}
	
	public GenderEnum getGarde() {
		return garde.get();
	}
	
	public void setGarde(GenderEnum garde) {
		this.garde.set(garde);
	}
	
	public static String encrytpMD5PWD(String Pwd){
		try {
			return Coder.encryptBASE64(Coder.encryptMD5(Pwd.getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
