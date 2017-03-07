package com.udx.util;

import com.ngis.udx.EKernelType;
import com.ngis.udx.UdxKernelIntArray;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelRealArray;
import com.ngis.udx.UdxKernelRealValue;
import com.ngis.udx.UdxKernelStringArray;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;
import com.udx.util.StringUtil.AlignType;

/**
 * 一些写入、读取udx时的函数
 * 
 * @author CK
 *
 */
public class UdxHandlUtil {
	public static enum UdxDataType {
		UdxKernelIntArray, UdxKernelIntValue, UdxKernelRealArray, UdxKernelRealValue, UdxKernelStringArray, UdxKernelStringValue
	}

	/**
	 * 创建子节点
	 * 
	 * @param parentNode
	 *            //需要创建子节点的父节点
	 * @param udxDataType
	 *            //子节点的数据类型（只包含6种，待完善）
	 * @param NodeName
	 *            //子节点的数据名称
	 * @param inputDataStr
	 *            //子节点的字符串，若是数组以若干空格符隔开
	 * @throws Exception
	 */
	public static void createNode(UdxNode parentNode, UdxDataType udxDataType,
			String NodeName, String inputDataStr) throws Exception {
		switch (udxDataType) {
		case UdxKernelIntArray:
			String[] dataStrArray = inputDataStr.split("\\s+");
			if (dataStrArray.length < 2) {
				System.out.println("inputDataStr can not be splited.");
				return;
			}
			UdxNode intArrayChildNode = parentNode.addChildNode(NodeName,
					EKernelType.EKT_INT, dataStrArray.length);
			for (int i = 0; i < dataStrArray.length; i++) {
				((UdxKernelIntArray) intArrayChildNode.getKernel())
						.addTypedValue(Integer.parseInt(dataStrArray[i]));
			}
			break;
		case UdxKernelIntValue:
			UdxNode intChildNode = parentNode.addChildNode(NodeName,
					EKernelType.EKT_INT, 1);
			((UdxKernelIntValue) intChildNode.getKernel())
					.setTypedValue(Integer.parseInt(inputDataStr));
			break;
		case UdxKernelRealArray:
			String[] realStrArray = inputDataStr.split("\\s+");
			if (realStrArray.length < 2) {
				System.out.println("inputDataStr can not be splited.");
				return;
			}
			UdxNode realArrayChildNode = parentNode.addChildNode(NodeName,
					EKernelType.EKT_REAL, realStrArray.length);
			for (int i = 0; i < realStrArray.length; i++) {
				((UdxKernelRealArray) realArrayChildNode.getKernel())
						.addTypedValue(Double.parseDouble(realStrArray[i]));
			}
			break;
		case UdxKernelRealValue:
			UdxNode realChildNode = parentNode.addChildNode(NodeName,
					EKernelType.EKT_REAL, 1);
			((UdxKernelRealValue) realChildNode.getKernel())
					.setTypedValue(Double.parseDouble(inputDataStr));
			break;
		case UdxKernelStringArray:
			String[] StrArray = inputDataStr.split("\\s+");
			if (StrArray.length < 2) {
				System.out.println("inputDataStr can not be splited.");
				return;
			}
			UdxNode strArrayChildNode = parentNode.addChildNode(NodeName,
					EKernelType.EKT_STRING, StrArray.length);
			for (int i = 0; i < StrArray.length; i++) {
				((UdxKernelStringArray) strArrayChildNode.getKernel())
						.addTypedValue((StrArray[i]));
			}
			break;
		case UdxKernelStringValue:
			UdxNode stringChildNode = parentNode.addChildNode(NodeName,
					EKernelType.EKT_STRING, 1);
			((UdxKernelStringValue) stringChildNode.getKernel())
					.setTypedValue(inputDataStr);
			break;
		default:
			break;

		}

	}

	/**
	 * 
	 * @param udxNode
	 *            //需要获取值的udx节点
	 * @param Len
	 *            //若RightAlign为true,表示右对齐后所占总长度，否则表示空格长度
	 * @param RightAlign
	 *            //是否右对齐
	 * @return
	 * @throws Exception
	 */
	public static String getStrFromUdxNode(UdxNode udxNode, int Len,
			AlignType alignType) throws Exception {
		StringBuilder dataStr = new StringBuilder();
		switch (udxNode.getKernel().getType()) {
		case EKT_INT:
			if (udxNode.getKernel().getLength() == 1) {
				dataStr.append(String.valueOf(((UdxKernelIntValue) udxNode
						.getKernel()).getTypedValue()));
			} else if (udxNode.getKernel().getLength() > 1) {				
				for(int i=0;i<udxNode.getKernel().getLength();i++){
					int data=((UdxKernelIntArray) udxNode
							.getKernel()).getTypedValueByIndex(i);
					
					switch(alignType){
					case Left:
						dataStr.append(StringUtil.LeftAlign(String.valueOf(data), Len));
						break;
					case Right:
						dataStr.append(StringUtil.RightAlign(String.valueOf(data), Len));
						break;
					case None:
						dataStr.append(String.valueOf(data)+StringUtil.getSpace(Len));
						break;
					default:
						break;
					
					}
				}
			} else {
				return null;
			}
			break;
		case EKT_REAL:
			if (udxNode.getKernel().getLength() == 1) {
				dataStr.append(String.valueOf(((UdxKernelRealValue)udxNode.getKernel()).getTypedValue()));
			} else if (udxNode.getKernel().getLength() > 1) {
				for(int i=0;i<udxNode.getKernel().getLength();i++){
					double data=((UdxKernelRealArray)udxNode.getKernel()).getTypedValueByIndex(i);
					switch(alignType){
					case Left:
						dataStr.append(StringUtil.LeftAlign(String.valueOf(data), Len));
						break;
					case Right:
						dataStr.append(StringUtil.RightAlign(String.valueOf(data), Len));
						break;
					case None:
						dataStr.append(String.valueOf(data)+StringUtil.getSpace(Len));
						break;
					default:
						break;
					
					}
				}

			} else {
				return null;
			}
			break;
		case EKT_STRING:
			int len=udxNode.getKernel().getLength();
			String a=udxNode.getName();
			if (len == 1) {
				dataStr.append(((UdxKernelStringValue)udxNode.getKernel()).getTypedValue());
			} else if (len > 1) {
				for(int i=0;i<len;i++){
					String data=((UdxKernelStringArray)udxNode.getKernel()).getTypedValueByIndex(i);
					switch(alignType){
					case Left:
						dataStr.append(StringUtil.LeftAlign(String.valueOf(data), Len));
						break;
					case Right:
						dataStr.append(StringUtil.RightAlign(String.valueOf(data), Len));
						break;
					case None:
						dataStr.append(String.valueOf(data)+StringUtil.getSpace(Len));
						break;
					default:
						break;
					
					}
				}

			} else {
				return null;
			}
			break;

		default:
			break;

		}

		return dataStr.toString();
	}
}
