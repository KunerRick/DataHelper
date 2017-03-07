package com.Binary.Schema;

public enum ESchemaNodeType {
		EDTKT_INT ,				//!<integer
		EDTKT_INT_LIST,
		EDTKT_REAL,				//!<float with 64bit precise
		EDTKT_REAL_LIST,
		EDTKT_VECTOR2,		//!<2 dimensions vector with x and y
		EDTKT_VECTOR2_LIST,
		EDTKT_VECTOR3,		//!<3 dimensions vector with x, y and z
		EDTKT_VECTOR3_LIST,
		EDTKT_VECTOR4,		//!<4 dimensions vector with x, y, z and w
		EDTKT_VECTOR4_LIST,
		EDTKT_STRING,			//!<a string with NULL terminated
		EDTKT_STRING_LIST,
		EDTKT_NODE,			//!<a no-constraints node container
		EDTKT_LIST,				//!<a container with same structure node
		EDTKT_MAP,				//!<a k-v map container
		EDTKT_TABLE,			//!<a data table container
}
