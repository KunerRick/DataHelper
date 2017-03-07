package com.ngis.util;

import java.util.List;

import com.ngis.util.domain.MDLInputParam;

public class test {

	public static void main2(String[] args) throws Exception {
		String filename = "C:\\Users\\Administrator\\Desktop\\ModelWrapper_QTUI\\wrapper\\model\\slope.mdl";
		
		List<MDLInputParam> list = MDLUtil.parseMDL(filename);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getStateId());
			System.out.println(list.get(i).getResEventName());
			System.out.println(list.get(i).getNoresEventName());
			System.out.println("===================");
		}
		
	}

}
