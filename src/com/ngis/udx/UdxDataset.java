package com.ngis.udx;

public class UdxDataset extends UdxNode {
	private int nativeDxObj = -1;

	public UdxDataset(String dllPaths) {
		super(null, "", EKernelType.EKT_NODE, 1);
		mHandleApi = new UdxHandleApi(dllPaths);
		mName = "dataset";
		nativeDxObj = mHandleApi.createUdxDataset();
		nativeNodeObj = mHandleApi.getDatesetNode(nativeDxObj);
	}

	@Override
	public void finalize() {
		mHandleApi.releaseUdxDataset(nativeDxObj);
	}

	public boolean LoadFromXmlFile(String fileName) {
		mHandleApi.loadFromXmlFile(nativeDxObj, fileName, this);
		return true;
	}

	public boolean FormatToXmlFile(String fileName) {
		mHandleApi.formatToXmlFile(nativeDxObj, fileName);
		return true;
	}
	
	public boolean LoadFromXmlStream(String xmlStr){
		mHandleApi.loadFromXmlStream(nativeDxObj, xmlStr, this);
		return true;
	}
	
	public String FormatToXmlStream(){
		return mHandleApi.formatToXmlStream(nativeDxObj);
	}

	String KernelType2String(EKernelType pType, int len) {
		switch (pType) {
		case EKT_INT: {
			if (len == 1)
				return "int";
			else
				return "int_array";
		}
		case EKT_REAL: {
			if (len == 1)
				return "real";
			else
				return "real_array";
		}
		case EKT_STRING: {
			if (len == 1)
				return "string";
			else
				return "string_array";
		}
		case EKT_VECTOR2: {
			if (len == 1)
				return "vector2d";
			else
				return "vector2d_array";
		}
		case EKT_VECTOR3: {
			if (len == 1)
				return "vector3d";
			else
				return "vector3d_array";
		}
		case EKT_VECTOR4: {
			if (len == 1)
				return "vector4d";
			else
				return "vector4d_array";
		}
		case EKT_NODE:
			return "any";
		case EKT_LIST:
			return "list";
		case EKT_MAP:
			return "map";
		case EKT_TABLE:
			return "table";
		default:
			return "any";
		}
	}

	EKernelType String2KernelType(String pStr) {
		if (pStr == "int" || pStr == "int_array")
			return EKernelType.EKT_INT;
		else if (pStr == "real" || pStr == "real_array")
			return EKernelType.EKT_REAL;
		else if (pStr == "string" || pStr == "string_array")
			return EKernelType.EKT_STRING;
		else if (pStr == "vector2d" || pStr == "vector2d_array")
			return EKernelType.EKT_VECTOR2;
		else if (pStr == "vector3d" || pStr == "vector3d_array")
			return EKernelType.EKT_VECTOR3;
		else if (pStr == "vector4d" || pStr == "vector4d_array")
			return EKernelType.EKT_VECTOR4;
		else if (pStr == "any")
			return EKernelType.EKT_NODE;
		else if (pStr == "list")
			return EKernelType.EKT_LIST;
		else if (pStr == "map")
			return EKernelType.EKT_MAP;
		else if (pStr == "table")
			return EKernelType.EKT_TABLE;
		return null;
	}

}
