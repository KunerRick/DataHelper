package com.ngis.udx;

public class UdxKernelVector4dArray extends UdxKernel 
{
	public UdxKernelVector4dArray(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
		//mCurrentIndex = this.getLength();
	}

	public boolean addTypedValue(double pX, double pY, double pZ, double pM)
	{
		if (mCurrentIndex==getLength())
			return false;
		mNode.mHandleApi.addNodeVector4dArrayValue(mNode.nativeNodeObj, mCurrentIndex, pX, pY, pZ, pM);
		mCurrentIndex++;
		return true;
	}

	public Vector4d getTypedValueByIndex(int idx)
	{
		if(hasGetFirstValue){
			mCurrentIndex=getLength();
			hasGetFirstValue=false;
		}
		if (idx<0 || idx>=mCurrentIndex) 
			return null;
		double[] ret = mNode.mHandleApi.getNodeVector4dArrayValue(mNode.nativeNodeObj, idx);
		Vector4d vec4 = new Vector4d();
		vec4.x = ret[0];
		vec4.y = ret[1];
		vec4.z = ret[2];
		vec4.m = ret[3];
		return vec4;
	}
}
