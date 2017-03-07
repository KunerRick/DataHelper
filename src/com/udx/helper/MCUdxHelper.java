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
import com.ngis.udx.UdxKernelRealValue;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;
import com.udx.util.StringUtil;

/**
 * Dr.Lu's Fvcom,step3,[output] [ch_mc.dat] conversion Tool
 * @author CK
 *
 */
public class MCUdxHelper {
	
public static UdxDataset MCToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\ch_mc.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
		UdxNode descriptionNode = udx_return_Dt.addChildNode("description", EKernelType.EKT_STRING, 1);
		UdxNode minNode = udx_return_Dt.addChildNode("min", EKernelType.EKT_REAL, 1);
		UdxNode minArrayNode = udx_return_Dt.addChildNode("minArray", EKernelType.EKT_REAL, 6);
		UdxNode maxNode = udx_return_Dt.addChildNode("max", EKernelType.EKT_REAL, 1);
		UdxNode minArrayNode2 = udx_return_Dt.addChildNode("minArray2", EKernelType.EKT_REAL, 6);
		int count = 0;
		do{
			if((line=br.readLine())!=null){
				count +=1;				
				line=line.trim();
				switch(count){
				case 1:
					((UdxKernelStringValue)descriptionNode.getKernel()).setTypedValue(line);
					break;
				case 2:
					((UdxKernelRealValue)minNode.getKernel()).setTypedValue(Double.parseDouble(line));
					break;
				case 3:
					String[] dataArray=line.split("\\s+");
					if(dataArray.length!=6) {
						br.close();
						return null;
					}
					for(int i=0;i<dataArray.length;i++){
						((UdxKernelRealArray)minArrayNode.getKernel()).addTypedValue(
								Double.parseDouble(dataArray[i]));
					}					
					break;
				case 4:
					((UdxKernelRealValue)maxNode.getKernel()).setTypedValue(Double.parseDouble(line));
					break;
				case 5:
					String[] dataArray2=line.split("\\s+");
					if(dataArray2.length!=6) {
						br.close();
						return null;
					}
					for(int i=0;i<dataArray2.length;i++){
						((UdxKernelRealArray)minArrayNode2.getKernel()).addTypedValue(
								Double.parseDouble(dataArray2[i]));
					}				
					break;
				}				
			}
		}while(line!=null);
		
		
//////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
   }
   
public static boolean MCToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=MCToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_mc.xml";
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
   
public static String UdxToMC(UdxDataset dataset) throws Exception{
	   
		if(dataset!=null){		
		}else{
			String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\ch_mc.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		UdxNode descriptionNode = dataset.getChildNode(0);
		UdxNode minNode = dataset.getChildNode(1);
		UdxNode minArrayNode = dataset.getChildNode(2);
		UdxNode maxNode = dataset.getChildNode(3);
		UdxNode minArrayNode2 = dataset.getChildNode(4);
		
		DataStr.append(" "+((UdxKernelStringValue)descriptionNode.getKernel()).getTypedValue()+"\n");
		DataStr.append(" "+String.valueOf(((UdxKernelRealValue)minNode.getKernel()).getTypedValue())+"\n");
		for(int i=0;i<((UdxKernelRealArray)minArrayNode.getKernel()).getLength();i++){
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray)
					minArrayNode.getKernel()).getTypedValueByIndex(i)), 13));
		}
		DataStr.append("\n");
		DataStr.append("   "+String.valueOf(((UdxKernelRealValue)maxNode.getKernel()).getTypedValue())+"\n");
		for(int i=0;i<((UdxKernelRealArray)minArrayNode2.getKernel()).getLength();i++){
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray)
					minArrayNode2.getKernel()).getTypedValueByIndex(i)), 13));
		}
		DataStr.append("\n");
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
}
public static boolean UdxToMC(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToMC(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_mc1.dat";
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

