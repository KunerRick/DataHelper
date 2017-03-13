package com.Binary.BinNode;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.Schema.ESchemaNodeType;
import com.dataUtil.util.BinaryIO;
import com.dataUtil.util.DataFactory;
import com.util.HandleBinary;

public class BinRealListNode extends BinNode{
	final Logger logger = LoggerFactory.getLogger(BinNode.class);
	private List<Double> Data=null;
	
	public BinRealListNode(){
		super();
	}
	public BinRealListNode(String name,ESchemaNodeType NodeType,int  DataSize , long beginSeek){
		super();
		this.setName(name);
		this.setENodeType(NodeType);
		this.setDataSize(DataSize);
		this.setBeginSeek(beginSeek);
	}
	public List<Double> getDataList() {
		if(!connectData()){
			return null;
		}
		return Data;
	}
	/**
	 * 通过下标获取List中对应的值
	 * @param index
	 * @return
	 */
	public double getData(int index) { 
		this.setCurrentIndex(index);
		if(!connectData()){
			return 0.0;
		}
		
		return this.Data.get(index);
		
		
	}
	
	public void  addDoubleData(Double data){
		this.Data.add(data);
	}

	/**
	 * 计算seek,获取二进制文件中的数据并赋值给Data变量
	 * @return
	 */
	private boolean connectData() {
		
		if(this.getDataSize()<=0){
			logger.warn("DataSize is {}.maybe something wrong or have no data.",this.getDataSize());
		}
		//calculate BeginSeek
		Data=new ArrayList<Double>();
		long oneDataLength = 8*this.getDataSize();
		List<Long> ChildCount = new ArrayList<Long>();
		List<Long> LocationIndex = new ArrayList<Long>();
		long count = 0;
		BinNode binNode=new BinNode();
		binNode=this;
		do{
			if(binNode.getParentNode()==null||binNode.getParentNode().equals(null)){
				break;
			}
			
			
			count=binNode.getParentNode().getDataSize();
			
			LocationIndex.add(binNode.getCurrentIndex());
			ChildCount.add(count);
			binNode = binNode.getParentNode(); 
		}while(binNode!=null); 
		if(ChildCount.size()==0) {
			return false;
		}
		long[] dataCount = new long[ChildCount.size()];
		long[] location = new long[ChildCount.size()];
		for(int i=ChildCount.size()-1;i>=0;i--){
			dataCount[ChildCount.size()-i-1]=ChildCount.get(i);
			location[ChildCount.size()-i-1] = LocationIndex.get(i)+1;
		}
		
		
		long seek = DataFactory.getSeekBegin(oneDataLength, location, dataCount);
		
		HandleBinary handleBinary = new HandleBinary(this.getBinaryDataFilepath());
		if(!handleBinary.beginRead()){
			return false;
		}
	
		for(int i=0;i<this.getDataSize();i++){
			this.setBeginSeek(seek+i*8);
			handleBinary.ReadData(this);
		}
		
		if(!handleBinary.endRead()){
			return false;
		}
		
		
	
		return true;
	}
	
}
