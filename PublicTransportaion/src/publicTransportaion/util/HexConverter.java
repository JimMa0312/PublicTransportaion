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
		String hString="";
		String temp="";
		for(int n=0;n<b.length;n++){
			temp=Integer.toHexString(b[0]&0xff);
			if (temp.length()==1) {
				hString+="0"+temp;
			}else{
				hString+=temp;
			}
		}
		temp=null;
		return hString.toUpperCase();
	}
	
	public static byte[] hexToByte(byte[] b) {
		if ((b.length%2)!=0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2=new byte[b.length/2];
		for(int n=0;n<b.length;n+=2){
			String item=new String(b, n, 2);
			
			b2[n/2]=(byte)Integer.parseInt(item,16);
		}
		b=null;
		return b2;
	}
}
