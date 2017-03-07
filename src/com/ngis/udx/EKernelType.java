package com.ngis.udx;

public enum EKernelType {
	EKT_NULL,
	EKT_INT,			//!<integer
	EKT_REAL,			//!<float with 64bit precise
	EKT_VECTOR2,		//!<2 dimensions vector with x and y
	EKT_VECTOR3,		//!<3 dimensions vector with x, y and z
	EKT_VECTOR4,		//!<4 dimensions vector with x, y, z and w
	EKT_STRING,			//!<a string with NULL terminated
	EKT_WSTRING,		//!<a wide byte(16 bits) string
	EKT_NODE,			//!<a no-constraints node container
	EKT_LIST,			//!<a container with same structure node
	EKT_MAP,			//!<a k-v map container
	EKT_TABLE,			//!<a data table container
	EKT_COUNT
}
