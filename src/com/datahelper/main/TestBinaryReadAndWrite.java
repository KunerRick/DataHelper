package com.datahelper.main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataUtil.util.BinaryIO;


/**
 * 测试数据： name:Wangming  value:23.45617892 age:24
 * 		  name:Zhangsan  value:-564.15484687 age:22
 * @author Administrator
 *
 */
public class TestBinaryReadAndWrite {
	private List<DataClass> DataList = new ArrayList<DataClass>();
	private String filePath="";
	 final Logger logger = LoggerFactory.getLogger(TestBinaryReadAndWrite.class);
	
	/**
	 * 
	 * @param savePath
	 */
	public TestBinaryReadAndWrite(String savePath){
		   
     
		DataClass a=new DataClass("Wangming",23.45617892,24);
		DataClass b=new DataClass("Zhangsan",-564.15484687,22);
		DataList.add(a);
		DataList.add(b);
		this.filePath = savePath;
		
		
	}
	/**
	 * binary file write test
	 */
	public void writeToFile(){
		File file=new File(filePath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		if(file.exists()){			
			file.delete();		
		}
		DataOutputStream out = null;
		try {
			file.createNewFile();			
			out=new DataOutputStream(new FileOutputStream(file));
			for(DataClass dataClass:DataList){
				String name=dataClass.getName();
				BinaryIO.writeString(out, name, DataClass.NameSize);				
				BinaryIO.writeDouble(out, dataClass.getValue());
				BinaryIO.writeInt(out, dataClass.getAge());
				
				
			}		
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("charToBytes failed.{}",e.getMessage());
		}finally{			
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("charToBytes file close failed.");
			}
		}	
	}
	/**
	 * 使用RandomAccessFile方便快速定位（通过文件指针）
	 * @throws FileNotFoundException
	 */
	public void readFromfile() throws FileNotFoundException{
		RandomAccessFile  in=null;
		 
		try {
			in=new RandomAccessFile(filePath, "r");
			int len=(int)(in.length()/DataClass.dataLength);
			
			for(long i=0;i<len;i++){
				//in.seek(i*DataClass.dataLength);								
				String name1=BinaryIO.readString(in, DataClass.NameSize);
				logger.info("the test name is {}.",name1);
				logger.debug(String.valueOf(BinaryIO.readDouble(in)));			
				int b= BinaryIO.readInt(in);
				logger.debug(String.valueOf(b));				
			}
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			logger.debug("read error.");
			
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.debug("in close error.");
			}
		}
	}
	

	
	
	
	
}

class DataClass{
	private String name;
	private double value;
	private int age;
	
	public static int  NameSize=20;
	public static int dataLength = 32;
	public DataClass(String name,double value,int age){
		this.name = name;
		this.value = value;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}