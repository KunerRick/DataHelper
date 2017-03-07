package com.ngis.udx;

public class AppTest {
	
	  
	
	static void IterateNode(UdxNode pNode) {
		String name = pNode.getName();
		EKernelType kernelType = pNode.getKernel().getType();
		UdxKernel kernel = pNode.getKernel();
		int valueLen = kernel.getLength();
		System.out.print("\n");
		System.out.printf("name=%s\n", name);
		System.out.printf("kernelType=%s\n", kernelType.toString());
		if (kernelType == EKernelType.EKT_INT) {
			if (valueLen == 1) {
				UdxKernelIntValue realKernel = (UdxKernelIntValue) kernel;
				int val = realKernel.getTypedValue();
				System.out.printf("value = %d \n", val);
			} else {
				UdxKernelIntArray realKernel = (UdxKernelIntArray) kernel;
				for (int iVal = 0; iVal < valueLen; iVal++) {
					int val = realKernel.getTypedValueByIndex(iVal);
					System.out.printf("value = %d \n", val);
				}
				System.out.print("\n");
			}
		} else if (kernelType == EKernelType.EKT_REAL) {
			if (valueLen == 1) {
				UdxKernelRealValue realKernel = (UdxKernelRealValue) kernel;
				double val = realKernel.getTypedValue();
				System.out.printf("value = %f \n", val);
			} else {
				UdxKernelRealArray realKernel = (UdxKernelRealArray) kernel;
				for (int iVal = 0; iVal < valueLen; iVal++) {
					double val = realKernel.getTypedValueByIndex(iVal);
					System.out.printf("value = %f \n", val);
				}
				System.out.print("\n");
			}
		} else if (kernelType == EKernelType.EKT_STRING) {
			if (valueLen == 1) {
				UdxKernelStringValue realKernel = (UdxKernelStringValue) kernel;
				String val = realKernel.getTypedValue();
				System.out.printf("value = %s \n", val);
			} else {
				UdxKernelStringArray realKernel = (UdxKernelStringArray) kernel;
				for (int iVal = 0; iVal < valueLen; iVal++) {
					String val = realKernel.getTypedValueByIndex(iVal);
					System.out.printf("value = %s \n", val);
				}
				System.out.print("\n");
			}
		} else if (kernelType == EKernelType.EKT_VECTOR2) {
			if (valueLen == 1) {
				UdxKernelVector2dValue realKernel = (UdxKernelVector2dValue) kernel;
				Vector2d vec = realKernel.getTypedValue();
				System.out.printf("value = %f, %f \n", vec.x, vec.y);
			} else {
				UdxKernelVector2dArray realKernel = (UdxKernelVector2dArray) kernel;
				for (int iVal = 0; iVal < valueLen; iVal++) {
					Vector2d vec = realKernel.getTypedValueByIndex(iVal);
					if (vec != null)
						System.out.printf("value = %f, %f; \n", vec.x, vec.y);
				}
				System.out.print("\n");
			}
		} else if (kernelType == EKernelType.EKT_VECTOR3) {
			if (valueLen == 1) {
				UdxKernelVector3dValue realKernel = (UdxKernelVector3dValue) kernel;
				Vector3d vec = realKernel.getTypedValue();
				System.out.printf("value = %f, %f, %f \n", vec.x, vec.y, vec.z);
			} else {
				UdxKernelVector3dArray realKernel = (UdxKernelVector3dArray) kernel;
				for (int iVal = 0; iVal < valueLen; iVal++) {
					Vector3d vec = realKernel.getTypedValueByIndex(iVal);
					if (vec != null)
						System.out.printf("value = %f, %f, %f; \n", vec.x,
								vec.y, vec.z);
				}
				System.out.print("\n");
			}
		} else if (kernelType == EKernelType.EKT_VECTOR4) {
			if (valueLen == 1) {
				UdxKernelVector4dValue realKernel = (UdxKernelVector4dValue) kernel;
				Vector4d vec = realKernel.getTypedValue();
				System.out.printf("value = %f, %f, %f, %f \n", vec.x, vec.y,
						vec.z, vec.m);
			} else {
				UdxKernelVector4dArray realKernel = (UdxKernelVector4dArray) kernel;
				for (int iVal = 0; iVal < valueLen; iVal++) {
					Vector4d vec = realKernel.getTypedValueByIndex(iVal);
					if (vec != null)
						System.out.printf("value = %f, %f, %f, %f; \n", vec.x,
								vec.y, vec.z, vec.m);
				}
				System.out.print("\n");
			}
		} else if (kernelType == EKernelType.EKT_NODE
				|| kernelType == EKernelType.EKT_LIST
				|| kernelType == EKernelType.EKT_MAP
				|| kernelType == EKernelType.EKT_TABLE) {
			int nodeCount = pNode.getChildNodeCount();
			for (int iNode = 0; iNode < nodeCount; iNode++) {
				IterateNode(pNode.getChildNode(iNode));
			}
		}

	}

	public static void genTestUdx() {
		UdxHandleApi myHandle = new UdxHandleApi("C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
		int version = myHandle.getVersion();
		System.out.print(version);

		UdxDataset udxDataset = new UdxDataset("C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
		udxDataset.addChildNode("Node1", EKernelType.EKT_INT, 1);
		udxDataset.addChildNode("Node2", EKernelType.EKT_NODE, 1);

		UdxNode firstNode = udxDataset.getChildNode(0);
		{
			((UdxKernelIntValue) firstNode.getKernel()).setTypedValue(1234);
		}

		UdxNode secondNode = udxDataset.getChildNode(1);
		{
			UdxNode realValueNode = secondNode.addChildNode("我是Node2_node1",
					EKernelType.EKT_REAL, 1);
			((UdxKernelRealValue) realValueNode.getKernel())
					.setTypedValue(10.234);

			// ////////////////////////////////////////////////////////////////////////
			UdxNode stringValueNode = secondNode.addChildNode("Node2_node2",
					EKernelType.EKT_STRING, 1);
			((UdxKernelStringValue) stringValueNode.getKernel())
					.setTypedValue("woshishui我是谁");

			// ////////////////////////////////////////////////////////////////////////
			UdxNode vector4dArrayNode = secondNode.addChildNode("Node2_node3",
					EKernelType.EKT_VECTOR4, 5);
			UdxKernelVector4dArray realKernel = (UdxKernelVector4dArray) vector4dArrayNode
					.getKernel();
			realKernel.addTypedValue(1.0, 2.0, 3.0, 4.0);
			realKernel.addTypedValue(5.0, 6.0, 7.0, 8.0);
			realKernel.addTypedValue(9.0, 10.0, 11.0, 12.0);
			realKernel.addTypedValue(13.0, 14.0, 15.0, 16.0);
		}

		// ///////////////////////////////////////////////////////////

		for (int iNode = 0; iNode < udxDataset.getChildNodeCount(); iNode++) {
			IterateNode(udxDataset.getChildNode(iNode));
		}

		udxDataset.FormatToXmlFile("D:\\testUdxInJava.xml");
	}

	public static void LoadUdx() {
		UdxHandleApi myHandle = new UdxHandleApi("C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
		int version = myHandle.getVersion();
		System.out.println("version: "+version);

		UdxDataset udxDataset = new UdxDataset("C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
//		udxDataset.LoadFromXmlFile("D:\\UP\\..\\testUdxInJava.xml");
		udxDataset.LoadFromXmlFile("D:\\slope_data.xml");
		
		UdxNode node1 = udxDataset.getChildNode(0);
		int method = ((UdxKernelIntValue)node1.getKernel()).getTypedValue();
		
		UdxNode node2 = udxDataset.getChildNode(1);
		UdxNode node2_1 = node2.getChildNode(0);
		UdxNode node2_2 = node2.getChildNode(1);
		
		String inPath = ((UdxKernelStringValue)node2_1.getKernel()).getTypedValue();
		String outPath = ((UdxKernelStringValue)node2_2.getKernel()).getTypedValue();
		
		System.out.println("method="+method);
		System.out.println("inPath="+inPath);
		System.out.println("outPath="+outPath);
		

//		for (int iNode = 0; iNode < udxDataset.getChildNodeCount(); iNode++) {
//			IterateNode(udxDataset.getChildNode(iNode));
//		}
//
//		udxDataset.FormatToXmlFile("D:\\testUdxInJava1.xml");
	}

	public static void main(String[] args) {
		System.out.println("Test JNI UDX");
		
		
//		System.out.println(System.getProperty("java.class.path"));

		LoadUdx();
		
		genTestUdx();
	}
}
