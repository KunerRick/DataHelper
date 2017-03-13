package com.dataUtil.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataUtil.DataClass.BasicDataType;
import com.dataUtil.DataClass.ConfigInfo;
import com.dataUtil.DataClass.DataStruct;
import com.util.HandleXml;

public class DataFactory {
	final static Logger logger=LoggerFactory.getLogger(DataFactory.class);
	public static long getOneDataLength(List<DataStruct> OneData){
		long oneDataLength=0;
		if(OneData.equals(null)||OneData.isEmpty()){
			logger.warn("OneData can not be null or empty.");
			return oneDataLength;
		}		
		for(int i=0;i<OneData.size();i++){
			if(getOneItemLength(OneData.get(i))!=0){
				oneDataLength+=getOneItemLength(OneData.get(i));
			}else{
				logger.warn("the length of data which  index equals {} is 0.",i);
				return oneDataLength;
			};
		}
			return 	oneDataLength;
	}
	/**
	 * 
	 * @param location
	 * location array.egs: 2,3,4
	 * @param dataCount
	 * the count of each dimension.egs:2,6,4
	 * @return
	 */
	public static long getSeekBegin(long oneDataLength,long[] location,long[] dataCount){
		long rslt=0;
		if(location.length!=dataCount.length){
			logger.warn("the location array is not correct.");
			return -1;
		}
		if(!CheckData.CheckLocationData(location, dataCount)){
			return -1;
		}
		long midCount=1;
		for(int i=0;i<location.length;i++){
			for(int j=i+1;j<dataCount.length;j++){
				midCount=midCount*(location[i]-1)*dataCount[j];
			}
			if(i+1==location.length){
				midCount=location[i]-1;
			}
			rslt+=midCount;
			midCount=1;
		}
		rslt=rslt*oneDataLength;
		
		return rslt;
		
	}
	private static int getOneItemLength(DataStruct oneItem){
		int oneItemLength=0;
		if(oneItem==null){
			logger.warn("the data of oneData list is null.");
			return oneItemLength;
		}
		switch(oneItem.getBasicDataType()){
		case DOUBLE:
			oneItemLength=8;
			break;
		case FLOAT:
			oneItemLength=4;
			break;
		case INT:
			oneItemLength=4;
			break;
		case LONG:
			oneItemLength=8;
			break;
		case STRING:
			if(oneItem.getDataLength()==0){
				logger.warn("the length of oneData is not correct.");
				return oneItemLength;
			}
			oneItemLength=oneItem.getDataLength();
			break;
		default:
			logger.warn("the basic data type of oneData is not correct.");
			break;
		}
		return oneItemLength;
	}
	
	public static ConfigInfo getConfigInfo(String ConfigFilePath){
		ConfigInfo configInfo = new ConfigInfo();
		Document doc = null;
		String BinaryFilePath = "";
		long Dimensions=0;
		long[] DataCountOfEveryDimens;
		List<DataStruct> OneData;
		try {
			doc=HandleXml.parse(ConfigFilePath);						
		} catch (DocumentException e) {			
			logger.warn("to read the config file failed. info as follows:\n {} \n",e.toString());
			return null;
			
		}
		Element root =doc.getRootElement();
		
		// get OneData 
		List nodes = root.selectNodes("/BinaryData/DataStruct");
		if(nodes.size()<=0){
			logger.error("Data struct description does not exist.");
			return null;
		}
	    Element DataStructElem=(Element) nodes.get(0);
		List elements= DataStructElem.elements();
		if(elements.size()<=0){
			logger.error("Data struct description is null.");
			return null;
		}
		OneData = new ArrayList<DataStruct>();
		for(int i=0;i<elements.size();i++){
			Element ItemElem=(Element)elements.get(i);
			String DataItemType=ItemElem.getText();
			BasicDataType basicDataType=CheckData.getBasicDataType(DataItemType);
			String dataLength=ItemElem.attributeValue("length");
			if(dataLength.equals(null)){
				
			}
			if(basicDataType.equals(null)){
				logger.error("read the item Error.the index is {} .",i);
				return null;
			}
			DataStruct dataStruct=new DataStruct();
			dataStruct.setBasicDataType(basicDataType);
			dataStruct.setOrder(i);
			dataStruct.setDataLength(Integer.parseInt(dataLength));
			OneData.add(dataStruct);			
		}
		
		//get data properties
		List DataPropertiesNode = root.selectNodes("/BinaryData/DataProperties");
		if(DataPropertiesNode.size()<=0){
			logger.error("Data properties node does not exist.");
			return null;
		}
		Element DataPropertiesElem =(Element) DataPropertiesNode.get(0);
		if(DataPropertiesElem.elements().size()<=0){
			logger.error("Data properties is null.");
			return null;
		}
		//--get data file path 
		Element  DataFilePathElem=(Element)DataPropertiesElem.selectSingleNode("DataFilePath");
		if(DataFilePathElem==null){
			logger.error("Binary data file path node does not exist.");
			return null;
		}
		
		BinaryFilePath=DataFilePathElem.getText();
		
		//get Dimension Count
		Element  DimensionCountElem=(Element)DataPropertiesElem.selectSingleNode("DimensionCount");
		if(DimensionCountElem==null){
			logger.error("Binary data dimension count node does not exist.");
			return null;
		}
		 if(DimensionCountElem.getText().equals("")||DimensionCountElem.getText().equals("0")){
			 logger.error("Binary data dimension count can not be null or 0.");
			 return null;
		 }
		 Dimensions=Long.parseLong(DimensionCountElem.getText());
		 
		 //get Data Count Set
		 
	     Element  DataCountSetElem=(Element)DataPropertiesElem.selectSingleNode("DataCountSet");
		if(DataCountSetElem==null){
			logger.error("Binary Data Count Set node does not exist.");
			return null;
		}
		String DataCountSetStr = DataCountSetElem.getText();
		if(DataCountSetStr.equals("")){
			logger.error("Binary Data Count Set can not be null.");
			return null;
		}
		String[] DataCountSetStrArray=DataCountSetStr.trim().split(",");
		if(DataCountSetStrArray==null||DataCountSetStrArray.length<=0){
			logger.error("the format of Binary Data Count Set is not correct.");
			return null;
		}
		DataCountOfEveryDimens=new long[DataCountSetStrArray.length];
		for(int i=0;i<DataCountSetStrArray.length;i++){
			DataCountOfEveryDimens[i]=Long.parseLong(DataCountSetStrArray[i]);
		}
		
		//set info
		configInfo.setBinaryFilePath(BinaryFilePath);
		configInfo.setDimensions(Dimensions);
		configInfo.setDataCountOfEveryDimens(DataCountOfEveryDimens);
		configInfo.setOneData(OneData);
		
		
		
		return configInfo;
	}
	
	public static String readData(RandomAccessFile in,List<DataStruct> oneData,long SeekbeginLocation){
		String rslt = "";
		if(in.equals(null)) {
			logger.warn("the random access file is null.");
			return "";
		}
		if(oneData.equals(null)){
			logger.warn("the oneData is null.");
			return "";
		}
		if(SeekbeginLocation < 0){
			logger.warn("the seek begin address {} is not correct.",SeekbeginLocation);
			return "";
		}
		try {
			in.seek(SeekbeginLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(" the seek location is not correct.");
		}
		for(int i=0;i<oneData.size();i++){
			if(oneData.get(i).getBasicDataType().equals(null)){
				logger.error("the basic data type of data which index is {} is null.",i);
				return "";
			}
			switch(oneData.get(i).getBasicDataType()){
			case DOUBLE:
				rslt += String.valueOf(BinaryIO.readDouble(in));
				break;
			case FLOAT:
				rslt +=String.valueOf(BinaryIO.readFloat(in));
				break;
			case INT:
				rslt +=String.valueOf(BinaryIO.readInt(in));
				break;
			case LONG:
				rslt +=String.valueOf(BinaryIO.readLong(in));
				break;
			case STRING:
				if(oneData.get(i).getDataLength()==0){
					logger.error("the length of String data which index is {}  cannot be 0.",i);
					return "";
				}
				rslt +=BinaryIO.readString(in, oneData.get(i).getDataLength());
				break;
			default:
				logger.error("the basic data type of data which index is {} is error.",i);
				return "";
								
			}
			rslt+="   ";
		}
		return rslt;
	}
	
	
}
