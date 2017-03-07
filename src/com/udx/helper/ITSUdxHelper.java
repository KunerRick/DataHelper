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
import com.ngis.udx.UdxKernelRealValue;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;

/**
 * Dr.Lu's Fvcom,step3,[output] [ch_its.dat] conversion Tool
 * @author CK
 *
 */
public class ITSUdxHelper {
	
public static UdxDataset ITSToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\ch_its.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
		UdxNode descriptionNode = udx_return_Dt.addChildNode("description", EKernelType.EKT_STRING, 1);
		UdxNode typeDescriptionNode = udx_return_Dt.addChildNode("typeDescription", EKernelType.EKT_STRING, 1);
		UdxNode temperatureNode = udx_return_Dt.addChildNode("temperature", EKernelType.EKT_REAL, 1);
		UdxNode salinityNode = udx_return_Dt.addChildNode("salinity", EKernelType.EKT_REAL, 1);
		//the first
		if((line=br.readLine())!=null){
			line=line.trim();
			((UdxKernelStringValue)descriptionNode.getKernel())
			.setTypedValue(line);
		}else{
			br.close();
			return null;
		}
		//the second
		if((line=br.readLine())!=null){
			line=line.trim();
			((UdxKernelStringValue)typeDescriptionNode.getKernel())
			.setTypedValue(line);
		}else{
			br.close();
			return null;
		}
		//the third
		if((line=br.readLine())!=null){
			line=line.trim();
			String[] dataStrArray=line.split("\\s+");
			if(dataStrArray.length==2){
				((UdxKernelRealValue)temperatureNode.getKernel())
				.setTypedValue(Double.parseDouble(dataStrArray[0]));
				((UdxKernelRealValue)salinityNode.getKernel())
				.setTypedValue(Double.parseDouble(dataStrArray[1]));							
			}else{
				br.close();
				return null;				
			}
		}else{
			br.close();
			return null;
		}		
//////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
   }
   
public static boolean ITSToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=ITSToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_its.xml";
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
   
public static String UdxToITS(UdxDataset dataset) throws Exception{
	   
		if(dataset!=null){		
		}else{
			String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\ch_its.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		int count = dataset.getChildNodeCount();
		for(int i=1;i<=count;i++){
			UdxNode childNode =dataset.getChildNode(i-1);
			if(i<3){
				if(i==1){
				   DataStr.append(" "+((UdxKernelStringValue)childNode.getKernel()).getTypedValue()).append("\n");
				   continue;
				}
				DataStr.append(((UdxKernelStringValue)childNode.getKernel()).getTypedValue()).append("\n");
			}else{
				DataStr.append(String.valueOf(((UdxKernelRealValue)childNode.getKernel()).getTypedValue())+" ");
			}
		}
		DataStr.append("\n");
			
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
}
public static boolean UdxToITS(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToITS(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_its1.dat";
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

