package com.Binary.BinDataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Binary.Schema.*;

public class StaticSchemaInfo {
	final static Logger logger = LoggerFactory.getLogger(StaticSchemaInfo.class);
	private static SchemaInfo SchemaInfo=null;
	
	public static boolean initStaticSchemaInfo(String SchemaFilePath){
		SchemaRead SR = new SchemaRead(SchemaFilePath);
		if(SR==null||(SchemaInfo=SR.getSchemaInfo())==null){
			logger.warn("SchemaRead failed.");
			return false;
		}					
		return true;
	}
	
    public static SchemaInfo getSchemaInfo(){
	  return SchemaInfo;
	}
}
