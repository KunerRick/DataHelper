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
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;
import com.udx.util.UdxHandlUtil;
import com.udx.util.StringUtil.AlignType;
import com.udx.util.UdxHandlUtil.UdxDataType;

/**
 * 计算区域边界文件 Computational region boundary Dr.Lu's Fvcom,step4,[output]
 * [boundary.BLN] conversion Tool
 * 
 * @author CK
 *
 */
public class CRBUdxHelper {

	public static UdxDataset CRBToUdx(String fileFullPath, String dllPaths)
			throws Exception {
		String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if (dllPaths != null) {
		} else {
			dllPaths = dllPath;
		}
		if (fileFullPath.equals("TestPath")) {
			fileFullPath = "D:\\Workspace\\FvCom\\testData\\output\\boundary.BLN";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(
				fileFullPath)));
		String line = null;
		// ////////////////////////////////////////////////////////////////////////////////
		UdxNode headNode = udx_return_Dt.addChildNode("head",
				EKernelType.EKT_NODE, 1);
		UdxNode bodyNode = udx_return_Dt.addChildNode("body",
				EKernelType.EKT_LIST, 1);
		UdxNode boundaryCountNode = headNode.addChildNode("boundaryCount",
				EKernelType.EKT_INT, 1);
		int count = 0;
		int pointsOfOneBound = 0;
		int pointOfOneBoundIndex = 0;
		UdxNode childNode = null;
		do {
			if ((line = br.readLine()) != null) {
				line = line.trim();
				String[] dataStr = line.split("\\s+");
				int ArrayLen = dataStr.length;
				if (ArrayLen == 1) {
					count += 1;
					pointOfOneBoundIndex = 0;
					pointsOfOneBound = Integer.parseInt(dataStr[0]);
					childNode = bodyNode.addChildNode(String.valueOf(count),
							EKernelType.EKT_NODE, 2);
					UdxHandlUtil.createNode(childNode,
							UdxDataType.UdxKernelIntValue, "pointsCount",
							String.valueOf(pointsOfOneBound));
					continue;
				} else if (ArrayLen == 2) {
					pointOfOneBoundIndex += 1;
					// 若数目不符
					if (pointOfOneBoundIndex > pointsOfOneBound) {
						br.close();
						System.out
								.println("the format of data is not correct.");
						return null;
					}
					UdxHandlUtil.createNode(childNode,
							UdxDataType.UdxKernelRealArray,
							String.valueOf(pointOfOneBoundIndex), line);
				}
				((UdxKernelIntValue) boundaryCountNode.getKernel())
						.setTypedValue(count);

			}
		} while (line != null);

		// ////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
	}

	public static boolean CRBToUdx(String inputPath, String dllPaths,
			String SavePath) throws Exception {

		UdxDataset dataset = CRBToUdx(inputPath, dllPaths);
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\output\\boundary.xml";
		} else if (SavePath.equals("") || SavePath == null) {
			System.out.println("the SavePath is not right.");
			return false;
		}
		File outputFile = new File(SavePath);
		if (!outputFile.exists()) {
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
		}
		if (dataset != null) {
			dataset.FormatToXmlFile(SavePath);
			return true;
		} else {
			System.out.println("dataset is null.");
			return false;
		}
	}

	public static String UdxToCRB(UdxDataset dataset) throws Exception {

		if (dataset != null) {
		} else {
			String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName = "D:\\Workspace\\FvCom\\tmpData\\output\\boundary.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset = udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		// //////////////////////////////////////////////////////////////////////////////////////
		UdxNode bodyNode = dataset.getChildNode(1);
		for (int i = 0; i < bodyNode.getChildNodeCount(); i++) {
			UdxNode childNode = bodyNode.getChildNode(i);
			int pointsOfOneBound = Integer.parseInt(UdxHandlUtil
					.getStrFromUdxNode(childNode.getChildNode(0), 1,
							AlignType.None));
			DataStr.append("           " + String.valueOf(pointsOfOneBound)
					+ "\n");

			for (int j = 1; j < childNode.getChildNodeCount(); j++) {
				DataStr.append("   ");
				String dataStr = UdxHandlUtil.getStrFromUdxNode(
						childNode.getChildNode(j), 16, AlignType.Left);
				DataStr.append(dataStr);
				DataStr.append("\n");
			}
		}
		// //////////////////////////////////////////////////////////////////////////////////////

		return DataStr.toString();
	}

	public static boolean UdxToCRB(UdxDataset dataset, String SavePath)
			throws Exception {
		String DataStr = UdxToCRB(dataset);
		if (DataStr.equals("") || DataStr == null) {
			System.out.println("conversion failed.");
			return false;
		}
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\output\\boundary1.BLN";
		} else if (SavePath.equals("") || SavePath == null) {
			System.out.println("the SavePath is not right.");
			return false;
		}
		File outputFile = new File(SavePath);
		if (!outputFile.exists()) {
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
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
