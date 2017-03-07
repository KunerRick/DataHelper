package com.ngis.udx;

import java.util.ArrayList;

public class UdxNode {
	protected UdxHandleApi mHandleApi;
	protected String mName;
	protected UdxNode mParentNode;
	protected UdxKernel mKernel;

	private ArrayList<UdxNode> mChildNodes;

	protected int nativeNodeObj;

	public UdxNode(UdxNode parentNode, String pName, EKernelType pType,
			int pLength) {
		mChildNodes = new ArrayList<UdxNode>();
		if (parentNode != null) {
			mName = pName;
			mParentNode = parentNode;
			mHandleApi = parentNode.mHandleApi;
			createKernel(pName, pType, pLength);
		}
	}

	private void createKernel(String pName, EKernelType pType, int pLength) {
		int kernelType = UdxKernel.KernelType2Int(pType);
		nativeNodeObj = mHandleApi.addNode(mParentNode.nativeNodeObj, mName,
				kernelType, pLength);
		switch (pType) {
		case EKT_NULL:
		case EKT_COUNT:
			break;

		case EKT_INT: {
			if (pLength == 1)
				mKernel = new UdxKernelIntValue(pType, this);
			else
				mKernel = new UdxKernelIntArray(pType, this);
		}
			break;
		case EKT_REAL: {
			if (pLength == 1)
				mKernel = new UdxKernelRealValue(pType, this);
			else
				mKernel = new UdxKernelRealArray(pType, this);
		}
			break;
		case EKT_STRING: {
			if (pLength == 1)
				mKernel = new UdxKernelStringValue(pType, this);
			else
				mKernel = new UdxKernelStringArray(pType, this);
		}
			break;
		case EKT_WSTRING:
			break;
		case EKT_VECTOR2: {
			if (pLength == 1)
				mKernel = new UdxKernelVector2dValue(pType, this);
			else
				mKernel = new UdxKernelVector2dArray(pType, this);
		}
			break;
		case EKT_VECTOR3: {
			if (pLength == 1)
				mKernel = new UdxKernelVector3dValue(pType, this);
			else
				mKernel = new UdxKernelVector3dArray(pType, this);
		}
			break;
		case EKT_VECTOR4: {
			if (pLength == 1)
				mKernel = new UdxKernelVector4dValue(pType, this);
			else
				mKernel = new UdxKernelVector4dArray(pType, this);
		}
			break;

		case EKT_NODE:
		case EKT_LIST:
		case EKT_MAP:
		case EKT_TABLE: {
			mKernel = new UdxKernel(pType, this);
		}
			break;
		}
	}

	public String getName() {
		return mName;
	}

	public UdxKernel getKernel() {
		return mKernel;
	}

	public int getChildNodeCount() {
		return mChildNodes.size();
	}

	public UdxNode getChildNode(int idx) {
		if (idx < 0 || idx >= mChildNodes.size())
			return null;
		return mChildNodes.get(idx);
	}

	public UdxNode addChildNode(String pName, EKernelType pType, int valueLen) {
		UdxNode node = new UdxNode(this, pName, pType, valueLen);
		mChildNodes.add(node);
		return node;
	}

	public boolean removeChildNode(UdxNode pNode) {
		return false;
	}

	public boolean removeChildNode(int idx) {
		return false;
	}

	/*
	 * public int onReturnAddChildNode(int nodeObj, String pName, int pType, int
	 * valueLen) { UdxNode node = new UdxNode(this, pName,
	 * UdxKernel.Int2KernelType(pType), valueLen); mChildNodes.add(node); return
	 * node.nativeNodeObj; }
	 */

	public int currentIndex;
	UdxNode tempNode = null;
	public ArrayList<UdxNode> nodeStack = new ArrayList<UdxNode>();

	public int onReturnAddChildNode(int nodeObj, String pName, int pType,
			int pLength) {
		if (nodeStack.size() == 0) {
			nodeStack.add(this);
		}
		EKernelType kernelType = UdxKernel.Int2KernelType(pType);
		tempNode = nodeStack.get(nodeStack.size() - 1).addChildNode(pName,
				kernelType, pLength);

		return tempNode.nativeNodeObj;
	}

	public void onPushNode() {
		nodeStack.add(tempNode);
	}

	public void onPopNode() {
		nodeStack.remove(nodeStack.size() - 1);
	}
}
