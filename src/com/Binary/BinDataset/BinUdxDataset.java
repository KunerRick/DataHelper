package com.Binary.BinDataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.BinNode.BinNode;
import com.Binary.BinNode.BinRealListNode;
import com.Binary.Schema.ESchemaNodeType;
import com.Binary.Schema.SchemaInfo;
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
	private SchemaInfo SchemaInfo=null;
	private BinNode binNode =null;
	public BinUdxDataset(String SchemaFilePath){
		if(!initDataset(SchemaFilePath)){
			return;
		};
	}
	public BinNode getRootNode(){
		return this.binNode;
	}
	
	private boolean initDataset(String SchemaFilePath){
		SchemaRead SR = new SchemaRead(SchemaFilePath);
		if((this.SchemaInfo=SR.getSchemaInfo())==null){
			return false;
		}
		this.binNode=instantiateBinNode(this.SchemaInfo.getNodeType());
		this.foreachSchemaInfo(this.SchemaInfo,this.binNode);
		
		
		return true;
	}
	private boolean foreachSchemaInfo(SchemaInfo SchemaInfo,BinNode binNode){
		if(SchemaInfo==null) return false;
		
		if(binNode==null){
			return false;
		}
		binNode.setName(SchemaInfo.getNodeName());
		binNode.setENodeType(SchemaInfo.getNodeType());
		int count = SchemaInfo.getChildCount();
		for(int i=0;i<count;i++){
			BinNode childBinNode=instantiateBinNode(SchemaInfo.getChildNode(i).getNodeType());
			foreachSchemaInfo(SchemaInfo.getChildNode(i),childBinNode);
			binNode.addChildNode(childBinNode);
		}
		return true;
		
	}
	/**
	 * 实例化为具体的数据类型的节点(未齐全)
	 * @param NodeType
	 * @return
	 */
	private BinNode instantiateBinNode(ESchemaNodeType NodeType){
		if(NodeType==null){
			logger.warn("the NodeType in instantiateBinNode is null.");
			return null;
		}
		BinNode binNode=null;
		switch(NodeType){
		case EDTKT_LIST:
			binNode=new BinNode();
			break;
		case EDTKT_REAL_LIST:
			binNode = new BinRealListNode();
			break;
		default:
			return null;		
		}
		return binNode;
	}
	
	
}
