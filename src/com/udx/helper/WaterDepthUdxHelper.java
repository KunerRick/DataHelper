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
import com.ngis.udx.UdxKernelRealArray;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;
import com.udx.util.StringUtil;

/**
 * Dr.Lu's Fvcom,step3,[output] [ch_dep.dat] conversion Tool
 * @author CK
 *
 */
public class WaterDepthUdxHelper {
	
public static UdxDataset WaterDepthToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\ch_dep.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
		UdxNode headNode = udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 1);
		UdxNode bodyNode = udx_return_Dt.addChildNode("body", EKernelType.EKT_LIST, 1);
		
		UdxNode countNode = headNode.addChildNode("dataCount",EKernelType.EKT_INT,1);
		int count = 0;
		do{
			if((line=br.readLine())!=null){
				line=line.trim();
				String[] dataStrArray=line.split("\\s+");
				if(dataStrArray.length==3){
					count+=1;
					UdxNode dataNode=bodyNode.addChildNode(String.valueOf(count), EKernelType.EKT_REAL, 3);
					for(int i=0;i<dataStrArray.length;i++){
						((UdxKernelRealArray)dataNode.getKernel())
						.addTypedValue(Double.parseDouble(dataStrArray[i]));
					}			
				}else{
					System.out.println("the format of the data is not correct.");
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
   
public static boolean WaterDepthToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=WaterDepthToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_dep.xml";
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
   
public static String UdxToWaterDepth(UdxDataset dataset) throws Exception{
	   
		if(dataset!=null){		
		}else{
			String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\ch_dep.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		UdxNode bodyNode = dataset.getChildNode(1);
		
		for(int i=0;i<bodyNode.getChildNodeCount();i++){
			UdxNode dataNode=bodyNode.getChildNode(i);
			int ArrayLength = ((UdxKernelRealArray)dataNode.getKernel()).getLength();
			if(ArrayLength!=3) {
				System.out.println("the format of this Udx is not correct.");
				return null;
			}
			for(int j=0;j<ArrayLength;j++){
				DataStr.append(StringUtil.RightAlign(String.valueOf(
						((UdxKernelRealArray)dataNode.getKernel()).getTypedValueByIndex(j)),18));						
			}
			DataStr.append("\n");
		}
		
		
		
			
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
}
public static boolean UdxToWaterDepth(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToWaterDepth(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_dep1.dat";
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

