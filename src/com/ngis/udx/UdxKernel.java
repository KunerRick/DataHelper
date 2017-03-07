package com.ngis.udx;

public class UdxKernel 
{
	protected UdxNode mNode;
	protected EKernelType mType;
	protected int mCurrentIndex;
	protected boolean hasGetFirstValue;
	public UdxKernel(EKernelType pType, UdxNode pNode)
	{
		mNode = pNode;
		mType = pType;
		mCurrentIndex = 0;
		hasGetFirstValue=true;
	}
	
	public UdxNode getNode()
	{
		return mNode;
	}

	public EKernelType getType()
	{
		return mType;
	}

	public int getLength()
	{
		return mNode.mHandleApi.getCellLength(mNode.nativeNodeObj);
	}


	public static int KernelType2Int(EKernelType pType)
	{
		if (pType == EKernelType.EKT_NULL) return 0;
		else if (pType == EKernelType.EKT_INT) return 1;
		else if (pType == EKernelType.EKT_REAL) return 2;
		else if (pType == EKernelType.EKT_VECTOR2) return 3;
		else if (pType == EKernelType.EKT_VECTOR3) return 4;
		else if (pType == EKernelType.EKT_VECTOR4) return 5;
		else if (pType == EKernelType.EKT_STRING) return 6;
		else if (pType == EKernelType.EKT_WSTRING) return 7;
		else if (pType == EKernelType.EKT_NODE) return 8;
		else if (pType == EKernelType.EKT_LIST) return 9;
		else if (pType == EKernelType.EKT_MAP) return 10;
		else if (pType == EKernelType.EKT_TABLE) return 11;
		else if (pType == EKernelType.EKT_COUNT) return 12;
		return -1;
	}
	
	public static EKernelType Int2KernelType(int pType)
	{
		if (pType == 0) return EKernelType.EKT_NULL;
		else if (pType == 1) return EKernelType.EKT_INT;
		else if (pType == 2) return EKernelType.EKT_REAL;
		else if (pType == 3) return EKernelType.EKT_VECTOR2;
		else if (pType == 4) return EKernelType.EKT_VECTOR3;
		else if (pType == 5) return EKernelType.EKT_VECTOR4;
		else if (pType == 6) return EKernelType.EKT_STRING;
		else if (pType == 7) return EKernelType.EKT_WSTRING;
		else if (pType == 8) return EKernelType.EKT_NODE;
		else if (pType == 9) return EKernelType.EKT_LIST;
		else if (pType == 10) return EKernelType.EKT_MAP;
		else if (pType == 11) return EKernelType.EKT_TABLE;
		else if (pType == 12) return EKernelType.EKT_COUNT;
		return EKernelType.EKT_NULL;
	}
}
