package publicTransportaion.util;

public class HexConverter {
	/*
	 * 字符串转成十六进制的
	 * @param bin string 要转换的普通字符串
	 * @return string
	 */
	public static String binToHex(String bin) {
		char[] digital="0123456789ABCDEF".toCharArray();
		StringBuffer sBuffer=new StringBuffer("");
		byte[] bs=bin.getBytes();
		int bit;
		for (byte b : bs) {
			bit=(b&0x0f0)>>4;
			sBuffer.append(digital[bit]);
			bit=b&0x0f;
			sBuffer.append(digital[bit]);
		}
		
		return sBuffer.toString();
	}
	
	public static String hexToBin(String hex) {
		String digital ="0123456789ABCDEF";
		char[] hex2Char=hex.toCharArray();
		byte[] bytes=new byte[hex.length()/2];
		int temp;
		for(int i=0;i<bytes.length;i++){
			temp=digital.indexOf(hex2Char[2*i])*16;
			temp+=digital.indexOf(hex2Char[2*i+1]);
			bytes[i]=(byte) (temp&0xff);
		}
		
		return new String(bytes);
	}
	
	public static String byteToHex(byte[] b) {
		StringBuffer sBuffer=new StringBuffer(b.length*2);
		for (int i = 0; i < b.length; i++) {
			if ((b[i] & 0xff) < 0x10) {
				sBuffer.append("0");
			}
			sBuffer.append(Integer.toHexString(b[i]&0xFF));
		}
		
		return sBuffer.toString();
	}
	
	public static byte[] hexToByte(String str) {
		byte[] bytes=str.getBytes();
		
		int len=bytes.length;
		byte[] arr=new byte[len/2];
		for (int i = 0; i < len; i+=2) {
			String tmp=new String(bytes, i, 2);
			arr[i/2]=(byte)Integer.parseInt(tmp, 16);
		}
		
		return arr;
	}
}
