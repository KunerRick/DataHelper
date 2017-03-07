package com.dataUtil.DataClass;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigInfo {
	final Logger logger = LoggerFactory.getLogger(ConfigInfo.class);
	
	private String BinaryFilePath;
	private long Dimensions;
	private long[] DataCountOfEveryDimens;
	private List<DataStruct> OneData;
	
	
	public ConfigInfo(){
		super();
		this.BinaryFilePath = "";
		this.Dimensions = 0;
		this.DataCountOfEveryDimens = null;
		this.OneData = null;
	}
	
	public ConfigInfo(String BinaryFilePath,long Dimensions,
			long[] DataCountOfEveryDimens,List<DataStruct> OneData){
		this.setBinaryFilePath(BinaryFilePath);
		this.setDimensions(Dimensions);
		this.setDataCountOfEveryDimens(DataCountOfEveryDimens);
		this.setOneData(OneData);
	}
	
	public String getBinaryFilePath() {
		return BinaryFilePath;
	}
	public void setBinaryFilePath(String binaryFilePath) {
		if(binaryFilePath.equals(null)||binaryFilePath.equals("")){
			logger.warn("Binary file path is null,please check.");
			return ;
		}
		BinaryFilePath = binaryFilePath;
	}
	public long getDimensions() {
		return Dimensions;
	}
	public void setDimensions(long dimensions) {
		if(dimensions == 0||dimensions<0){
			logger.warn("the dimension must be more than 0.");
			return ;
		}
		Dimensions = dimensions;
	}
	public long[] getDataCountOfEveryDimens() {
		return DataCountOfEveryDimens;
	}
	public void setDataCountOfEveryDimens(long[] dataCountOfEveryDimens) {
		if(dataCountOfEveryDimens.equals(null)){
			logger.warn("the data count array can not be null.");
			return ;
		}
		this.DataCountOfEveryDimens = dataCountOfEveryDimens;
	}
	public List<DataStruct> getOneData() {
		return OneData;
	}
	public void setOneData(List<DataStruct> oneData) {
		if(oneData.equals(null)){
			logger.warn("the data struct list  can not be null.");
			return ;
		}
		OneData = oneData;
	}
	
	
	
	
}
