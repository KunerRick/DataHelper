package com.udx.helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ngis.udx.EKernelType;
import com.ngis.udx.UdxDataset;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;
import com.udx.util.DataUtil;
import com.udx.util.StringUtil;
import com.udx.util.StringUtil.AlignType;
import com.udx.util.UdxHandlUtil;
import com.udx.util.UdxHandlUtil.UdxDataType;

/**
 * Dr.Lu's Fvcom,step4,[output] [cha_AUV.txt2] conversion Tool(未完成， 因转换数据很大，并且转换之后精度可能丢失，只转换了第一组数据)
 * @author CK
 *
 */
public class AUVUdxHelper {
	
public static UdxDataset AUVToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\output\\cha_AUV.txt2";
			
			//fileFullPath="D:\\Workspace\\FvCom\\testData\\output\\cha_ET.txt2";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		//BufferedReader br;
		//br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		
		 DataInputStream br=new DataInputStream(  
                 new BufferedInputStream(  
                 new FileInputStream(fileFullPath)));  
		//String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
		int TriangleCount=23543;
		//int pointsCount=13270;
		int oneLineDataCount=2;
		byte[] oneData=new byte[8];	
		int dataGroupCount=0;
		int oneUVIndex=0;
		int dataIndex=0;
		Double[] oneUV = new Double[2];
		int End=-1;
		//创建xml
		UdxNode headNode= udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 2);
		UdxNode bodyNode=udx_return_Dt.addChildNode("body", EKernelType.EKT_LIST, 1);
		UdxHandlUtil.createNode(headNode, UdxDataType.UdxKernelIntValue, "oneGroupDataCount", String.valueOf(TriangleCount));
		
		do{			
			UdxNode dataGroupNode=bodyNode.addChildNode(String.valueOf(dataGroupCount+1), EKernelType.EKT_LIST, 1);
			do{
				End=br.read(oneData, 0, 8);
				if(End!=-1){
					oneUV[oneUVIndex++]=DataUtil.bytes2Double(oneData);											
					if(oneUVIndex==oneLineDataCount){
						dataIndex+=1;
						String dataStr=String.valueOf(oneUV[0])+"   "+String.valueOf(oneUV[1]);
						UdxHandlUtil.createNode(dataGroupNode, UdxDataType.UdxKernelRealArray, String.valueOf(dataIndex), dataStr);					
						oneUVIndex=0;
						if(dataIndex==TriangleCount){
							dataIndex=0;
							dataGroupCount+=1;
							System.out.println(dataGroupCount+"\n");
							break;
							
						}
					}			
				}
			}while(End!=-1);
			break;  //暂时只保存一组数据
		}while(End!=-1);
		UdxHandlUtil.createNode(headNode, UdxDataType.UdxKernelIntValue, "dataGroupCount", String.valueOf(dataGroupCount));
//////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
   }
   
public static boolean AUVToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=AUVToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\output\\cha_AUV.xml";
	   }else if(SavePath.equals("")||SavePath==null){
		   System.out.println("the SavePath is not right.");
		   return false;
	   }
	 File outputFile=new File(SavePath);
	 if(!outputFile.exists()){
		 if(!outputFile.getParentFile().exists()){
			 outputFile.getParentFile().mkdirs();
		 }
	 }
	 if(dataset!=null){
	   dataset.FormatToXmlFile(SavePath);
	   return true;
	 }else{
	    System.out.println("dataset is null.");
	   return false;
	 }
   }
   
public static String UdxToAUV(UdxDataset dataset) throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		UdxDataset udx_return_Dt = new UdxDataset(dllPath);
		String fileName="D:\\Workspace\\FvCom\\tmpData\\output\\cha_AUV.xml";
		udx_return_Dt.LoadFromXmlFile(fileName);
		if(dataset!=null){		
		}else{
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		UdxNode bodyNode=dataset.getChildNode(1);
		for(int i=0;i<bodyNode.getChildNodeCount();i++){
			UdxNode childNode=bodyNode.getChildNode(i);
			for(int j=0;j<100;j++){
				String a =UdxHandlUtil.getStrFromUdxNode(childNode.getChildNode(j), 2, AlignType.None);
				DataStr.append(a+" ");
			}
			
		}
			
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
}
public static boolean UdxToAUV(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToAUV(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\output\\cha_AUV1.txt2";
	   }else if(SavePath.equals("")||SavePath==null){
		   System.out.println("the SavePath is not right.");
		   return false;
	   }
	   DataUtil dataUtil=new DataUtil();	   
	   return dataUtil.saveBinaryFile(DataStr, SavePath);
	  
}
}

