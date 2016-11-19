package publicTransportaion.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import publicTransportaion.sql.SqlCon;

public class DBConfig {

	private static StringProperty publicUserName;
	private static StringProperty privateUerName;
	private static StringProperty publicKeyPwd;
	private static StringProperty privateKePpwd;
	private static StringProperty DBName;
	
	private static void DBConfigNomal() {
		privateUerName=new SimpleStringProperty();
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
				privateKePpwd=new SimpleStringProperty(SqlCon.getPrivateKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			publicUserName=new SimpleStringProperty();
			privateKePpwd=new SimpleStringProperty();
		}
	}

	public StringProperty getPublicUserName() {
		return publicUserName;
	}

	public void setPublicUserName(StringProperty publicUserName) {
		DBConfig.publicUserName = publicUserName;
	}

	public StringProperty getPrivateUerName() {
		return privateUerName;
	}

	public void setPrivateUerName(StringProperty privateUerName) {
		DBConfig.privateUerName = privateUerName;
	}

	public StringProperty getPublicKeyPwd() {
		return publicKeyPwd;
	}

	public void setPublicKeyPwd(StringProperty publicKeyPwd) {
		DBConfig.publicKeyPwd = publicKeyPwd;
	}

	public StringProperty getPrivateKePpwd() {
		return privateKePpwd;
	}

	public void setPrivateKePpwd(StringProperty privateKePpwd) {
		DBConfig.privateKePpwd = privateKePpwd;
	}
	
	public void setPwd(String password){
		
	}

	public StringProperty getDBName() {
		return DBName;
	}

	public void setDBName(StringProperty dBName) {
		DBName = dBName;
	}
	
	public static void setUserName(String userName){
		privateUerName.set(
				new String(SqlCon.getenptyUserNameByPrivateKey(userName)));
	}
	
	public static void setPWD(String password){
		publicKeyPwd.set(
				new String(SqlCon.getenptyUserNameByPrivateKey(password)));
	}
	
	public static void setDBName(String DBname){
		DBName.set(DBname);
	}
}
