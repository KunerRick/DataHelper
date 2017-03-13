package com.Binary.BinDataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.BinNode.BinNode;
import com.Binary.BinNode.BinRealListNode;
import com.Binary.Schema.ESchemaNodeType;
import com.Binary.Schema.SchemaInfo;
import com.Binary.Schema.SchemaNodeTypeHandle;
import com.Binary.Schema.SchemaRead;

/**
 *  * 初始化，建立和二进制文件的联系
 * ①：从schema中读取相关UdxNode的信息，并存为SchemaInfo的格式
 * ②：根据得到的SchemaInfo的格式，建立一个BinUdxDataSet,主要为BinNode构成
 * @author Administrator
 *
 */
public class BinUdxDataset {
	final Logger logger = LoggerFactory.getLogger(BinUdxDataset.class);
	private BinNode binNode =null;
	public BinUdxDataset(String SchemaFilePath,String BinaryFilePath){
		if(!initDataset(SchemaFilePath,BinaryFilePath)){
			return;
		};
	}
	public BinNode getRootNode(){
		
		
		
		return this.binNode;
	}
	
	private boolean initDataset(String SchemaFilePath,String BinaryFilePath){
		if(!StaticSchemaInfo.initStaticSchemaInfo(SchemaFilePath)){
			logger.warn("Init schemaInfo failed.");
			return false;
		}
		BinNode rootNode = SchemaNodeTypeHandle.instantiateBinNode(StaticSchemaInfo.getSchemaInfo().getNodeType());
		SchemaInfo a=StaticSchemaInfo.getSchemaInfo();
		rootNode.setThisSchemaInfo(a);
		rootNode.setBinaryDataFilepath(BinaryFilePath);
		rootNode.setName(a.getNodeName());
		rootNode.setDataSize(a.getDataSize());
		binNode=rootNode;
		return true;
	}
	
	
	
}
