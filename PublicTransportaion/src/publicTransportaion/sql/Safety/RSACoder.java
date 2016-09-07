package publicTransportaion.sql.Safety;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/*
 * RSA安全编码组件
 */

public abstract class RSACoder extends Coder {
	public static final String KEY_ALGORITHM="RSA";
	public static final String SIGNATURE_ALGORITHM="MDSwithRSA";
	
	private static final String PUBLIC_KEY="RSAPublicKey";
	private static final String PRIVATE_KEY="RSAPrivateKey";
	
	/*
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 * 			加密数据
	 * @param privateKey
	 * 			私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		//解密 由base64编码的私钥
		byte[] keyBytes=decryptBASE64(privateKey);
		
		//构造PKCS8EncodeKeySpec对象
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(keyBytes);
		//KEY_ALGORITHM指定的加密算法
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		
		//取私钥对象
		PrivateKey priKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		
		//用私钥对信息生成数字签名
		Signature signature=Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		
		return encryptBASE64(signature.sign());
	}
	
	/*
	 * 校验数字签名
	 * 
	 * @param data
	 * 			加密数据
	 * @param publicKey
	 * 			公共密钥
	 * @param sign
	 * 			数字签名
	 * 
	 *@return 校验成功返回true,失败返回false
	 *@throws Execption
	 */
	
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes=decryptBASE64(publicKey);
		
		X509EncodedKeySpec keySepc=new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey pubKey=keyFactory.generatePublic(keySepc);
		Signature signature=Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);
		
		return signature.verify(decryptBASE64(sign));	
	}
	
	/*
	 * 解密<br>
	 * 用私钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	
	public static byte[] decryptyByPrivateKey(byte[] data, String key) throws Exception{
		//获取钥匙
		byte[] keyBytes=decryptBASE64(key);
		PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey=keyFactory.generatePrivate(pkcs8KeySpec);
		
		//数据解密
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		return cipher.doFinal(data);
	}
	
	/*
	 * 解密<br>
	 * 用共钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	
	public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
		byte[] KeyBytes=decryptBASE64(key);
		X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(KeyBytes);
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey=keyFactory.generatePublic(x509KeySpec);
		
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		
		return cipher.doFinal(data);
	}
	
	/*
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception{
		byte[] keyBytes=decryptBASE64(key);
		X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey=keyFactory.generatePublic(x509KeySpec);
		
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		return cipher.doFinal(data);
	}
	
	/*
	 * 加密<br>
	 * 用私钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryByPricvateKey(byte[] data, String key) throws Exception {
		byte[] keyBytes=decryptBASE64(key);
		
		PKCS8EncodedKeySpec pKeySpec=new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey=keyFactory.generatePrivate(pKeySpec);
		
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		
		return cipher.doFinal(data);
	}
	
	/*
	 * 获取私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key=(Key) keyMap.get(PRIVATE_KEY);
		
		return encryptBASE64(key.getEncoded());
	}
	
	/*
	 * 获取公钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key=(Key) keyMap.get(PUBLIC_KEY);
		
		return encryptBASE64(key.getEncoded());
	}
	
	/*
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGenerator.initialize(1024);
		
		KeyPair keyPair=keyPairGenerator.generateKeyPair();
		
		//public
		RSAPublicKey publicKey=(RSAPublicKey) keyPair.getPublic();
		
		//private
		RSAPrivateKey privateKey=(RSAPrivateKey)keyPair.getPrivate();
		
		Map<String, Object> keyMap=new HashMap<String, Object>(2);
		
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		
		return keyMap;
	}
}
