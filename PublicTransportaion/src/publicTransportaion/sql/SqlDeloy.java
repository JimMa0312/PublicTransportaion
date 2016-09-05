package publicTransportaion.sql;

import java.sql.Connection;
import java.sql.DriverManager;

import publicTransportaion.sql.Safety.RSACoder;

public class SqlDeloy {
	private static final String DRIVERNAME="con.microsoft.sql.server.jdbc.SQLServerDriver";
	private static final String	DBURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Bus";
	private static SqlCon sqlCon;
	
	public SqlDeloy() {
		try {
			SqlCon.setUp();
			Class.forName(DRIVERNAME);
			Connection connection=DriverManager.getConnection(DBURL,DenpytUserName(),DenpytUserPwd());
			
			System.out.println("Connected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String DenpytUserName(){
		byte[] decodeData=null;
		
		try {
			decodeData=RSACoder.decryptByPublicKey(SqlCon.getenptyUserNameByPrivateKey(), SqlCon.getPublicKey());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new String(decodeData);
	}
	
	private String DenpytUserPwd(){
		byte[] decodeData=null;
		
		try {
			decodeData=RSACoder.decryptyByPrivateKey(SqlCon.getenptyUserPwdByPublicKey(), SqlCon.getPrivateKey());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new String(decodeData);
	}
}
