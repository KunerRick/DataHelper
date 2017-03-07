package com.udx.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.udx.util.DataUtil.SaveFileType;
import com.udx.util.UdxHandlUtil.UdxDataType;

/**
 * 测试二进制大文件读写（200M）
 * @author CK
 *
 */
public class FileTest {
	static String inputFilePath="D:\\Workspace\\FvCom\\testData\\output\\cha_AUV.txt2";
	static String outputFilePath="D:\\Workspace\\FvCom\\testData\\output\\cha_AUVCopy.txt2";
	
	public static void  file2file() throws IOException{
		DataUtil dataUtil=new DataUtil();
		 DataInputStream br=new DataInputStream(  
                 new BufferedInputStream(  
                 new FileInputStream(inputFilePath)));  
		        dataUtil.BeginSave(outputFilePath, SaveFileType.Text);
		 		byte[] oneData=new byte[8];
		 		int i=0,count =0 ;
				while(br.read(oneData, 0, 8)!=-1){	
					i=i+1;
					dataUtil.AddSave(String.valueOf(DataUtil.bytes2Double(oneData)));					
					if(i/23543==0){
						count++;
						System.out.println(count+"\n");

					}
				}
				dataUtil.EndSave();		
	}
}
