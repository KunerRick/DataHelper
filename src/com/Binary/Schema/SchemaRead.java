package com.Binary.Schema;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.HandleXml;

public class SchemaRead {
	final static Logger logger = LoggerFactory.getLogger(SchemaRead.class);
	private String SchemaFilePath="";
	private SchemaInfo SchemaInfo=null;
	public SchemaRead(){
		super();
	}
	public SchemaRead(String SchemaFilePath){
		this.setSchemaFilePath(SchemaFilePath);
	}
	public String getSchemaFilePath() {
		return this.SchemaFilePath;
	}
	public void setSchemaFilePath(String schemaFilePath) {
		this.SchemaFilePath = schemaFilePath;
	}
	public SchemaInfo getSchemaInfo() {
		this.SchemaInfo = new SchemaInfo();
		if(!setSchemaInfo()){
			return null;
		};
		return this.SchemaInfo;
	}
	private boolean setSchemaInfo() {
		Document doc = null;
		if(this.SchemaFilePath==""){
			logger.warn("The schema file is null,please set.");
		}
		try {
			doc=HandleXml.parse(this.SchemaFilePath);						
		} catch (DocumentException e) {			
			logger.warn("to read {} failed. info as follows:\n {} \n",this.SchemaFilePath,e.toString());
			return false;
			
		}
		Element root =doc.getRootElement();		
		List UdxSchemaNodes = root.selectNodes("/UdxDeclaration/UdxNode");
		if(UdxSchemaNodes.size()<=0){
			logger.error(" Udx schema node does not exist.");
			return false;
		}
		//根节点
		SchemaInfo rootInfo = new SchemaInfo(); 
		rootInfo.setNodeName("rootNode");
		this.SchemaInfo=rootInfo;
		
		
		
		for(int i=0;i<UdxSchemaNodes.size();i++){
			 Element UdxSchemaNodesElem=(Element) UdxSchemaNodes.get(i);
			 SchemaInfo childSchemaInfo = new SchemaInfo();			
			this.foreachUdxSchemaNodes(UdxSchemaNodesElem,childSchemaInfo);	
			rootInfo.setDataSize(rootInfo.getDataSize()+1);
			 rootInfo.addChildNode(childSchemaInfo);
		}			   
		return true;
		
	}
	private  boolean foreachUdxSchemaNodes(Element UdxSchemaNodesElem,SchemaInfo schemaInfo){
		if(UdxSchemaNodesElem==null){
			logger.warn("UdxSchemaNodesElem is null");
			return false;
		}
		List elements= UdxSchemaNodesElem.elements();
		if(elements.size()==0){
			return false;
		}
		for(int i=0;i<elements.size();i++){
			
			Element childNodeElem=(Element)elements.get(i);
			String name=childNodeElem.attributeValue("name");
			String NodeTypeStr = childNodeElem.attributeValue("type");
			long DataSize = Long.parseLong(childNodeElem.attributeValue("size"));
			ESchemaNodeType NodeType = SchemaNodeTypeHandle.String2SchemaNodeType(NodeTypeStr);
			
			String NodeDescription = childNodeElem.attributeValue("description");
			schemaInfo.setNodeName(name);
			schemaInfo.setNodeType(NodeType);
			schemaInfo.setDataSize(DataSize);
			schemaInfo.setDescription(NodeDescription);			
			
			SchemaInfo childSchemaInfo=new SchemaInfo();
			if(foreachUdxSchemaNodes(childNodeElem,childSchemaInfo)){
			   schemaInfo.addChildNode(childSchemaInfo);
			}
			
		}
		return true;
		
	}
	
	
}
