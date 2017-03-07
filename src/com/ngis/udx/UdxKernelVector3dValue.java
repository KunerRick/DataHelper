package com.ngis.udx;

public class UdxKernelVector3dValue extends UdxKernel 
{
	public UdxKernelVector3dValue(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
	}
	

	public boolean setTypedValue(double pX, double pY, double pZ)
	{
		return mNode.mHandleApi.setNodeVector3dValue(mNode.nativeNodeObj, pX, pY, pZ);
	}

	public Vector3d getTypedValue()
	{
		double[] ret = mNode.mHandleApi.getNodeVector3dValue(mNode.nativeNodeObj);
		Vector3d vec3 = new Vector3d();
		vec3.x = ret[0];
		vec3.y = ret[1];
		vec3.z = ret[2];
		return vec3;
	}
}
