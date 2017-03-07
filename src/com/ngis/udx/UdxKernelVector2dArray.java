package com.ngis.udx;

public class UdxKernelVector2dArray extends UdxKernel 
{
	public UdxKernelVector2dArray(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
		//mCurrentIndex = this.getLength();
	}

	public boolean addTypedValue(double pX, double pY)
	{
		if (mCurrentIndex==getLength())
			return false;
		mNode.mHandleApi.addNodeVector2dArrayValue(mNode.nativeNodeObj, mCurrentIndex, pX, pY);
		mCurrentIndex++;
		return true;
	}

	public Vector2d getTypedValueByIndex(int idx)
	{
		if(hasGetFirstValue){
			mCurrentIndex=getLength();
			hasGetFirstValue=false;
		}
		if (idx<0 || idx>=mCurrentIndex) 
			return null;
		double[] ret = mNode.mHandleApi.getNodeVector2dArrayValue(mNode.nativeNodeObj, idx);
		Vector2d vec2 = new Vector2d();
		vec2.x = ret[0];
		vec2.y = ret[1];
		return vec2;
	}
}
