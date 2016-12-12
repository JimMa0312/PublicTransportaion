package publicTransportaion.model;

import java.beans.Transient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import publicTransportaion.safety.SefDES;
import publicTransportaion.util.HexConverter;

@XmlRootElement(name="DBconfig")
public class DBconfigXml {
	@XmlElement
	private String UserName;
	@XmlElement
	private String Pwd;
	@XmlElement
	private String DBName;
	@XmlElement
	private String Key;

	public DBconfigXml() {
	}
	
	public void LoadDBconfig()throws Exception{
		String factKey=SefDES.initKey();
		Key=HexConverter.binToHex(factKey);
		UserName=SefDES.hexStrEncrypt(DBConfig.getUserName(), factKey);
		Pwd=SefDES.hexStrEncrypt(DBConfig.getPWD(), factKey);
		DBName=DBConfig.getDBname();
	}
	
	public void WriteDBconfig(){
		DBConfig.setuserName(HexConverter.hexToBin(UserName));
		DBConfig.setPWD(HexConverter.hexToBin(Pwd));
		DBConfig.setDBname(DBName);
		DBConfig.setKey(HexConverter.hexToBin(Key));
	}

	@XmlTransient
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	@XmlTransient
	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}

	@XmlTransient
	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	@XmlTransient
	public String getDBName() {
		return DBName;
	}

	public void setDBName(String dBName) {
		DBName = dBName;
	}
}
