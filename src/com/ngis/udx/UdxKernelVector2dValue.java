package com.ngis.udx;

public class UdxKernelVector2dValue extends UdxKernel 
{
	public UdxKernelVector2dValue(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
	}
	

	public boolean setTypedValue(double pX, double pY)
	{
		return mNode.mHandleApi.setNodeVector2dValue(mNode.nativeNodeObj, pX, pY);
	}

	public Vector2d getTypedValue()
	{
		double[] ret = mNode.mHandleApi.getNodeVector2dValue(mNode.nativeNodeObj);
		Vector2d vec2 = new Vector2d();
		vec2.x = ret[0];
		vec2.y = ret[1];
		return vec2;
	}
}
