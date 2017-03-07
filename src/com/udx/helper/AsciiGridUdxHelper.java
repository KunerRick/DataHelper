package com.udx.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.ngis.udx.EKernelType;
import com.ngis.udx.UdxDataset;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelRealArray;
import com.ngis.udx.UdxKernelRealValue;
import com.ngis.udx.UdxNode;

public class AsciiGridUdxHelper {

	/**
	 * 将 AsciiGrid 转换为 UDX
	 * 
	 * @param AsciiGrid
	 *            对应的 路径
	 * @return
	 */
	public static UdxDataset asciiGrid2Udx(String fileFullPath,String dllPaths) throws Exception {
		
		int nCol = 0;
		int nRow = 0;
		double minX = 0;
		double minY = 0;
		double cellSize = 0;
		double nodata = -9999.0;

		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);

		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath)));

		
		String line = br.readLine();
		String[] tmpArray = line.split(" ");
		nCol = Integer.parseInt(tmpArray[tmpArray.length - 1]);
		
		line = br.readLine();
		tmpArray = line.split(" ");
		nRow = Integer.parseInt(tmpArray[tmpArray.length - 1]);
		
		line = br.readLine();
		tmpArray = line.split(" ");
		minX = Double.parseDouble(tmpArray[tmpArray.length - 1]);
		
		line = br.readLine();
		tmpArray = line.split(" ");
		minY = Double.parseDouble(tmpArray[tmpArray.length - 1]);
		
		line = br.readLine();
		tmpArray = line.split(" ");
		cellSize = Double.parseDouble(tmpArray[tmpArray.length - 1]);
		
		line = br.readLine();
		tmpArray = line.split(" ");
		nodata = Double.parseDouble(tmpArray[tmpArray.length - 1]);
		
		//写UDX head部分
		
		UdxNode headNode = udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 6);//6 个 child
		UdxNode colNode = headNode.addChildNode("ncols", EKernelType.EKT_INT,1);
		((UdxKernelIntValue)colNode.getKernel()).setTypedValue(nCol);
		
		UdxNode rowNode = headNode.addChildNode("nrows", EKernelType.EKT_INT,1);
		((UdxKernelIntValue)rowNode.getKernel()).setTypedValue(nRow);		
		
		UdxNode minXNode = headNode.addChildNode("xllcorner", EKernelType.EKT_REAL, 1);
		((UdxKernelRealValue)minXNode.getKernel()).setTypedValue(minX);	
		
		UdxNode minYNode = headNode.addChildNode("yllcorner", EKernelType.EKT_REAL, 1);
		((UdxKernelRealValue)minYNode.getKernel()).setTypedValue(minY);	
		
		UdxNode cellSizeNode = headNode.addChildNode("cellsize", EKernelType.EKT_REAL, 1);
		((UdxKernelRealValue)cellSizeNode.getKernel()).setTypedValue(cellSize);
		
		UdxNode nodataNode = headNode.addChildNode("NODATA_value", EKernelType.EKT_REAL, 1);
		((UdxKernelRealValue)nodataNode.getKernel()).setTypedValue(nodata);
		
		
		//写UDX body部分
		UdxNode bodyNode =udx_return_Dt.addChildNode("body", EKernelType.EKT_LIST, nRow);
		int index = 0;
		for ( line = br.readLine(); line != null; line = br.readLine()) {
			++index;
			UdxNode oneRowNode = bodyNode.addChildNode(index + "", EKernelType.EKT_REAL, nCol);
			tmpArray = line.split(" ");
			for (int i = 0; i < tmpArray.length; i++) {
				((UdxKernelRealArray)oneRowNode.getKernel()).addTypedValue(Double.parseDouble(tmpArray[i]));
			}
		}
		br.close();

		// udx_return_Dt.addChildNode("", pType, valueLen);

		return udx_return_Dt;
	}

	/**
	 * UDX 转为 AsciiGrid
	 * 
	 * @param dataset
	 *            UDX数据对应的UdxDataset对象
	 * @return AsciiGrid对应的字符串数据
	 */
	public static String udx2AsciiGrid(UdxDataset dataset) {

		StringBuilder sb = new StringBuilder();

		int nCol = 0;
		int nRow = 0;
		double minX = 0;
		double minY = 0;
		double cellSize = 0;
		double nodata = -9999.0;

		UdxNode headNode = dataset.getChildNode(0);// head

		UdxNode colNode = headNode.getChildNode(0);
		nCol = ((UdxKernelIntValue) colNode.getKernel()).getTypedValue();

		UdxNode rowNode = headNode.getChildNode(1);
		nRow = ((UdxKernelIntValue) rowNode.getKernel()).getTypedValue();

		UdxNode minXNode = headNode.getChildNode(2);
		minX = ((UdxKernelRealValue) minXNode.getKernel()).getTypedValue();

		UdxNode minYNode = headNode.getChildNode(3);
		minY = ((UdxKernelRealValue) minYNode.getKernel()).getTypedValue();

		UdxNode cellSizeNode = headNode.getChildNode(4);
		cellSize = ((UdxKernelRealValue) cellSizeNode.getKernel())
				.getTypedValue();

		UdxNode nodataNode = headNode.getChildNode(5);
		nodata = ((UdxKernelRealValue) nodataNode.getKernel()).getTypedValue();

		sb.append("ncols	" + nCol + "\n");
		sb.append("nrows	" + nRow + "\n");
		sb.append("xllcorner	" + minX + "\n");
		sb.append("yllcorner	" + minY + "\n");
		sb.append("cellsize	" + cellSize + "\n");
		sb.append("NODATA_value	" + nodata + "\n");

		UdxNode bodyNode = dataset.getChildNode(1);// body
		// 读取数据体
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				// lenth表示数据的长度，length=1则为UdxKernelRealValue，否则为UdxKernelRealArray
				// System.out.println("body length : " +
				// bodyNode.getChildNode(i).getKernel().getLength());
				double value = ((UdxKernelRealArray) (bodyNode.getChildNode(i)
						.getKernel())).getTypedValueByIndex(j);
				sb.append(value + " ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
