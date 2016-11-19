package publicTransportaion.sql;

import java.util.Map;

import publicTransportaion.sql.Safety.RSACoder;

public class SqlCon {
	
	private static final String userName="sa";
	private static final String userPwd="1230";
	private static  String publicKey;
	private static String privateKey;
	
	public static void setUp() throws Exception{
		Map<String, Object> keyMap=RSACoder.initKey();
		
		publicKey=RSACoder.getPublicKey(keyMap);
		privateKey=RSACoder.getPrivateKey(keyMap);
	}
	
	
	/*
	 * 使用私钥加密数据库用户用户
	 * 
	 * 使用公钥进行解密
	 * @return
	 */
	@SuppressWarnings("finally")
	public static byte[] getenptyUserNameByPrivateKey(){
		byte[] data=userName.getBytes();
		
		byte[] encodeData = null;
		
		try {
			encodeData=RSACoder.encryByPricvateKey(data, privateKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return encodeData;
		}
	}
	
	@SuppressWarnings("finally")
	public static byte[] getenptyUserNameByPrivateKey(String userName){
		byte[] data=userName.getBytes();
		
		byte[] encodeData = null;
		
		try {
			encodeData=RSACoder.encryByPricvateKey(data, privateKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return encodeData;
		}
	}
	/*
	 * 使用公钥加密数据库用户密码
	 * 
	 * 使用私钥进行解密
	 * @return
	 */
	@SuppressWarnings("finally")
	public static byte[] getenptyUserPwdByPublicKey() {
		byte[] data=userPwd.getBytes();
		
		byte[] encodeData=null;
		
		try {
			encodeData=RSACoder.encryptByPublicKey(data, publicKey);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			return encodeData;
		}
	}
	
	@SuppressWarnings("finally")
	public static byte[] getenptyUserPwdByPublicKey(String userPwd) {
		byte[] data=userPwd.getBytes();
		
		byte[] encodeData=null;
		
		try {
			encodeData=RSACoder.encryptByPublicKey(data, publicKey);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			return encodeData;
		}
	}
	
	public static String getPublicKey() {
		if (publicKey==null) {
			return null;
		}
		return publicKey;
	}
	
	public static String getPrivateKey() {
		if (privateKey==null) {
			return null;
		}
		return privateKey;
	}

}
