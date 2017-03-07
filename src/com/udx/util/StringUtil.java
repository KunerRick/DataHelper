package com.udx.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringUtil {
	
	public static enum AlignType{Left,Right,None};
	/**
	 * 返回右对齐字符串，即左边不足位以空格填充
	 * @param inputStr    //输入字符串
	 * @param totalLength //右对齐字符串总共所占的长度
	 * @return
	 */
	public static String RightAlign(String inputStr,int totalLength){
		StringBuilder rsltStr=new StringBuilder();
		if(totalLength<inputStr.length()) return null;
		for(int i=0;i<totalLength-inputStr.length();i++){
			rsltStr.append(" ");
		}
		rsltStr.append(inputStr);
		return rsltStr.toString();
	}
	/**
	 * 左对齐，其他同上
	 * @param inputStr
	 * @param totalLength
	 * @return
	 */
	public static String LeftAlign(String inputStr,int totalLength){
		StringBuilder rsltStr=new StringBuilder();
		if(totalLength<inputStr.length()) return null;		
		rsltStr.append(inputStr);
		for(int i=0;i<totalLength-inputStr.length();i++){
			rsltStr.append(" ");
		}
		return rsltStr.toString();
	}

	/**
	 * 根据mdl中事件名返回不带后缀的文件名
	 * @param resEventName    // [文件名]@[后缀] 如：smsTin@geo
	 * @return 
	 * @throws Exception
	 */
	public static String getFileName(String resEventName)throws Exception{
		String[] str=resEventName.split("@");
		return str[0];
	}
	/**
	 * 根据mdl中事件名返回带后缀的文件名
	 * @param resEventName    // [文件名]@[后缀] 如：smsTin@geo
	 * @return 
	 * @throws Exception
	 */
	public static String getFileFullName(String resEventName) {
		// TODO Auto-generated method stub
		String str=resEventName.replaceAll("@", ".");
		return str;
		
	}
	
	public static String getSpace(int number){
		String rslt="";
		if(number>=1){
			for(int i=0;i<number;i++){
				rslt+=" ";
			}
		}
		return rslt;
		
	}
	
	public static byte[] getBytes4File(String filePath) throws IOException {  
		  
	    InputStream in = null;  
	    BufferedInputStream buffer = null;  
	    DataInputStream dataIn = null;  
	    ByteArrayOutputStream bos = null;  
	    DataOutputStream dos = null;  
	    byte[] bArray = null;  
	    try {  
	        in = new FileInputStream(filePath);  
	        buffer = new BufferedInputStream(in);  
	        dataIn = new DataInputStream(buffer);  
	        bos = new ByteArrayOutputStream();  
	        dos = new DataOutputStream(bos);  
	        byte[] buf = new byte[1024];  
	        while (true) {  
	            int len = dataIn.read(buf);  
	            if (len < 0)  
	                break;  
	            dos.write(buf, 0, 20); 
	            break;
	        }  
	        bArray = bos.toByteArray();  

	    } catch (Exception e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	        return null;  

	    } finally {  

	        if (in != null)  
	            in.close();  
	        if (dataIn != null)  
	            dataIn.close();  
	        if (buffer != null)  
	            buffer.close();  
	        if (bos != null)  
	            bos.close();  
	        if (dos != null)  
	            dos.close();  
	    }  

	    return bArray;  
	  }  
	
}
