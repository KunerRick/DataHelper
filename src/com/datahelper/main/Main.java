package com.datahelper.main;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.BinaryTest;
import com.Binary.BinDataset.BinUdxDataset;
import com.Binary.BinNode.BinNode;
import com.Binary.BinNode.BinRealListNode;
import com.Binary.BinNode.BinRealNode;
import com.Binary.Schema.ESchemaNodeType;
import com.Binary.Schema.SchemaInfo;
import com.Binary.Schema.SchemaNodeTypeHandle;
import com.Binary.Schema.SchemaRead;
import com.dataUtil.util.UdxBuilder;



public class Main {
	final static Logger logger=LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) throws IOException {
		
		UdxBuilder udxBuilder=new UdxBuilder();
		long[] location = {2,5};
		String ConfigFilePath = "E:\\Kuner\\work\\JWorkSpace\\DataHelper\\testData\\BinaryDataConfig.xml";
		
		String dataStr = udxBuilder.getData(location,ConfigFilePath);
		if(dataStr!=""){
		logger.info("the data string is \n {}",dataStr);
		}else{
			logger.info("get data error.");
		}
		udxBuilder.Finish();
		BinUdxDataset udxDataset = new BinUdxDataset("E:\\Kuner\\work\\JWorkSpace\\DataHelper\\testData\\UVSchema.xml","E:\\Kuner\\model\\二进制数据\\cha_AUV.txt2");
		BinNode RootNode = udxDataset.getRootNode();
		BinNode DataList1=  RootNode.getChildNode(0);		
		BinNode DataList2 = DataList1.getChildNode(1);
		BinNode DataIndex = DataList2.getChildNode(4);
		List<Double> dd =((BinRealListNode)DataIndex).getDataList();
		logger.warn("finish \n {}",dd);
		
		
	}

}
