package com.ngis.udx;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;


public class UdxHandleApi 
{
	
	public UdxHandleApi(String dllPaths)
	{
		
		System.load(dllPaths + "\\nxid.dll");
		System.load(dllPaths + "\\nxdat.mini.dll");
		System.load(dllPaths + "\\nxdat.jni.dll");
		
//		System.loadLibrary("nxid");
//		System.loadLibrary("nxdat.mini");
//		System.loadLibrary("nxdat.jni");
	}
	
	public native int getVersion();
	
	public native int createUdxDataset();
	
	public native int getDatesetNode(int dxObj);

	public native int addNode(int parentNode, String name, int type, int length);

	public native void releaseUdxDataset(int dxObj);
	
	public native int getCellLength(int nodeObj);

	///////////////////////////////////////////////////////////////////////////////////////////////
	public native boolean setNodeIntValue(int nodeObj, int val);

	public native boolean setNodeRealValue(int nodeObj, double val);

	public native boolean setNodeStringValue(int nodeObj, String val);
	
	public native boolean setNodeVector2dValue(int nodeObj, double x, double y);

	public native boolean setNodeVector3dValue(int nodeObj, double x, double y, double z);

	public native boolean setNodeVector4dValue(int nodeObj, double x, double y, double z, double m);

	///////////////////////////////////////////////////////////////////////////////////////////////
	public native boolean addNodeIntArrayValue(int nodeObj, int idx, int val);

	public native boolean addNodeRealArrayValue(int nodeObj, int idx, double val);

	public native boolean addNodeStringArrayValue(int nodeObj, int idx, String val);
	
	public native boolean addNodeVector2dArrayValue(int nodeObj, int idx, double x, double y);

	public native boolean addNodeVector3dArrayValue(int nodeObj, int idx, double x, double y, double z);

	public native boolean addNodeVector4dArrayValue(int nodeObj, int idx, double x, double y, double z, double m);

	///////////////////////////////////////////////////////////////////////////////////////////////
	public native int getNodeIntValue(int node);
	
	public native double getNodeRealValue(int node);
	
	public native String getNodeStringValue(int node);
	
	public native double[] getNodeVector2dValue(int node);
	
	public native double[] getNodeVector3dValue(int node);
	
	public native double[] getNodeVector4dValue(int node);
	
///////////////////////////////////////////////////////////////////////////////////////////////
	public native int getNodeIntArrayValue(int node, int idx);
	
	public native double getNodeRealArrayValue(int node, int idx);
	
	public native String getNodeStringArrayValue(int node, int idx);
	
	public native double[] getNodeVector2dArrayValue(int node, int idx);
	
	public native double[] getNodeVector3dArrayValue(int node, int idx);
	
	public native double[] getNodeVector4dArrayValue(int node, int idx);

///////////////////////////////////////////////////////////////////////////////////////////////
	public native int formatToXmlFile(int dxObj, String fileName);
	
	public native int loadFromXmlFile(int dxObj, String fileName, UdxNode node);
	
	public native String formatToXmlStream(int dxObj);

	public native int  loadFromXmlStream(int dxObj, String xmlStr, UdxNode node);
	
////////////////////////////////////////////////////////////////////////////////////////////////
}
