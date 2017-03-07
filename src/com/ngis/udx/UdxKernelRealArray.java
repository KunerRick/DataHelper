package com.ngis.udx;

public class UdxKernelRealArray extends UdxKernel {
	public UdxKernelRealArray(EKernelType pType, UdxNode pNode) {
		super(pType, pNode);
	}

	public boolean addTypedValue(double pValue) {
		if (mCurrentIndex == getLength())
			return false;
		mNode.mHandleApi.addNodeRealArrayValue(mNode.nativeNodeObj,
				mCurrentIndex, pValue);
		mCurrentIndex++;
		return true;
	}

	public double getTypedValueByIndex(int idx) {
		if(hasGetFirstValue){
			mCurrentIndex=getLength();
			hasGetFirstValue=false;
		}
		if (idx < 0 || idx >= mCurrentIndex)
			return 0;
		if(idx < 0)
			return 0;
		
		return mNode.mHandleApi.getNodeRealArrayValue(mNode.nativeNodeObj, idx);
	}
}
