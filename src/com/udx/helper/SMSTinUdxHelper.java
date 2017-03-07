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
 * Dr.Lu's Fvcom,step1,[input] [cha_dep.geo] conversion Tool
 * @author CK
 *
 */
public class SMSTinUdxHelper {
	/**
	 * SMSTin to UDX
	 * @param fileFullPath  包含文件名及其后缀
	 * @param dllPaths  //nxdat.dll等文件的路径
	 * @return
	 * @throws Exception
	 */
	public static UdxDataset SMSTinToUdx(String fileFullPath,String dllPaths) throws Exception {
		String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\cha_dep.geo";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		
		int TriangleCount=0;
		int PointCount=0;
		//head		
		UdxNode headNode = udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 1);
		for(int i=0;i<5;i++){
			
			//((UdxKernelStringValue)unkownNode.getKernel()).setTypedValue("");
			if(i!=3){
				String line = br.readLine().trim();
				headNode.addChildNode(line, EKernelType.EKT_STRING,1);
			}			
			else{
				String line = br.readLine().trim();
				String[] strArray=line.split("\\s+");
				UdxNode unkownNode = headNode.addChildNode(strArray[0], EKernelType.EKT_INT,1);
				((UdxKernelIntValue)unkownNode.getKernel()).setTypedValue(Integer.parseInt(strArray[1]));
			}
		}
		
		UdxNode TriangleCountNode = headNode.addChildNode("TriangleCount", EKernelType.EKT_INT,1);
		
		UdxNode PointCountNode = headNode.addChildNode("PointCount", EKernelType.EKT_INT,1);
		
		
		//body
		UdxNode bodyNode = udx_return_Dt.addChildNode("body", EKernelType.EKT_LIST, 2);
		UdxNode TriangleListNode = bodyNode.addChildNode("TriangleList", EKernelType.EKT_LIST, 2);
		UdxNode PointListNode = bodyNode.addChildNode("PointList", EKernelType.EKT_LIST, 2);
		String line=null;
		do{
			
			
			if((line=br.readLine())==null) break;
			line=line.trim();
			String[] dataArray=line.split("\\s+");
			if(dataArray[0].equals("GE")){
				TriangleCount+=1;
				UdxNode TriangleNode=TriangleListNode.addChildNode(String.valueOf(TriangleCount),EKernelType.EKT_INT , 3);
				for(int i=1;i<=3;i++){
					((UdxKernelIntArray)TriangleNode.getKernel()).addTypedValue(Integer.parseInt(dataArray[i*2]));
				}
				
			}else if(dataArray[0].equals("GNN")){
				PointCount+=1;
				UdxNode PointNode=PointListNode.addChildNode(String.valueOf(PointCount),EKernelType.EKT_REAL , 3);
				for(int i=1;i<=3;i++){
					((UdxKernelRealArray)PointNode.getKernel()).addTypedValue(Double.parseDouble(dataArray[i+1]));
				}
			}
			
			
		}while(line!=null);		
		((UdxKernelIntValue)TriangleCountNode.getKernel()).setTypedValue(TriangleCount);		
		((UdxKernelIntValue)PointCountNode.getKernel()).setTypedValue(PointCount);	
		br.close();
		return udx_return_Dt;
	}
	
	public static boolean SMSTinToUdx(String inputPath,String dllPaths,String SavePath) throws Exception {
		   
		UdxDataset dataset=SMSTinToUdx(inputPath,dllPaths);
		 if(SavePath=="TestPath"){
			   SavePath="D:\\Workspace\\FvCom\\tmpData\\cha_dep.xml";
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
	
	/**
	 * 
	 * @param dataset 输入的udx数据集,返回转换之后的字符串
	 * @return
	 */
	public static String UdxToSMSTin(UdxDataset dataset)throws Exception{
	
		if(dataset!=null){		
		}else{
			String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName="D:\\Workspace\\FvCom\\tmpData\\cha_dep.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////UdxNode headNode=dataset.getChildNode(0);
		UdxNode headNode=dataset.getChildNode(0);
		
		for(int i=0;i<headNode.getChildNodeCount()-2;i++){
			UdxNode headChild=headNode.getChildNode(i);
			if(headChild.getName().equals("SI")){
				DataStr.append(headChild.getName()+"    ").append(String.valueOf(((UdxKernelIntValue) headChild.getKernel()).getTypedValue())).append("\n");
			}
			else{
				DataStr.append(headChild.getName()+"\n");
			}		
		}
	////UdxNode bodyNode=dataset.getChildNode(1);
		UdxNode bodyNode=dataset.getChildNode(1);
		UdxNode TriangleList = bodyNode.getChildNode(0);
		UdxNode PointList = bodyNode.getChildNode(1);		
		for(int i=0;i<TriangleList.getChildNodeCount();i++){
			UdxNode Triangle=TriangleList.getChildNode(i);
			
			DataStr.append("GE").append(StringUtil.RightAlign(Triangle.getName(), 6));
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelIntArray) Triangle.getKernel()).getTypedValueByIndex(0)),6));
			DataStr.append(StringUtil.RightAlign("0",6));
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelIntArray) Triangle.getKernel()).getTypedValueByIndex(1)),6));
			DataStr.append(StringUtil.RightAlign("0",6));
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelIntArray) Triangle.getKernel()).getTypedValueByIndex(2)),6));
			DataStr.append(StringUtil.RightAlign("0",6));
			DataStr.append(StringUtil.RightAlign("0",6)).append(StringUtil.RightAlign("0",6)).append(StringUtil.RightAlign("1",6))
			.append(StringUtil.RightAlign("0.0",7)).append("\n");
		}
		for(int i=0;i<PointList.getChildNodeCount();i++){
			UdxNode Point=PointList.getChildNode(i);
			DataStr.append("GNN").append(StringUtil.RightAlign(Point.getName(),6));
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray) Point.getKernel()).getTypedValueByIndex(0)),21));
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray) Point.getKernel()).getTypedValueByIndex(1)),21));
			DataStr.append(StringUtil.RightAlign(String.valueOf(((UdxKernelRealArray) Point.getKernel()).getTypedValueByIndex(2)),21));
			DataStr.append("\n");
		}
		return DataStr.toString();
	}
	public static boolean UdxToSMSTin(UdxDataset dataset,String SavePath)throws Exception{
		 String DataStr =UdxToSMSTin(dataset);
		   if(DataStr.equals("")||DataStr==null){
			   System.out.println("conversion failed.");
			   return false;
		   }
		   if(SavePath=="TestPath"){
			   SavePath="D:\\Workspace\\FvCom\\tmpData\\cha_dep1.geo";
		   }else if(SavePath.equals("")||SavePath==null){
			   System.out.println("the SavePath is not right.");
			   return false;
		   }
		   try {
				File dataFile=new File(SavePath);
				if(dataFile.exists()){
					dataFile.delete();
				}
				
				dataFile.getParentFile().mkdirs();
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
