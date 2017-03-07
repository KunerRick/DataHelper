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

/**
 * Dr.Lu's Fvcom,step3,[output] [ch_bfw.dat] conversion Tool
 * @author CK
 *
 */
public class BFWUdxHelper {
	
public static UdxDataset BFWToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\ch_bfw.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
		UdxNode descriptionNode=udx_return_Dt.addChildNode("descriptionNode", EKernelType.EKT_STRING, 1);
		UdxNode countNode = udx_return_Dt.addChildNode("countNode", EKernelType.EKT_INT, 1);
		
		do{
			if((line=br.readLine())!=null){
				line=line.trim();
				((UdxKernelStringValue)descriptionNode.getKernel()).setTypedValue(line);
			}
			if((line=br.readLine())!=null){
				line=line.trim();
				((UdxKernelIntValue)countNode.getKernel()).setTypedValue(Integer.parseInt(line));
			}			
		}while(line!=null);
				
//////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
   }
   
public static boolean BFWToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=BFWToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_bfw.xml";
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
   
public static String UdxToBFW(UdxDataset dataset) throws Exception{
	  
		if(dataset!=null){		
		}else{
			 String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
				UdxDataset udx_return_Dt = new UdxDataset(dllPath);
				String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\ch_bfw.xml";
				udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		
			UdxNode descriptionNode=dataset.getChildNode(0);
			UdxNode countNode = dataset.getChildNode(1);
			
			DataStr.append(" "+((UdxKernelStringValue)
					descriptionNode.getKernel()).getTypedValue()).append("\n");
			DataStr.append(" "+String.valueOf(((UdxKernelIntValue)countNode.getKernel()).getTypedValue())).append("\n");
			
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
}
public static boolean UdxToBFW(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToBFW(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_bfw1.dat";
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

