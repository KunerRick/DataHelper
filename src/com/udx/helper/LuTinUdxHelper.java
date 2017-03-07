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
import com.ngis.udx.UdxKernelIntArray;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelRealArray;
import com.ngis.udx.UdxNode;
import com.udx.util.StringUtil;
/**
 * Dr.Lu's Fvcom,step1,[output] [ch_grd.dat] conversion Tool
 * @author CK
 *
 */
public class LuTinUdxHelper {
	public static UdxDataset LuTinToUdx(String fileFullPath,String dllPaths)throws Exception{
		   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			if(dllPaths!=null){}
			else{
				dllPaths=dllPath;
			}
			if(fileFullPath.equals("TestPath")){
				fileFullPath="D:\\Workspace\\FvCom\\testData\\run\\ch_grd.dat";
			}
			UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		   //////////////////////////////////////////////////////////////////////////////////
			String line=null;
			int TriangleCount = 0;
			int PointCount = 0;
			
			UdxNode headNode = udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 2);
			UdxNode bodyNode = udx_return_Dt.addChildNode("body", EKernelType.EKT_NODE, 1);
			
			UdxNode TriangleCountNode=headNode.addChildNode("TriangleCount", EKernelType.EKT_INT, 1);
			UdxNode PointCountNode=headNode.addChildNode("PointCount", EKernelType.EKT_INT, 1);
			UdxNode TriangleListNode=bodyNode.addChildNode("TriangleList", EKernelType.EKT_LIST, 1);
			UdxNode PointListNode=bodyNode.addChildNode("PointList", EKernelType.EKT_LIST, 1);
			
			
			
			
			do{
				if((line=br.readLine())!=null){
					line=line.trim();
					String[] strArray=line.split("\\s+");
					
					if(strArray.length ==5&&!strArray[1].contains(".")){
						//triangle
						TriangleCount+=1;
						UdxNode dataNode=TriangleListNode.addChildNode(String.valueOf(TriangleCount),EKernelType.EKT_INT,3);
						for(int i=0;i<3;i++){
							((UdxKernelIntArray)dataNode.getKernel()).addTypedValue(Integer.parseInt(strArray[i+1]));
						}
					}else if(strArray.length ==4&&strArray[1].contains(".")){
						//point
						PointCount+=1;
						UdxNode dataNode=PointListNode.addChildNode(String.valueOf(PointCount),EKernelType.EKT_REAL,3);
						for(int i=0;i<3;i++){
							((UdxKernelRealArray)dataNode.getKernel()).addTypedValue(Double.parseDouble(strArray[i+1]));
						}
						
					}else{
						System.out.println("error");
					}
				}
			}while(line!=null);
			((UdxKernelIntValue)TriangleCountNode.getKernel()).setTypedValue(TriangleCount);
			((UdxKernelIntValue)PointCountNode.getKernel()).setTypedValue(PointCount);
	       //////////////////////////////////////////////////////////////////////////////////
			br.close();
			return udx_return_Dt;
	   }
	   
	public static boolean LuTinToUdx(String inputPath,String dllPaths,String SavePath)throws Exception{
		   
		UdxDataset dataset=LuTinToUdx(inputPath,dllPaths);
		 if(SavePath=="TestPath"){
			   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_grd.xml";
		   }else if(SavePath.equals("")||SavePath==null){
			   System.out.println("the SavePath is not right.");
			   return false;
		   }
		 if(dataset!=null){
		   dataset.FormatToXmlFile(SavePath);
		   return true;
		 }else{
		    System.out.println("dataset is null.");
		   return false;
		 }
	   }
	   
	public static String UdxToLuTin(UdxDataset dataset) throws Exception{
		  
			if(dataset!=null){		
			}else{
				 String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
					UdxDataset udx_return_Dt = new UdxDataset(dllPath);
					String fileName="D:\\Workspace\\FvCom\\tmpData\\run\\ch_grd.xml";
					udx_return_Dt.LoadFromXmlFile(fileName);
				dataset=udx_return_Dt;
			}
			StringBuilder DataStr = new StringBuilder();
			////////////////////////////////////////////////////////////////////////////////////////
			UdxNode bodyNode=dataset.getChildNode(1);
			UdxNode TriangleListNode=bodyNode.getChildNode(0);
			UdxNode PointListNode=bodyNode.getChildNode(1);
			
			for(int i=0;i<TriangleListNode.getChildNodeCount();i++){
				UdxNode dataNode = TriangleListNode.getChildNode(i);
				DataStr.append(StringUtil.RightAlign(dataNode.getName(),8));
				for(int index=0;index<3;index++){
					DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelIntArray)dataNode.getKernel())
							.getTypedValueByIndex(index)), 8));
				}
				DataStr.append(StringUtil.RightAlign("1", 8)).append("\n");			
			}
			for(int i=0;i<PointListNode.getChildNodeCount();i++){
				UdxNode dataNode = PointListNode.getChildNode(i);
				DataStr.append(StringUtil.RightAlign(dataNode.getName(),8));
				for(int index=0;index<3;index++){
					DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray)dataNode.getKernel())
							.getTypedValueByIndex(index)), 13));
				}
				DataStr.append("\n");			
			}
			
			
			
				
			////////////////////////////////////////////////////////////////////////////////////////
			
			return DataStr.toString();
	}
	public static boolean UdxToLuTin(UdxDataset dataset,String SavePath) throws Exception{
		   String DataStr =UdxToLuTin(dataset);
		   if(DataStr.equals("")||DataStr==null){
			   System.out.println("conversion failed.");
			   return false;
		   }
		   if(SavePath=="TestPath"){
			   SavePath="D:\\Workspace\\FvCom\\tmpData\\run\\ch_grd1.dat";
		   }else if(SavePath.equals("")||SavePath==null){
			   System.out.println("the SavePath is not right.");
			   return false;
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
