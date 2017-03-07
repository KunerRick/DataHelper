package com.udx.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import com.ngis.udx.EKernelType;
import com.ngis.udx.UdxDataset;
import com.ngis.udx.UdxKernelIntArray;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;

/**
 * BSQ是按波段保存，也就是一个波段保存完后紧接着保存第二个波段；
 * BIL是按行保存，也就是保存完第一个波段的第一行后，紧接着保存第二个波段的第一行数据，以此类推；
 * BIP是按照像元保存的，即先保存第一个波段的第一个像元，紧接着保存第二个波段的第一个像元，以此类推。
 */

public class RsUdxHelper {
	/**
	 * BSQ 转为 UDX
	 */
	public static UdxDataset rsBSQ2UDX(String filename, String udxDllsPath)
			throws Exception {

		String description = "";
		int samples = 0;
		int lines = 0;
		int bands = 0;
		int headerOffset = 0;
		String fileType = "";
		int dataType = 0;
		String interleave = "";
		String sensorType = "";
		int byteOrder = 0;
		String waveLengthUnits = "";
		String pixelSize = "";
		String defaultStretch = "";

		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] spt;
			if (line.contains("description")) {
				spt = line.split("=");
				description = spt[1].trim();
			} else if (line.contains("samples")) {
				spt = line.split("=");
				samples = Integer.parseInt(spt[1].trim());
			} else if (line.contains("lines")) {
				spt = line.split("=");
				lines = (Integer.parseInt(spt[1].trim()));
			} else if (line.contains("bands")) {
				spt = line.split("=");
				bands = (Integer.parseInt(spt[1].trim()));
			} else if (line.contains("header offset")) {
				spt = line.split("=");
				headerOffset = (Integer.parseInt(spt[1].trim()));
			} else if (line.contains("file type")) {
				spt = line.split("=");
				fileType = spt[1].trim();
			} else if (line.contains("data type")) {
				spt = line.split("=");
				dataType = (Integer.parseInt(spt[1].trim()));
			} else if (line.contains("interleave")) {
				spt = line.split("=");
				interleave = spt[1].trim();
			} else if (line.contains("sensor type")) {
				spt = line.split("=");
				sensorType = spt[1].trim();
			} else if (line.contains("byte order")) {
				spt = line.split("=");
				byteOrder = (Integer.parseInt(spt[1].trim()));
			} else if (line.contains("wavelength units")) {
				spt = line.split("=");
				waveLengthUnits = spt[1].trim();
			} else if (line.contains("pixel size")) {
				spt = line.split("=");
				pixelSize = spt[1].trim();
			} else if (line.contains("default stretch")) {
				spt = line.split("=");
				defaultStretch = spt[1].trim();
			}
		}
		br.close();

		UdxDataset udx_return_Dt = new UdxDataset(udxDllsPath);

		UdxNode headNode = udx_return_Dt.addChildNode("head",
				EKernelType.EKT_NODE, 13);
		// description
		UdxNode desNode = headNode.addChildNode("description",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) desNode.getKernel()).setTypedValue(description);
		// samples
		UdxNode samplesNode = headNode.addChildNode("samples",
				EKernelType.EKT_INT, 1);
		((UdxKernelIntValue) samplesNode.getKernel()).setTypedValue(samples);
		// lines
		UdxNode linesNode = headNode.addChildNode("lines", EKernelType.EKT_INT,
				1);
		((UdxKernelIntValue) linesNode.getKernel()).setTypedValue(lines);
		// bands
		UdxNode bandsNode = headNode.addChildNode("bands", EKernelType.EKT_INT,
				1);
		((UdxKernelIntValue) bandsNode.getKernel()).setTypedValue(bands);
		// header offset
		UdxNode headerOffsetNode = headNode.addChildNode("header offset",
				EKernelType.EKT_INT, 1);
		((UdxKernelIntValue) headerOffsetNode.getKernel())
				.setTypedValue(headerOffset);
		// file type
		UdxNode fileTypeNode = headNode.addChildNode("file type",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) fileTypeNode.getKernel())
				.setTypedValue(fileType);
		// data type
		UdxNode dataTypeNode = headNode.addChildNode("data type",
				EKernelType.EKT_INT, 1);
		((UdxKernelIntValue) dataTypeNode.getKernel()).setTypedValue(dataType);
		// interleave
		UdxNode interleaveNode = headNode.addChildNode("interleave",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) interleaveNode.getKernel())
				.setTypedValue(interleave);
		// sensor type
		UdxNode sensorTypeNode = headNode.addChildNode("sensor type",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) sensorTypeNode.getKernel())
				.setTypedValue(sensorType);
		// byte order
		UdxNode byteOrderNode = headNode.addChildNode("byte order",
				EKernelType.EKT_INT, 1);
		((UdxKernelIntValue) byteOrderNode.getKernel())
				.setTypedValue(byteOrder);
		// wavelength units
		UdxNode waveLengthUnitsNode = headNode.addChildNode("wavelength units",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) waveLengthUnitsNode.getKernel())
				.setTypedValue(waveLengthUnits);
		// pixel size
		UdxNode pixelSizeNode = headNode.addChildNode("pixel size",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) pixelSizeNode.getKernel())
				.setTypedValue(pixelSize);
		// default stretch
		UdxNode defaultStretchNode = headNode.addChildNode("default stretch",
				EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue) defaultStretchNode.getKernel())
				.setTypedValue(defaultStretch);

		// 写body

		UdxNode bodyNode = udx_return_Dt.addChildNode("body",
				EKernelType.EKT_LIST, bands);
		if (interleave.equals("bsq")) {

			// 读取数据文件
			File reallFile = new File(filename.substring(0,
					filename.length() - 3));
			FileInputStream fis = new FileInputStream(reallFile);

			// 每个band单独创建一个XDO节点
			for (int i = 0; i < bands; i++) {
				// band node
				UdxNode bandNode = bodyNode.addChildNode("band" + (i + 1),
						EKernelType.EKT_LIST, lines);
				for (int j = 0; j < lines; j++) {
					// row node
					UdxNode rowNode = bandNode.addChildNode("line" + (j + 1),
							EKernelType.EKT_INT, samples);
					for (int k = 0; k < samples; k++) {
						// set row value
						((UdxKernelIntArray) rowNode.getKernel())
								.addTypedValue(fis.read());
						// imageData[i][j][k] = fis.read();

					}
				}
			}

			fis.close();
		}
		return udx_return_Dt;
	}

	/**
	 * UDX 转 BSQ
	 */
	public static boolean udx2RsBSQ(UdxDataset dataset,String savePath,String udxDllsPath) throws Exception{
		
		boolean result = true;
		
		StringBuilder sb = new StringBuilder();
		
		String description = "";
		int samples = 0;
		int lines = 0;
		int bands = 0;
		int headerOffset = 0;
		String fileType = "";
		int dataType = 0;
		String interleave = "";
		String sensorType = "";
		int byteOrder = 0;
		String waveLengthUnits = "";
		String pixelSize = "";
		String defaultStretch = "";
		
		sb.append("ENVI"+"\n");
		
		UdxNode headNode = dataset.getChildNode(0);//head
		
		//description
		UdxNode desNode = headNode.getChildNode(0);
		description = ((UdxKernelStringValue)desNode.getKernel()).getTypedValue();
		//samples
		UdxNode samplesNode = headNode.getChildNode(1);
		samples = ((UdxKernelIntValue)samplesNode.getKernel()).getTypedValue();
		//lines
		UdxNode linesNode = headNode.getChildNode(2);
		lines = ((UdxKernelIntValue)linesNode.getKernel()).getTypedValue();
		//bands
		UdxNode bandsNode = headNode.getChildNode(3);
		bands = ((UdxKernelIntValue)bandsNode.getKernel()).getTypedValue();
		//head offset
		UdxNode headerOffsetNode = headNode.getChildNode(4);
		headerOffset = ((UdxKernelIntValue)headerOffsetNode.getKernel()).getTypedValue();
		//file type
		UdxNode fileTypeNode = headNode.getChildNode(5);
		fileType = ((UdxKernelStringValue)fileTypeNode.getKernel()).getTypedValue();
		//data type
		UdxNode dataTypeNode = headNode.getChildNode(6);
		dataType = ((UdxKernelIntValue)dataTypeNode.getKernel()).getTypedValue();
		//interleave
		UdxNode interleaveNode = headNode.getChildNode(7);
		interleave = ((UdxKernelStringValue)interleaveNode.getKernel()).getTypedValue();
		//sensorType
		UdxNode sensorTypeNode = headNode.getChildNode(8);
		sensorType = ((UdxKernelStringValue)sensorTypeNode.getKernel()).getTypedValue();
		//byteOrder
		UdxNode byteOrderNode = headNode.getChildNode(9);
		byteOrder = ((UdxKernelIntValue)byteOrderNode.getKernel()).getTypedValue();
		//waveLengthUnits
		UdxNode waveLengthUnitsNode = headNode.getChildNode(10);
		waveLengthUnits = ((UdxKernelStringValue)waveLengthUnitsNode.getKernel()).getTypedValue();
		//pixelSize
		UdxNode pixelSizeNode = headNode.getChildNode(11);
		pixelSize = ((UdxKernelStringValue)pixelSizeNode.getKernel()).getTypedValue();
		//defaultStretch
		UdxNode defaultStretchNode = headNode.getChildNode(12);
		defaultStretch = ((UdxKernelStringValue)defaultStretchNode.getKernel()).getTypedValue();
		
		sb.append("description = "+description+"\n");
		sb.append("samples = "+samples+"\n");
		sb.append("lines   = "+lines+"\n");
		sb.append("bands   = "+bands+"\n");
		sb.append("header offset = "+headerOffset+"\n");
		sb.append("file type = "+fileType+"\n");
		sb.append("data type = "+dataType+"\n");
		sb.append("interleave = "+interleave+"\n");
		sb.append("sensor type = "+sensorType+"\n");
		sb.append("byte order = "+byteOrder+"\n");
		sb.append("wavelength units = "+waveLengthUnits+"\n");
		sb.append("pixel size = "+pixelSize+"\n");
		sb.append("default stretch = "+defaultStretch+"\n");
		
		File headerFile = new File(savePath);
		FileOutputStream fos = new FileOutputStream(headerFile);
		fos.write(sb.toString().getBytes());
		fos.flush();
		fos.close();
		
		System.out.println("rs bsq header file save success.");
		
		
		File dataFile = new File(savePath.substring(0, savePath.length()-3));
		FileOutputStream fosdata = new FileOutputStream(dataFile);
		
		UdxNode bodyNode = dataset.getChildNode(1);// body
		
		for(int i = 0 ; i < bands ; i++){
			
			UdxNode bandNode = bodyNode.getChildNode(i);
			
			for(int j = 0 ; j < lines ; j++){
				
				UdxNode rowNode = bandNode.getChildNode(j);
				
				for(int k = 0 ; k < samples ; k++){
					
					fosdata.write((byte)(((UdxKernelIntArray)rowNode.getKernel()).getTypedValueByIndex(k)));
					
				}
			}
		}
		
		fosdata.flush();
		fosdata.close();
		
		System.out.println("rs bsq data file save success.");
		
		return result;
	}

}
