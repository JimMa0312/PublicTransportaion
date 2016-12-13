package publicTransportaion.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import publicTransportaion.safety.SefDES;
import publicTransportaion.sql.SqlConRSA;

public class DBConfig {
	private static StringProperty UserName;
	private static StringProperty Pwd;
	private static StringProperty DBName;

	public static void initDBConfig() {
		UserName = new SimpleStringProperty("123");
		Pwd = new SimpleStringProperty("123");
		DBName = new SimpleStringProperty("123");
	}

	public static StringProperty getUserNameProperty() {
		return UserName;
	}

	public static void setUserNameProperty(StringProperty userName) {
		UserName = userName;
	}

	public static StringProperty getPwdProperty() {
		return Pwd;
	}

	public static void setPwdProperty(StringProperty pwd) {
		Pwd = pwd;
	}

	public static StringProperty getDBNameProperty() {
		return DBName;
	}

	public static void setDBNameProperty(StringProperty dBName) {
		DBName = dBName;
	}

	public static String getUserName() {
		return UserName.get();
	}

	public static void setuserName(String userName) {
		UserName.set(userName);
	}

	public static String getPWD() {
		return Pwd.get();
	}

	public static void setPWD(String pwd) {
		Pwd.set(pwd);
	}

	public static String getDBname() {
		return DBName.get();
	}

	public static void setDBname(String dbName) {
		DBName.set(dbName);
	}
}
