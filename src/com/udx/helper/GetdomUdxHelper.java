package com.udx.helper;

import java.awt.image.Kernel;
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
 * Dr.Lu's Fvcom,step1,[input] [getdom.dat] conversion Tool
 * @author CK
 *
 */
public class GetdomUdxHelper {
	/**
	 * getdom to udx file
	 * @param fileFullPath
	 * @param dllPaths
	 * @return
	 * @throws Exception
	 */
	public static UdxDataset GetdomToUdx(String fileFullPath,String dllPaths) throws Exception{
		String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if(dllPaths!=null){}
		else{
			dllPaths=dllPath;
		}
		if(fileFullPath.equals("TestPath")){
			fileFullPath="D:\\Workspace\\FvCom\\testData\\getdom.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));
		UdxNode geoFileName=udx_return_Dt.addChildNode("geoFileName", EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue)geoFileName.getKernel()).setTypedValue(br.readLine().trim());
		
		UdxNode prefix=udx_return_Dt.addChildNode("prefix", EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue)prefix.getKernel()).setTypedValue(br.readLine().trim());
		
		UdxNode TriangleCount=udx_return_Dt.addChildNode("TriangleCount", EKernelType.EKT_INT, 1);
		((UdxKernelIntValue)TriangleCount.getKernel()).setTypedValue(Integer.parseInt(br.readLine().trim()));
		
		UdxNode PointCount=udx_return_Dt.addChildNode("PointCount", EKernelType.EKT_INT, 1);
		((UdxKernelIntValue)PointCount.getKernel()).setTypedValue(Integer.parseInt(br.readLine().trim()));
		
		UdxNode OpenBoundaryCount=udx_return_Dt.addChildNode("OpenBoundaryCount", EKernelType.EKT_INT, 1);
		((UdxKernelIntValue)OpenBoundaryCount.getKernel()).setTypedValue(Integer.parseInt(br.readLine().trim()));
		
		br.close();
		return udx_return_Dt;
	}
	/**
	 * for save
	 * @param fileFullPath
	 * @param dllPaths
	 * @param SavePath
	 * @return
	 * @throws Exception
	 */
	
	public static boolean GetdomToUdx(String fileFullPath,String dllPaths,String SavePath)throws Exception {
		UdxDataset dataset=GetdomToUdx(fileFullPath,dllPaths);
		 if(SavePath=="TestPath"){
			   SavePath="D:\\Workspace\\FvCom\\tmpData\\getdom.xml";
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
	 * udx file to getdom
	 * @param dataset Udx dataset
	 * @return
	 * @throws Exception
	 */
	public static String UdxToGetdom(UdxDataset dataset) throws Exception{
		
		if(dataset!=null){		
		}else{
			String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName="D:\\Workspace\\FvCom\\tmpData\\getdom.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset=udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		
	    UdxNode geoFileNameNode=dataset.getChildNode(0);
        DataStr.append(((UdxKernelStringValue) geoFileNameNode.getKernel()).getTypedValue()).append("\n");	
        UdxNode prefixNode=dataset.getChildNode(1);
        DataStr.append(((UdxKernelStringValue) prefixNode.getKernel()).getTypedValue()).append("\n");	
        
        UdxNode TriangleCountNode=dataset.getChildNode(2);
        DataStr.append(String.valueOf(((UdxKernelIntValue) TriangleCountNode.getKernel()).getTypedValue())).append("\n");
        UdxNode PointCountNode=dataset.getChildNode(3);
        DataStr.append(String.valueOf(((UdxKernelIntValue) PointCountNode.getKernel()).getTypedValue())).append("\n");
        UdxNode OpenBoundaryCountNode=dataset.getChildNode(4);
        DataStr.append(String.valueOf(((UdxKernelIntValue) OpenBoundaryCountNode.getKernel()).getTypedValue())).append("\n");
		
		return DataStr.toString();
	}
	/**
	 * for save
	 * @param dataset
	 * @param SavePath
	 * @return
	 * @throws Exception
	 */
	public static boolean UdxToGetdom(UdxDataset dataset,String SavePath) throws Exception{
		 String DataStr =UdxToGetdom(dataset);
		   if(DataStr.equals("")||DataStr==null){
			   System.out.println("conversion failed.");
			   return false;
		   }
		   if(SavePath=="TestPath"){
			   SavePath="D:\\Workspace\\FvCom\\tmpData\\getdom1.dat";
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
