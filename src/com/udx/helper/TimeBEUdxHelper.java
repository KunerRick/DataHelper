package com.udx.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ngis.udx.UdxDataset;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;
import com.udx.util.UdxHandlUtil;
import com.udx.util.StringUtil.AlignType;
import com.udx.util.UdxHandlUtil.UdxDataType;

/**
 * Dr.Lu's Fvcom,step4,[input] [time_BE.dat] conversion Tool
 * @author CK
 *
 */
public class TimeBEUdxHelper {
	
public static UdxDataset TimeBEToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\time_BE.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
		if((line=br.readLine())!=null){			
			UdxHandlUtil.createNode(udx_return_Dt, UdxDataType.UdxKernelIntArray, "beginTime", line.trim());
		}else{
			br.close();
			return null;
		}
		if((line=br.readLine())!=null){			
			UdxHandlUtil.createNode(udx_return_Dt, UdxDataType.UdxKernelIntArray, "endTime", line.trim());
		}else{
			br.close();
			return null;
		}				
//////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
   }
   
public static boolean TimeBEToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=TimeBEToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\time_BE.xml";
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
   
public static String UdxToTimeBE(UdxDataset dataset) throws Exception{
	  
		if(dataset!=null){		
		}else{
			 String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
				UdxDataset udx_return_Dt = new UdxDataset(dllPath);
				String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\time_BE.xml";
				udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		for(int i=0;i<dataset.getChildNodeCount();i++){
			DataStr.append(UdxHandlUtil.getStrFromUdxNode(dataset.getChildNode(i), 3, AlignType.None)+"\n");
		}			
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
}
public static boolean UdxToTimeBE(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToTimeBE(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\time_BE1.dat";
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

