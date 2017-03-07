package com.ngis.udx;

public class UdxKernelVector4dValue extends UdxKernel 
{
	public UdxKernelVector4dValue(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
	}
	

	public boolean setTypedValue(double pX, double pY, double pZ, double pM)
	{
		return mNode.mHandleApi.setNodeVector4dValue(mNode.nativeNodeObj, pX, pY, pZ, pM);
	}

	public Vector4d getTypedValue()
	{
		double[] ret = mNode.mHandleApi.getNodeVector4dValue(mNode.nativeNodeObj);
		Vector4d vec4 = new Vector4d();
		vec4.x = ret[0];
		vec4.y = ret[1];
		vec4.z = ret[2];
		vec4.m = ret[3];
		return vec4;
	}
}
