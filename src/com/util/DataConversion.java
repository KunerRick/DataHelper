package com.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 数据转换类
 * @author Administrator
 *
 */
public class DataConversion {

	private static ByteBuffer buffer = ByteBuffer.allocate(8);

	public static byte[] longToBytes(long x) {
		buffer.putLong(0, x);
		return buffer.array();
	}
	/**
	 * byte[] 转 long
	 * @param bytes
	 * @return
	 */
	public static long bytesToLong(byte[] bytes) {
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();// need flip
		return buffer.getLong();
	}
	/**
	 * double 转 byte[]
	 * @param d
	 * @return
	 */
	public static byte[] doubleToBytes(double d) {
		long value = Double.doubleToRawLongBits(d);
		byte[] byteRet = new byte[8];
		for (int i = 0; i < 8; i++) {
			byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
		}
		return byteRet;
	}

	/**
	 * byte[] to double
	 * @param arr
	 * @return
	 */
	public static double bytesToDouble(byte[] arr) {
		long value = 0;
		for (int i = 0; i < 8; i++) {
			value |= ((long) (arr[i] & 0xff)) << (8 * i);
		}
		return Double.longBitsToDouble(value);
	}
	/**
	 *  byte[] to int
	 * @param b
	 * @return
	 */
	public static int bytesToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16
				| (b[0] & 0xFF) << 24;
	}
	/**
	 * int to byte[]
	 * @param a
	 * @return
	 */
	public static byte[] intToBytes(int a) {
		return new byte[] { (byte) ((a >> 24) & 0xFF),
				(byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}
	/**
	 * char[] to byte[]
	 * @param chars
	 * @return
	 */
	public static byte[] charsToBytes(char[] chars) {
		Charset cs = Charset.forName("ISO-8859-1"); //若用utf-8长度会增加
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();

	}

	/**
	 * byte[] to char[]
	 * @param bytes
	 * @return
	 */
	public static char[] byteToChars(byte[] bytes) {
		Charset cs = Charset.forName("ISO-8859-1"); //若用utf-8长度会增加
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		return cb.array();
	}
	/**
	 * char[] to String
	 * @param chars
	 * @return
	 */
	public static String charsToString(char[] chars){
		String rslt="";
		for(int i=0;i<chars.length;i++){
			if(chars[i]==0){
				continue;
			}
			rslt+=chars[i];		
		}
		return rslt;
	}
	/**
	 * String to char[]
	 * @param str
	 * @param totalLength
	 * @return
	 */
	public static char[] stringToChars(String str,int totalLength){
		char[] ch=new char[totalLength];
		for(int i=0;i<str.length();i++){
			ch[i]=0;
			if(i<str.length()){
				ch[i]=str.charAt(i);
			}
		}
		return ch;
	}
	/**
	 * float to byte[]
	 * @param f
	 * @return
	 */
	public static byte[] floatToBytes(float f) {  
	      
	    // 把float转换为byte[]  
	    int fbit = Float.floatToIntBits(f);  
	      
	    byte[] b = new byte[4];    
	    for (int i = 0; i < 4; i++) {    
	        b[i] = (byte) (fbit >> (24 - i * 8));    
	    }   
	      
	    // 翻转数组  
	    int len = b.length;  
	    // 建立一个与源数组元素类型相同的数组  
	    byte[] dest = new byte[len];  
	    // 为了防止修改源数组，将源数组拷贝一份副本  
	    System.arraycopy(b, 0, dest, 0, len);  
	    byte temp;  
	    // 将顺位第i个与倒数第i个交换  
	    for (int i = 0; i < len / 2; ++i) {  
	        temp = dest[i];  
	        dest[i] = dest[len - i - 1];  
	        dest[len - i - 1] = temp;  
	    }  
	      
	    return dest;  
	      
	}  
	  
	/** 
	 * 字节转换为浮点 
	 *  
	 * @param b 字节（至少4个字节） 
	 * @param index 开始位置 
	 * @return 
	 */  
	public static float bytesToFloat(byte[] b, int index) {    
	    int l;                                             
	    l = b[index + 0];                                  
	    l &= 0xff;                                         
	    l |= ((long) b[index + 1] << 8);                   
	    l &= 0xffff;                                       
	    l |= ((long) b[index + 2] << 16);                  
	    l &= 0xffffff;                                     
	    l |= ((long) b[index + 3] << 24);                  
	    return Float.intBitsToFloat(l);                    
	}  
}
