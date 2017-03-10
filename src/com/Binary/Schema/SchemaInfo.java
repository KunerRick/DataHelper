package com.Binary.Schema;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaInfo {
	final static Logger logger = LoggerFactory.getLogger(SchemaInfo.class);
	private String NodeName="";
	private ESchemaNodeType NodeType=null;
	private String Description=null;
	private List<SchemaInfo> ChildNode=null;
	private int ChildCount=0;
	public String getNodeName() {
		return NodeName;
	}
	public void setNodeName(String nodeName) {
		NodeName = nodeName;
	}
	public ESchemaNodeType getNodeType() {
		return NodeType;
	}
	public void setNodeType(ESchemaNodeType nodeType) {
		NodeType = nodeType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getChildCount(){
		return ChildCount;
	}
	public void addChildNode(SchemaInfo schemaInfo){
		if(schemaInfo==null){
			return;
		}
		if(this.ChildCount==0){
			this.ChildNode=new ArrayList<SchemaInfo>();
		}
		this.ChildNode.add(schemaInfo);
		this.ChildCount+=1;
	}
	
	public SchemaInfo getChildNode(int index){
		return this.ChildNode.get(index);
	}
	
	public static void foreachSchemaInfo(SchemaInfo schemaInfo){
		if(schemaInfo==null){
			return ;
		}	
			for(int i=0;i<schemaInfo.getChildCount();i++){
				logger.info(schemaInfo.getNodeName());				
				foreachSchemaInfo(schemaInfo.getChildNode(i));
			}						
	}
	
}
