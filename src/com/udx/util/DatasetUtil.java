package com.udx.util;

import java.io.File;

import com.ngis.udx.UdxDataset;

public class DatasetUtil {
	/**
	 * 通过Dataset相关dll路径、从模型容器返回来的字符串来得到dataset
	 * @param dllsPath   Dataset相关dll路径
	 * @param infoStr    从模型容器返回来的信息字符串
	 * @return
	 * @throws Exception
	 */
	public static UdxDataset getUdxDataset(String dllsPath,String infoStr)throws Exception{
		if ("".equals(infoStr)) {
			System.out.println("handle.getReqGeoDataJ2  return null");
			return null;
		}
		if(infoStr.equals("TestStr")){
			String dat1 = "54a1ab06-d571-4f21-b9a5-e64731968d6c@@getSMSTin@@FILE@@D:\\Workspace\\FvCom\\tmpData\\SMSUDX.xml@@";
			infoStr = dat1;
		}
		if(dllsPath.equals("TestPath")){
			String dllPath="E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			dllsPath=dllPath;
		}
		UdxDataset dataset=new UdxDataset(dllsPath);		
		
		// 分割字符串
		// sid@@eventName@@type@@value@@
		String[] dat1s = infoStr.split("@@");
		String geoDataType = dat1s[2];
		String geoDataValue = dat1s[3];
		try {
			/**
			 * 1.接受模型容器传递过来的UDX数据,这里有两种情况：从数据流加载udx，从文件加载udx
			 */	
			if ("FILE".equals(geoDataType)) {
				dataset.LoadFromXmlFile(geoDataValue);
				File file = new File(geoDataValue);
				//System.out.println(file.getName());
			} else if ("STREAM".equals(geoDataType)) {
				dataset.LoadFromXmlStream(geoDataValue);
			} else {
				System.out
						.println("geoDataType 的值不符合要求：" + geoDataType);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("模型容器获取xml失败...");
		}
		
		
		return dataset;
	}
}
