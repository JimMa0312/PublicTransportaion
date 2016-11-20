package publicTransportaion.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import publicTransportaion.sql.SqlCon;

@XmlRootElement
public class DBConfig {
	@XmlElement
	private static StringProperty publicUserName;
	@XmlElement
	private static StringProperty privateUserName;
	@XmlElement
	private static StringProperty publicKeyPwd;
	@XmlElement
	private static StringProperty privateKeyPwd;
	@XmlElement
	private static StringProperty DBName;
	
	private static void DBConfigNomal() {
		privateUserName=new SimpleStringProperty();
		publicKeyPwd=new SimpleStringProperty();
		DBName=new SimpleStringProperty();
	}
	
	public static void DBConfigSwitch(boolean isConfig)
	{
		DBConfigNomal();
		if (isConfig) {
			try {
				SqlCon.setUp();
				publicUserName=new SimpleStringProperty(SqlCon.getPublicKey());
				privateKeyPwd=new SimpleStringProperty(SqlCon.getPrivateKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			publicUserName=new SimpleStringProperty();
			privateKeyPwd=new SimpleStringProperty();
		}
	}

	public StringProperty getPublicUserNameProperty() {
		return publicUserName;
	}

	public void setPublicUserNameProperty(StringProperty publicUserName) {
		DBConfig.publicUserName = publicUserName;
	}

	public StringProperty getPrivateUserNameProperty() {
		return privateUserName;
	}

	public void setPrivateUserNameProperty(StringProperty privateUserName) {
		DBConfig.privateUserName = privateUserName;
	}

	public StringProperty getPublicKeyPwdProperty() {
		return publicKeyPwd;
	}

	public void setPublicKeyPwdProperty(StringProperty publicKeyPwd) {
		DBConfig.publicKeyPwd = publicKeyPwd;
	}

	public StringProperty getprivateKeyPwdProperty() {
		return privateKeyPwd;
	}

	public void setprivateKeyPwdProperty(StringProperty privateKeyPwd) {
		DBConfig.privateKeyPwd = privateKeyPwd;
	}

	public StringProperty getDBNameProperty() {
		return DBName;
	}

	public void setDBNameProperty(StringProperty dBName) {
		DBName = dBName;
	}
	
	public static void setUserName(String userName){
		privateUserName.set(
				new String(SqlCon.getenptyUserNameByPrivateKey(userName)));
	}
	
	public static void setPWD(String password){
		publicKeyPwd.set(
				new String(SqlCon.getenptyUserNameByPrivateKey(password)));
	}
	
	public static void setDBaseName(String dataBaseName){
		DBName.set(dataBaseName);
	}
	
	public String getPublicUserName(){
		return publicUserName.get();
	}
	
	public void setPublicUserName(String publicUserName) {
		DBConfig.publicUserName.set(publicUserName);
	}
	
	public String getPrivateUserName() {
		return privateUserName.get();
	}
	
	public void setPrivateUserName(String privateUserName) {
		DBConfig.privateUserName.set(privateUserName);
	}
	
	public String getPublicKeyPwd() {
		return publicKeyPwd.get();
	}
	
	public void setPublicKeyPwd(String publicKeyPwd) {
		DBConfig.publicKeyPwd.set(publicKeyPwd);
	}
	
	public String getPrivateKeyPwd() {
		return privateKeyPwd.get();
	}
	
	public void setPrivateKeyPwd(String privateKeyPwd) {
		DBConfig.privateKeyPwd.set(privateKeyPwd);
	}
	
	public String getDBName() {
		return DBName.get();
	}
	
	public void setDBName(String dBName) {
		DBConfig.DBName.set(dBName);
	}
}
