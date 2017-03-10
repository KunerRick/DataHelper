package com.datahelper.main;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.BinaryTest;
import com.Binary.BinDataset.BinUdxDataset;
import com.Binary.BinNode.BinNode;
import com.Binary.BinNode.BinRealListNode;
import com.Binary.Schema.ESchemaNodeType;
import com.Binary.Schema.SchemaInfo;
import com.Binary.Schema.SchemaNodeTypeHandle;
import com.Binary.Schema.SchemaRead;
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
		BinUdxDataset udxDataset = new BinUdxDataset("E:\\Kuner\\work\\JWorkSpace\\DataHelper\\testData\\UVSchema.xml");
		BinNode binNode = udxDataset.getRootNode();
		((BinRealListNode)binNode.getChildNode(0).getChildNode(0)).setSeekBegin(12);
		long a=((BinRealListNode)binNode.getChildNode(0).getChildNode(0)).getSeekBegin();
		logger.warn("finish {}.",a);
		
		
	}

}
