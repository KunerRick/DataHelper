package com.dataUtil.util;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataUtil.DataClass.ConfigInfo;
import com.dataUtil.DataClass.DataStruct;

/**
 * 
 * @author Administrator
 *
 */
public class UdxBuilder {
	private String FilePath;   //Binary file path.                                                                                                  
	private long FileLength;  //total File length.
	private long DataDimension; //now, its value is between 1 and 2(include 1 and 2).
	private long[] DataCount; //the data count of each dimension.
	private List<DataStruct> OneData; //One Data.
	private RandomAccessFile BinaryFileRead; // Binary random access file.
	private long OneDataLength; //One Data length.
	private long SeekBegin;  //the begin location in binary file.
	final Logger logger = LoggerFactory.getLogger(UdxBuilder.class);                                                                                                                                                                                                                                                                                                                                                                                                                         
	public UdxBuilder(){
		super();			
	}
	
	public UdxBuilder(String FilePath,long DataDimension,long[] DataCount,List<DataStruct> OneData){
		this.setFilePath(FilePath);
		this.setDataDimension(DataDimension);
		this.setDataCount(DataCount);
		this.setOneData(OneData);		
	}

	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String filePath) {
		File BinaryFile=new File(filePath);
		if(!BinaryFile.exists()){
			logger.warn("File {} not exists,please check.",filePath);
			return ;
			
		}
		try {
			this.BinaryFileRead =new RandomAccessFile(BinaryFile, "r");
			this.FileLength=BinaryFileRead.length();
			this.FilePath = filePath;	
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("File {} read error,please check.",filePath);
		}
			
	}
	public long getDataDimension() {
		return DataDimension;
	}

	public void setDataDimension(long dataDimension) {
		DataDimension = dataDimension;
	}

	public long[] getDataCount() {
		return DataCount;
	}

	public void setDataCount(long[] dataCount) {
		DataCount = dataCount;
	}

	public List<DataStruct> getOneData() {
		return OneData;
	}

	public void setOneData(List<DataStruct> oneData) {
		this.OneData = oneData;
	}
	

	/**
	 * get the data at the location
	 * @param location
	 * @return
	 */
	public String getData(long[] location,String ConfigFilePath){
		String result="";
		if(location.equals(null)) {
			logger.warn("the location Array can not be null.");
			return "";
		}
		ConfigInfo  configInfo= DataFactory.getConfigInfo(ConfigFilePath);
		if(configInfo==null){
			return "";
		}
		String binaryFilePath=configInfo.getBinaryFilePath();
		if(binaryFilePath==null||binaryFilePath.equals("")){
			logger.error("binary File Path can not be null.");
		}
		this.setFilePath(binaryFilePath);
		
		if(configInfo.getOneData()==null){
			logger.error("data item list(OneData) is null.");
			return "";
		}
		this.setOneData(configInfo.getOneData());
		
		if(configInfo.getDataCountOfEveryDimens()==null){
			logger.error("Data count of every dimension Array is null.");
			return "";
		}
		this.setDataCount(configInfo.getDataCountOfEveryDimens());
		this.OneDataLength = DataFactory.getOneDataLength(this.OneData);
		this.SeekBegin =DataFactory.getSeekBegin(this.OneDataLength, location, this.DataCount);
		if(this.SeekBegin < 0||this.SeekBegin>=this.FileLength){
			logger.error("the begin location is not correct. \n begin: {}  total: {} ",this.SeekBegin,this.FileLength);
			return "";
		}
		result=DataFactory.readData(this.BinaryFileRead, this.OneData, this.SeekBegin);		
		return result;		
	}
	
	public void Finish(){
		try {
			if(this.BinaryFileRead!=null){
				this.BinaryFileRead.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Binary close failed.");
		}
	}
	
}
