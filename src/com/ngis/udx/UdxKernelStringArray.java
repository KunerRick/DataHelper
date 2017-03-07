package com.ngis.udx;

public class UdxKernelStringArray extends UdxKernel 
{
	public UdxKernelStringArray(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
		//mCurrentIndex = getLength();
	}

	public boolean addTypedValue(String pValue)
	{
		if (mCurrentIndex==getLength())
			return false;
		mNode.mHandleApi.addNodeStringArrayValue(mNode.nativeNodeObj, mCurrentIndex, pValue);
		mCurrentIndex++;
		return true;
	}

	public String getTypedValueByIndex(int idx)
	{
		if(hasGetFirstValue){
			mCurrentIndex=getLength();
			hasGetFirstValue=false;
		}
		if (idx<0 || idx>=mCurrentIndex) 
			return "";
		return mNode.mHandleApi.getNodeStringArrayValue(mNode.nativeNodeObj, idx);
	}
}
