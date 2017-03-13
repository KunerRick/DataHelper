package com.Binary.BinNode;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.Schema.ESchemaNodeType;
import com.Binary.Schema.SchemaInfo;
import com.Binary.Schema.SchemaNodeTypeHandle;

/**
 * 用于读取数据的父类
 * @author Kuner_rick
 *
 */
public class BinNode {
	final static Logger logger = LoggerFactory.getLogger(BinNode.class);
	
	private String name="";                        //节点名称
	private ESchemaNodeType ENodeType=null;        //节点类型
	private int ChildCount = 0;				     //子节点数目                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	private String BinaryDataFilepath = "";		 //二进制文件路径
	private long dataSize = 0;					     //单个节点中存放的数据的个数
	protected boolean IsFirstAdd=true;               //是否第一次添加子节点
	private long BeginSeek = 0;					 //起始读取位置
	private BinNode  ParentNode = null;		     //父节点
	private long currentIndex = 0;               //当前所取得节点索引
	private SchemaInfo ThisSchemaInfo = null;
	
	public long getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(long currentIndex) {
		this.currentIndex = currentIndex;
	}
	public BinNode getParentNode() {
		return ParentNode;
	}
	public void setParentNode(BinNode parentNode) {
		ParentNode = parentNode;
	}
	public long getBeginSeek() {
		return BeginSeek;
	}
	public void setBeginSeek(long beginSeek) {
		BeginSeek = beginSeek;
	}
	public String getBinaryDataFilepath() {
		return BinaryDataFilepath;
	}
	public void setBinaryDataFilepath(String binaryDataFilepath) {
		BinaryDataFilepath = binaryDataFilepath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null||name.equals("")){
			logger.warn("the name can not be null.");
			return ;
		}
		this.name = name;
	}
	public ESchemaNodeType getENodeType() {
		return ENodeType;
	}
	public void setENodeType(ESchemaNodeType eNodeType) {
		if(eNodeType==null){
			logger.warn("the schema node type can not be null.");
		}
		this.ENodeType = eNodeType;
	}
		
	public BinNode getChildNode(int index){
		if(index<0 || index>=this.dataSize){
			logger.warn("the index = {} is out of range, the ChildCount is {}.",index,this.dataSize);
			return null;
		} 
		if(this.getThisSchemaInfo()==null){
			logger.warn("this node have no schemaInfo ,please set this Property.");
			return null;
		}
		if(this.getThisSchemaInfo().getChildCount()<=0){
			logger.warn("have no childNode,please check Schema.");
			return null;
		}
		
		
		BinNode ChildNode = SchemaNodeTypeHandle.instantiateBinNode(this.getThisSchemaInfo().getChildNode(0).getNodeType());
		SchemaInfo  ChildSchema = this.getThisSchemaInfo().getChildNode(0);
		ChildNode.setCurrentIndex(index);
		ChildNode.setThisSchemaInfo(ChildSchema);
		ChildNode.setParentNode(this);	
		ChildNode.setBinaryDataFilepath(this.getBinaryDataFilepath());
		ChildNode.setENodeType(ChildSchema.getNodeType());
		ChildNode.setDataSize(ChildSchema.getDataSize());
		ChildNode.setName(ChildSchema.getNodeName());
		return ChildNode;
	}
	public long getDataSize() {
		if(dataSize==0){
			if(this.getThisSchemaInfo().getDataSize()!=0){
				dataSize=this.getThisSchemaInfo().getDataSize();
			}
		}
		return dataSize;
	}
	public void setDataSize(long l) {
		this.dataSize = l;
	}
	public SchemaInfo getThisSchemaInfo() {
		return ThisSchemaInfo;
	}
	public void setThisSchemaInfo(SchemaInfo thisSchemaInfo) {
		ThisSchemaInfo = thisSchemaInfo;
	}
	
	
}
