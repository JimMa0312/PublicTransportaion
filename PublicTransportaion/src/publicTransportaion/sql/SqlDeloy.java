package publicTransportaion.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import publicTransportaion.model.DBConfig;
import publicTransportaion.safety.DESCoder;
import publicTransportaion.safety.RSACoder;

public class SqlDeloy {
	private static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String	DBURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Bus";
	@SuppressWarnings("unused")
	private static SqlConRSA sqlCon;
	private static Connection connection=null;
	
	public SqlDeloy() {
		try {
			Class.forName(DRIVERNAME);
			connection=DriverManager.getConnection(DBURL,"sa","1230");
			System.out.println("Connected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void shotDownCon() {
		try {
			connection.close();
			System.out.println("ShotDown!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String DenpytUserName(){
		byte[] decodeData=null;
		
		try {
//			decodeData=RSACoder.decryptByPublicKey(null, null);
			decodeData=DESCoder.decrypt(DBConfig.getUserName().getBytes(), DBConfig.getKey());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new String(decodeData);
	}
	
	private String DenpytUserPwd(){
		byte[] decodeData=null;
		
		try {
//			decodeData=RSACoder.decryptyByPrivateKey(null, null);
			decodeData=DESCoder.decrypt(DBConfig.getPWD().getBytes(), DBConfig.getKey());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new String(decodeData);
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
