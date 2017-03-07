package com.ngis.udx;

public class UdxKernelVector3dArray extends UdxKernel 
{
	public UdxKernelVector3dArray(EKernelType pType, UdxNode pNode)
	{
		super(pType, pNode);
		//mCurrentIndex = this.getLength();
	}

	public boolean addTypedValue(double pX, double pY, double pZ)
	{
		if (mCurrentIndex==getLength())
			return false;
		mNode.mHandleApi.addNodeVector3dArrayValue(mNode.nativeNodeObj, mCurrentIndex, pX, pY, pZ);
		mCurrentIndex++;
		return true;
	}

	public Vector3d getTypedValueByIndex(int idx)
	{
		if(hasGetFirstValue){
			mCurrentIndex=getLength();
			hasGetFirstValue=false;
		}
		if (idx<0 || idx>=mCurrentIndex) 
			return null;
		double[] ret = mNode.mHandleApi.getNodeVector3dArrayValue(mNode.nativeNodeObj, idx);
		Vector3d vec3 = new Vector3d();
		vec3.x = ret[0];
		vec3.y = ret[1];
		vec3.z = ret[2];
		return vec3;
	}
}
