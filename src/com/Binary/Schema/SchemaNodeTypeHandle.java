package com.Binary.Schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaNodeTypeHandle {
	final static Logger logger = LoggerFactory.getLogger(SchemaNodeTypeHandle.class);
	
	public static String SchemaNodeType2String(ESchemaNodeType pType){
		String TypeStr="";
		if(pType==null) {
			logger.warn("the ESchemaNodeType is null,please check.");
			return "";
		}
		switch(pType){
        case EDTKT_INT :	
        	TypeStr="DTKT_INT";
        	break;
		case EDTKT_INT_LIST:
			TypeStr="DTKT_LIST";
			break;
		case EDTKT_REAL:
			TypeStr="DTKT_REAL";
			break;
		case EDTKT_REAL_LIST:
			TypeStr="DTKT_REAL | DTKT_LIST";
			break;
		case EDTKT_VECTOR2:
			TypeStr="DTKT_VECTOR2D";
			break;
		case EDTKT_VECTOR2_LIST:
			TypeStr="DTKT_VECTOR2D | DTKT_LIST";
			break;
		case EDTKT_VECTOR3:	
			TypeStr="DTKT_VECTOR3D";
			break;
		case EDTKT_VECTOR3_LIST:
			TypeStr="DTKT_VECTOR3D | DTKT_LIST";
			break;
		case EDTKT_VECTOR4:	
			TypeStr="DTKT_VECTOR4D";
			break;
		case EDTKT_VECTOR4_LIST:
			TypeStr="DTKT_VECTOR4D | DTKT_LIST";
			break;
		case EDTKT_STRING:	
			TypeStr="DTKT_STRING";
			break;
		case EDTKT_STRING_LIST:
			TypeStr="DTKT_STRING | DTKT_LIST";
			break;
		case EDTKT_NODE:
			TypeStr="DTKT_ANY";
			break;
		case EDTKT_LIST:	
			TypeStr="DTKT_LIST";
			break;
		case EDTKT_MAP:	
			TypeStr="DTKT_MAP";
			break;
		case EDTKT_TABLE:
			TypeStr="DTKT_TABLE";
			break;
			
			
		default:
			logger.warn("the ESchemaNodeType is not correct,please check.");
			return "";			
		}
		
		return TypeStr;
	}
	
	public static ESchemaNodeType String2SchemaNodeType(String TypeStr){
		if(TypeStr==null||TypeStr.equals("")){
			logger.warn("the TypeStr can not be null.");
		}
		ESchemaNodeType SchemaNodeType=null;
		switch(TypeStr){
		
		}
		return SchemaNodeType;
	}
}
