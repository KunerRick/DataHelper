package com.ngis.udx;

public class UdxKernelIntArray extends UdxKernel 
{
	public UdxKernelIntArray(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
	}

	public boolean addTypedValue(int pValue)
	{
		if (mCurrentIndex==getLength())
			return false;
		mNode.mHandleApi.addNodeIntArrayValue(mNode.nativeNodeObj, mCurrentIndex, pValue);
		mCurrentIndex++;
		return true;
	}

	public int getTypedValueByIndex(int idx)
	{
		if(hasGetFirstValue){
			mCurrentIndex=getLength();
			hasGetFirstValue=false;
		}
		
		if (idx<0 || idx>=mCurrentIndex) 
			return 0;
		
		if(idx<0)
			return 0;
		
		return mNode.mHandleApi.getNodeIntArrayValue(mNode.nativeNodeObj, idx);
	}
}
