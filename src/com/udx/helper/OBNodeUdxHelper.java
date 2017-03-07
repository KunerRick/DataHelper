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
 * Dr.Lu's Fvcom,step1,[output] [obc_xy.dat] conversion Tool
 * 
 * @author CK
 *
 */
public class OBNodeUdxHelper {
	/**
	 * 开边界节点文件obc_xy.dat to  udx
	 * 
	 * @param fileFullPath
	 * @param dllPaths
	 * @return
	 * @throws Exception
	 */
	public static UdxDataset OBNodeToUdx(String fileFullPath, String dllPaths)
			throws Exception {
		String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if (dllPaths != null) {
		} else {
			dllPaths = dllPath;
		}
		if (fileFullPath.equals("TestPath")) {
			fileFullPath = "D:\\Workspace\\FvCom\\testData\\obc_xy.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(
				fileFullPath)));
		// ////////////////////////////////////////////////////////////////////////////////
		UdxNode headNode = udx_return_Dt.addChildNode("head",
				EKernelType.EKT_NODE, 2);
		UdxNode bodyNode = udx_return_Dt.addChildNode("body",
				EKernelType.EKT_LIST, 1);

		UdxNode descriptionNode = headNode.addChildNode("description",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) descriptionNode.getKernel()).setTypedValue(br
				.readLine().trim());
		UdxNode nodeCountNode = headNode.addChildNode("nodeCount",
				EKernelType.EKT_INT, 1);
		((UdxKernelIntValue) nodeCountNode.getKernel()).setTypedValue(Integer
				.parseInt(br.readLine().trim()));

		String line = null;
		int nodeCount = 0;
		do {
			if ((line = br.readLine()) != null) {
				nodeCount += 1;
				line = line.trim();
				String[] strArray = line.split("\\s+");
				UdxNode dataNode = bodyNode.addChildNode(
						String.valueOf(nodeCount), EKernelType.EKT_REAL, 3);
				for (int i = 0; i < strArray.length; i++) {
					((UdxKernelRealArray) dataNode.getKernel())
							.addTypedValue(Double.parseDouble(strArray[i]));
				}
			}
		} while (line != null);

		// /////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
	}

	/**
	 * 
	 * 保存为文件
	 * 
	 * @param fileFullPath
	 * @param dllPaths
	 * @param SavePath
	 * @return
	 * @throws Exception
	 */
	public static boolean OBNodeToUdx(String inputPath, String dllPaths,
			String SavePath) throws Exception {

		UdxDataset dataset = OBNodeToUdx(inputPath, dllPaths);
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\obc_xy.xml";
		} else if (SavePath.equals("") || SavePath == null) {
			System.out.println("the SavePath is not right.");
			return false;
		}
		dataset.FormatToXmlFile(SavePath);
		return true;
	}

	/**
	 * udx to obc_xy.dat
	 * 
	 * @param dataset
	 * @return
	 * @throws Exception
	 */
	public static String UdxToOBNode(UdxDataset dataset) throws Exception {
		if (dataset != null) {
		} else {
			String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName = "D:\\Workspace\\FvCom\\tmpData\\obc_xy.xml";		
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset = udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		// //////////////////////////////////////////////////////////////////////////////////////
		UdxNode headNode = dataset.getChildNode(0);
		UdxNode bodyNode = dataset.getChildNode(1);

		UdxNode descriptionNode = headNode.getChildNode(0);
		DataStr.append(
				((UdxKernelStringValue) descriptionNode.getKernel())
						.getTypedValue()).append("\n");
		UdxNode nodeCountNode = headNode.getChildNode(1);
		DataStr.append("        ").append(
				String.valueOf(((UdxKernelIntValue) nodeCountNode.getKernel())
						.getTypedValue())).append("\n");

		for (int i = 0; i < bodyNode.getChildNodeCount(); i++) {
			UdxNode dataNode = bodyNode.getChildNode(i);
			for (int index = 0; index < 3; index++) {
				DataStr.append(StringUtil.RightAlign(String
						.valueOf(((UdxKernelRealArray) dataNode.getKernel())
								.getTypedValueByIndex(index)), 14));
			}
			DataStr.append("\n");
		}

		// //////////////////////////////////////////////////////////////////////////////////////

		return DataStr.toString();
	}

	public static boolean UdxToOBNode(UdxDataset dataset, String SavePath)
			throws Exception {
		String DataStr = UdxToOBNode(dataset);
		if (DataStr.equals("") || DataStr == null) {
			System.out.println("conversion failed.");
			return false;
		}
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\obc_xy1.dat";
		} else if (SavePath.equals("") || SavePath == null) {
			System.out.println("the SavePath is not right.");
			return false;
		}
		try {
			File dataFile = new File(SavePath);
			if (dataFile.exists()) {
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
