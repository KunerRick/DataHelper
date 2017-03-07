package com.dataUtil.DataClass;


public class DataStruct {
	private int Order;
	private BasicDataType BasicDataType;
	private int DataLength;
	private String Value;
	
	public DataStruct(){
		super();
		this.DataLength=0;
		this.Order=0;
		this.BasicDataType =null;
		this.Value = "";
	}
	public DataStruct(int Order,BasicDataType BasicDataType){
		super();
		this.DataLength=0;
		this.Order=Order;
		this.BasicDataType =BasicDataType;
		this.Value = "";
	}
	public DataStruct(BasicDataType BasicDataType){
		super();
		this.DataLength=0;
		this.Order=0;
		this.BasicDataType =BasicDataType;
		this.Value = "";
	}
	public DataStruct(int Order,BasicDataType BasicDataType,int DataLength){
		this.Order = Order;
		this.BasicDataType = BasicDataType;
		this.DataLength = DataLength;
	}
	public int getOrder() {
		return Order;
	}
	public void setOrder(int order) {
		Order = order;
	}
	public BasicDataType getBasicDataType() {
		return BasicDataType;
	}
	public void setBasicDataType(BasicDataType basicDataType) {
		BasicDataType = basicDataType;
	}
	public int getDataLength() {
		return DataLength;
	}
	public void setDataLength(int dataLength) {
		DataLength = dataLength;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	
}
