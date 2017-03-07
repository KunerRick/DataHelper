package com.ngis.udx;

public class UdxKernelStringValue extends UdxKernel 
{
	public UdxKernelStringValue(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
	}
	

	public boolean setTypedValue(String pValue)
	{
		return mNode.mHandleApi.setNodeStringValue(mNode.nativeNodeObj, pValue);
	}

	public String getTypedValue()
	{
		return mNode.mHandleApi.getNodeStringValue(mNode.nativeNodeObj);
	}
}
