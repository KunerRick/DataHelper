package com.datahelper.main;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.BinaryTest;
import com.Binary.Schema.ESchemaNodeType;
import com.Binary.Schema.SchemaNodeTypeHandle;
import com.dataUtil.util.UdxBuilder;



public class Main {
	final static Logger logger=LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) throws IOException {
		
//		UdxBuilder udxBuilder=new UdxBuilder();
//		long[] location = {72,10};
//		String ConfigFilePath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\DataHelper\\testData\\BinaryDataConfig.xml";
//		
//		String dataStr = udxBuilder.getData(location,ConfigFilePath);
//		if(dataStr!=""){
//		logger.info("the data string is {}.",dataStr);
//		}else{
//			logger.info("get data error.");
//		}
//		udxBuilder.Finish();
		SchemaNodeTypeHandle.SchemaNodeType2String(ESchemaNodeType.EDTKT_INT);
		
		
		
	}

}
