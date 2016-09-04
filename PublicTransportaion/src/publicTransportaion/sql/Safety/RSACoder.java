package publicTransportaion.sql.Safety;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.sun.glass.ui.CommonDialogs.Type;

import sun.launcher.resources.launcher;
import sun.security.jca.GetInstance;

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
}
