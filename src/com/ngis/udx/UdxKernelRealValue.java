package com.ngis.udx;

public class UdxKernelRealValue  extends UdxKernel 
{
	public UdxKernelRealValue(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
	}
	

	public boolean setTypedValue(double pValue)
	{
		return mNode.mHandleApi.setNodeRealValue(mNode.nativeNodeObj, pValue);
	}

	public double getTypedValue()
	{
		return mNode.mHandleApi.getNodeRealValue(mNode.nativeNodeObj);
	}
}
