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
 * Dr.Lu's Fvcom3,step3,[output] [ch_el_obc.dat] conversion Tool
 * @author CK
 *
 */
public class EL_OBNodeUdxHelper {
	
public static UdxDataset EL_OBNodeToUdx(String fileFullPath,String dllPaths)throws Exception{
	   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\ch_el_obc.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		String line = null;
	   //////////////////////////////////////////////////////////////////////////////////
	
		UdxNode headNode =udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 2);
		UdxNode bodyNode =udx_return_Dt.addChildNode("body", EKernelType.EKT_LIST, 1);
		
		//head
		UdxNode descriptionNode = headNode.addChildNode("description", EKernelType.EKT_STRING, 1);
		if((line=br.readLine())!=null){
			line=line.trim();
			((UdxKernelStringValue)descriptionNode.getKernel()).setTypedValue(line);
		}
		UdxNode countNode = headNode.addChildNode("dataCount", EKernelType.EKT_INT, 1);
		if((line=br.readLine())!=null){
			line=line.trim();
			((UdxKernelIntValue)countNode.getKernel()).setTypedValue(Integer.parseInt(line));
		}
		//body
		do{
			UdxNode dataItem = null;
			//the first line
			if((line=br.readLine())!=null){
				line=line.trim();
				String[] theFirstLineArray=line.split("\\s+");			
				dataItem=bodyNode.addChildNode(String.valueOf(theFirstLineArray[0]),EKernelType.EKT_NODE, 3);
				((UdxKernelRealValue)dataItem.addChildNode("meanSeaLevel",
						EKernelType.EKT_REAL, 1).getKernel())
						.setTypedValue(Double.parseDouble(theFirstLineArray[1]));			
			}
			//the second line  //amplitude
			if((line=br.readLine())!=null){
				line=line.trim();
				String[] theSecondLineArray=line.split("\\s+");
				UdxNode amplitudeNode = dataItem.addChildNode("amplitude", EKernelType.EKT_REAL, 8);
				if(theSecondLineArray.length == 8){
					for(int i=0;i<8;i++){
						((UdxKernelRealArray)amplitudeNode.getKernel()).addTypedValue(
								Double.parseDouble(theSecondLineArray[i]));
					}					
				}else{
					System.out.println("the format of this input data is not correct.");
					br.close();
					return null;
				}				
			}
			// the third line // Initial phase
			if((line=br.readLine())!=null){
				line=line.trim();
				String[] theThirdLineArray=line.split("\\s+");
				UdxNode initialPhaseNode = dataItem.addChildNode("initialPhase", EKernelType.EKT_REAL, 8);
				if(theThirdLineArray.length == 8){
					for(int i=0;i<8;i++){
						((UdxKernelRealArray)initialPhaseNode.getKernel()).addTypedValue(
								Double.parseDouble(theThirdLineArray[i]));
					}					
				}else{
					System.out.println("the format of this input data is not correct.");
					br.close();
					return null;
				}				
			}			
		}while(line!=null);
		
		
//////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
   }
   
public static boolean EL_OBNodeToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
	   
	UdxDataset dataset=EL_OBNodeToUdx(inputPath,dllPaths);
	 if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_el_obc.xml";
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
   
public static String UdxToEL_OBNode(UdxDataset dataset) throws Exception{
	   
		if(dataset!=null){		
		}else{
			String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\ch_el_obc.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
		UdxNode headNode = dataset.getChildNode(0);
		UdxNode bodyNode = dataset.getChildNode(1);
		
		DataStr.append(" "+((UdxKernelStringValue)
						headNode.getChildNode(0).getKernel()).getTypedValue()).append("\n");
		DataStr.append("          "+String.valueOf(((UdxKernelIntValue)
				headNode.getChildNode(1).getKernel()).getTypedValue())).append("\n");
		
		for(int i=0;i<bodyNode.getChildNodeCount();i++){
			UdxNode dataItem = bodyNode.getChildNode(i);
			//the first line 
			DataStr.append(StringUtil.RightAlign(dataItem.getName(), 4));
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealValue)
					dataItem.getChildNode(0).getKernel()).getTypedValue()), 6)).append("\n");
			
			//the second line
			int secondArrayLen = ((UdxKernelRealArray)dataItem.getChildNode(1).getKernel()).getLength();
			for(int j=0;j<secondArrayLen;j++){
				DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray)
						dataItem.getChildNode(1).getKernel()).getTypedValueByIndex(j)), 9));
			}
			DataStr.append("\n");
			
			//the third line 
			int thirdArrayLen = ((UdxKernelRealArray)dataItem.getChildNode(2).getKernel()).getLength();
			for(int j=0;j<thirdArrayLen;j++){
				DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray)
						dataItem.getChildNode(2).getKernel()).getTypedValueByIndex(j)), 9));
			}
			DataStr.append("\n");
			
			
			
		}
		
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
}
public static boolean UdxToEL_OBNode(UdxDataset dataset,String SavePath) throws Exception{
	   String DataStr =UdxToEL_OBNode(dataset);
	   if(DataStr.equals("")||DataStr==null){
		   System.out.println("conversion failed.");
		   return false;
	   }
	   if(SavePath=="TestPath"){
		   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_el_obc1.dat";
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

