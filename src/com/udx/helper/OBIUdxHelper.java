package com.udx.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import com.udx.util.StringUtil;

/**
 * Dr.Lu's Fvcom,step3,[output] [ch_obc.dat] conversion Tool
 * 开边界索引文件转换
 * @author CK
 *
 */
public class OBIUdxHelper {
	
public static UdxDataset OBIToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\ch_obc.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
		UdxNode headNode =udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 2);
		UdxNode bodyNode =udx_return_Dt.addChildNode("body", EKernelType.EKT_LIST, 1);		
		UdxNode descriptionNode = headNode.addChildNode("description",EKernelType.EKT_STRING, 1);
		UdxNode countNode = headNode.addChildNode("dataCount", EKernelType.EKT_INT, 1);
		if((line=br.readLine())!=null){
			line=line.trim();
			((UdxKernelStringValue)descriptionNode.getKernel()).setTypedValue(line);
		}else{
			br.close();
			return null;
		}
		int count=0;
		do{
			if((line=br.readLine())!=null){
				count+=1;
				line=line.trim();
				String[] dataArray=line.split("\\s+");
				if(dataArray.length==3){
					UdxNode childNode=bodyNode.addChildNode(String.valueOf(dataArray[0]), EKernelType.EKT_NODE, 2);
					UdxNode indexNode=childNode.addChildNode("index", EKernelType.EKT_INT, 1);
					((UdxKernelIntValue)indexNode.getKernel()).setTypedValue(Integer.parseInt(dataArray[1]));
					UdxNode stateNode=childNode.addChildNode("state", EKernelType.EKT_INT, 1);
					((UdxKernelIntValue)stateNode.getKernel()).setTypedValue(Integer.parseInt(dataArray[2]));
				}else{
					br.close();
					return null;
				}
			}
		}while(line!=null);		
		((UdxKernelIntValue)countNode.getKernel()).setTypedValue(count);
//////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
   }
   
public static boolean OBIToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=OBIToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_obc.xml";
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
   
public static String UdxToOBI(UdxDataset dataset) throws Exception{
	  
		if(dataset!=null){		
		}else{
			 String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
				UdxDataset udx_return_Dt = new UdxDataset(dllPath);
				String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\ch_obc.xml";
				udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		UdxNode descriptionNode=dataset.getChildNode(0).getChildNode(0);
		DataStr.append(" "+((UdxKernelStringValue)descriptionNode.getKernel()).getTypedValue()+"\n");
		
		UdxNode bodyNode=dataset.getChildNode(1);
		int listLen=bodyNode.getChildNodeCount();
		
		for(int i=0;i<listLen;i++){
			UdxNode childNode = bodyNode.getChildNode(i);
			DataStr.append(StringUtil.RightAlign(childNode.getName(), 12));
			for(int j=0;j<childNode.getChildNodeCount();j++){
				UdxNode dataNode=childNode.getChildNode(j);
				DataStr.append(StringUtil.RightAlign(
						String.valueOf(((UdxKernelIntValue)dataNode.getKernel()).getTypedValue()), 12));
			}
			DataStr.append("\n");
		}			
		////////////////////////////////////////////////////////////////////////////////////////		
		return DataStr.toString();
}
public static boolean UdxToOBI(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToOBI(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_obc1.dat";
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
	   
	   try {
			File dataFile=new File(SavePath);
			if(dataFile.exists()){
				dataFile.delete();
			}
			dataFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(dataFile));
			out.append(DataStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return true;
}
}

