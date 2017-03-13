package com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.BinNode.BinNode;
import com.Binary.BinNode.BinRealListNode;
import com.dataUtil.util.BinaryIO;

public class HandleBinary {
	final Logger logger = LoggerFactory.getLogger(HandleBinary.class);
	private RandomAccessFile RAfile = null;
	private String BinaryFilepath = "";
	public HandleBinary(String BinaryFilepath){
		
	
		this.BinaryFilepath = BinaryFilepath;
	}
	
	public boolean beginRead(){
		if(BinaryFilepath==null||BinaryFilepath.equals("")){
			logger.warn("the Binary file path can not be null.");
			return false;
		}
		File file = new File(this.BinaryFilepath);
		if(!file.exists()){
			logger.warn("the Binary file : {} does not exist.",this.BinaryFilepath);
			return false;
		}
		try {
			this.RAfile = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Some thing wrong at random access file initialization ,the error info as follows: \n {} .",e.getMessage());
			return false;
		}
		
		return true;
	}
	/***
	 * 
	 * @param BeginSeek
	 * 起始读取位置
	 * @param binNode
	 *    BinNode 节点
	 * @return
	 */
	public boolean ReadData(BinNode binNode){
		
		if(binNode==null){
			logger.warn("binNode can not be null.");
			return false;
		}
		if(binNode.getBeginSeek()<0){
			logger.warn("begin seek can not less than 0");
			return false;
		}
		if(binNode.getENodeType()==null){
			logger.warn("the node type of binNode can not be null.");
			return false;
		}
		switch(binNode.getENodeType()){
		case EDTKT_REAL_LIST:
			BinRealListNode Node = (BinRealListNode) binNode;
			Node.addDoubleData(BinaryIO.readDouble(RAfile,binNode.getBeginSeek()));
			break;
		default:			
			logger.warn("can't  suport this type: {}.",binNode.getENodeType().name());
			return false;
		}
		
		return true;
	}
	
	public boolean endRead(){
		if(this.RAfile != null){
			try {
				this.RAfile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Some thing wrong at random access file close ,the error info as follows: \n {} .",e.getMessage());
				return false;
			}
		}
		return true;
	}
	
}
