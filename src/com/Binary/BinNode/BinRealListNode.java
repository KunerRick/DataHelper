package com.Binary.BinNode;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.Schema.ESchemaNodeType;

public class BinRealListNode extends BinNode{
	final Logger logger = LoggerFactory.getLogger(BinNode.class);
	private List<Double> Data=null;
	private int dataCount = 0;
	private long seekBegin = 0;
	public BinRealListNode(){
		super();
	}
	public BinRealListNode(String name,ESchemaNodeType NodeType,int  DataCount , long beginSeek){
		super();
		this.setName(name);
		this.setENodeType(NodeType);
		this.setDataCount(DataCount);
		this.setSeekBegin(beginSeek);
	}
	public long getSeekBegin() {
		return seekBegin;
	}
	public void setSeekBegin(long seekBegin) {
		this.seekBegin = seekBegin;
	}
	public int getDataCount() {
		return dataCount;
	}
	public List<Double> getDataList() {
		return Data;
	}
	
	public double getData(int index) {                      
		return Data.get(index);
		
	}

	
	private void setData(List<Double> Data) {
		  if(Data==null||Data.size()<=0){
			  logger.warn("the data is null or length <= 0 .");
			  return;
		  }
		this.Data = Data;
		this.setDataCount(Data.size());
	}
	
	private void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	
}
