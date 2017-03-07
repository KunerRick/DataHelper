package com.dataUtil.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataUtil.DataClass.BasicDataType;

public class CheckData {
	final static Logger logger = LoggerFactory.getLogger(CheckData.class);
	public static boolean CheckLocationData(long[] location,long[] dataCount){
		if(location.equals(null)||dataCount.equals(null)||location.length!=dataCount.length){
			logger.warn("the location data is null,or not correct,please check.");
			return false;
		}
		for(int i=0;i<dataCount.length;i++){
			if(dataCount[i]<location[i]){
				logger.warn("the location data at index {} is out of range.",i);
				return false;
			}
		}
		return true;
	}
	
	public static BasicDataType getBasicDataType(String DataItemType){
		BasicDataType BDType = null;
		
		switch(DataItemType){
		case "BDT_DOUBLE":
			BDType=BasicDataType.DOUBLE;
			break;
		case "BDT_FLOAT":
			BDType=BasicDataType.FLOAT;
			break;
		case "BDT_INT":
			BDType=BasicDataType.INT;
			break;
		case "BDT_LONG":
			BDType=BasicDataType.LONG;
			break;
		case "BDT_STRING":
			BDType=BasicDataType.STRING;
			break;
		default:
			logger.error("read the item Error.");
			BDType=null;
			break;
				
		}
		return BDType;
	}
	
	
}
