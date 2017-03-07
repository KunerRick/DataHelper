package com.Binary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ngis.udx.EKernelType;
import com.ngis.udx.UdxDataset;
import com.ngis.udx.UdxKernelRealArray;
import com.ngis.udx.UdxNode;
import com.udx.util.UdxHandlUtil;

public class BinaryTest {
	/**
	 * 生成测试所用schema
	 * @return 
	 */
	public static void createSchema(String outPutData,String dllPaths){
			   String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\DataHelper\\lib\\Library";
				if(dllPaths!=null){}
				else{
					dllPaths=dllPath;
				}
				if(outPutData.equals("TestPath")){
					outPutData="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\DataHelper\\tempData\\UV.xml";
				}
				UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
				for(int i=1;i<11;i++){
					UdxNode Dimension1=udx_return_Dt.addChildNode(String.valueOf(i), EKernelType.EKT_LIST, 0);
					for(int j=1;j<11;j++){
						UdxNode Dimension2 = Dimension1.addChildNode(String.valueOf(j), EKernelType.EKT_REAL, 2);
						((UdxKernelRealArray) Dimension2.getKernel()).addTypedValue(3.3242342);
						((UdxKernelRealArray) Dimension2.getKernel()).addTypedValue(123.23131);
					}
				}			
				udx_return_Dt.FormatToXmlFile(outPutData);				
	}
}
