package com.ngis.udx;

public class UdxKernelIntValue extends UdxKernel {
	public UdxKernelIntValue(EKernelType pType, UdxNode pNode) {
		super(pType, pNode);
	}

	public boolean setTypedValue(int pValue) {
		return mNode.mHandleApi.setNodeIntValue(mNode.nativeNodeObj, pValue);
	}

	public int getTypedValue() {
		return mNode.mHandleApi.getNodeIntValue(mNode.nativeNodeObj);
	}
}
