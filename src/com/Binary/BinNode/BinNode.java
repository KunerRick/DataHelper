package com.Binary.BinNode;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.Schema.ESchemaNodeType;

/**
 * 用于读取数据的父类
 * @author Kuner_rick
 *
 */
public class BinNode {
	final Logger logger = LoggerFactory.getLogger(BinNode.class);
	protected String name="";
	protected ESchemaNodeType ENodeType=null;
	protected List<BinNode> ChildNode=null;
	protected int ChildCount = 0;
	
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
	
	public void addChildNode(BinNode binNode){
		if(binNode==null){
			logger.warn("the binary node for adding can not be null.");
			return ;
		}
		if(ChildCount==0){
			this.ChildNode = new ArrayList<BinNode>();
		}
		this.ChildNode.add(binNode);
		this.ChildCount+=1;		
	}
	
	public BinNode getChildNode(int index){
		if(index<0 || index>=this.ChildCount){
			logger.warn("the index = {} is out of range, the ChildCount is {}.",index,this.ChildCount);
			return null;
		}
		return this.ChildNode.get(index);
	}
}
