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
 * Dr.Lu's Fvcom,step2,[input] [AMPHA_obc.dat] conversion Tool
 * 
 * @author CK
 *
 */
public class AMPHAUdxHelper {

	public static UdxDataset AMPHAToUdx(String fileFullPath, String dllPaths)
			throws Exception {
		String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if (dllPaths != null) {
		} else {
			dllPaths = dllPath;
		}
		if (fileFullPath.equals("TestPath")) {
			fileFullPath = "D:\\Workspace\\FvCom\\testData\\AMPHA_obc.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(
				fileFullPath)));
		// ////////////////////////////////////////////////////////////////////////////////
		UdxNode headNode = udx_return_Dt.addChildNode("head",
				EKernelType.EKT_NODE, 1);
		UdxNode bodyNode = udx_return_Dt.addChildNode("body",
				EKernelType.EKT_LIST, 1);

		UdxNode OpenBoundaryCount = headNode.addChildNode("OpenBoundaryCount",
				EKernelType.EKT_INT, 1);
		String line = null;
		int count = 0;
		do {
			if ((line = br.readLine()) != null) {
				count += 1;
				line = line.trim();
				String[] dataStrArray = line.split("\\s+");
				UdxNode dataNode = bodyNode.addChildNode(String.valueOf(count),
						EKernelType.EKT_NODE, 2);

				UdxNode amplitudeNode = dataNode.addChildNode("amplitude",
						EKernelType.EKT_REAL, 8);
				UdxNode initialPhaseNode = dataNode.addChildNode(
						"initialPhase", EKernelType.EKT_REAL, 8);

				for (int i = 1; i < dataStrArray.length; i++) {
					if (i <= 8) {
						((UdxKernelRealArray) amplitudeNode.getKernel())
								.addTypedValue(Double
										.parseDouble(dataStrArray[i]));
					} else {
						((UdxKernelRealArray) initialPhaseNode.getKernel())
								.addTypedValue(Double
										.parseDouble(dataStrArray[i]));
					}
				}
			}
		} while (line != null);
		((UdxKernelIntValue) OpenBoundaryCount.getKernel())
				.setTypedValue(count);

		// ////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
	}

	public static boolean AMPHAToUdx(String inputPath, String dllPaths,
			String SavePath) throws Exception {

		UdxDataset dataset = AMPHAToUdx(inputPath, dllPaths);
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\AMPHA_obc.xml";
		} else if (SavePath == null || SavePath.equals("")) {
			System.out.println("the SavePath is not right.");
			return false;
		}
		if (dataset != null) {
			dataset.FormatToXmlFile(SavePath);
			return true;
		} else {
			System.out.println("dataset is null.");
			return false;
		}
	}

	public static String UdxToAMPHA(UdxDataset dataset) throws Exception {

		if (dataset != null) {
		} else {
			String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName = "D:\\Workspace\\FvCom\\tmpData\\AMPHA_obc.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset = udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		// //////////////////////////////////////////////////////////////////////////////////////
		UdxNode bodyNode = dataset.getChildNode(1);
		for (int i = 0; i < bodyNode.getChildNodeCount(); i++) {
			UdxNode dataNode = bodyNode.getChildNode(i);
			DataStr.append(StringUtil.RightAlign(dataNode.getName(), 6));
			for (int index = 0; index < dataNode.getChildNodeCount(); index++) {
				UdxNode childNode = dataNode.getChildNode(index);
				int ArrayLen = ((UdxKernelRealArray) childNode.getKernel())
						.getLength();
				for (int j = 0; j < ArrayLen; j++) {
					DataStr.append(StringUtil.RightAlign(String
							.valueOf(((UdxKernelRealArray) childNode
									.getKernel()).getTypedValueByIndex(j)), 10));
				}
			}
			DataStr.append("\n");

		}

		// //////////////////////////////////////////////////////////////////////////////////////

		return DataStr.toString();
	}

	public static boolean UdxToAMPHA(UdxDataset dataset, String SavePath)
			throws Exception {
		String DataStr = UdxToAMPHA(dataset);
		if (DataStr.equals("") || DataStr == null) {
			System.out.println("conversion failed.");
			return false;
		}
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\AMPHA_obc1.dat";
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
