package com.ngis.util;

import java.util.ArrayList;
import java.util.List;

public class CheckDLLOrder {

	/**
	 *  返回排好序的dll
	 * @param dir dll所在的路径
	 * @param dlls dlls的名称数组
	 * @return
	 */
	public static List<String> getOrderedDLLs(String dir,String[] dlls){
				
		List<String> orderedDLLs = new ArrayList<String>();

		int count = 0;
		while (count < dlls.length) {
			for (int i = 0; i < dlls.length; i++) {
				if (dlls[i] != "") {
					if (loadDLL(dir + dlls[i])) {
						orderedDLLs.add(dir + dlls[i]);
						dlls[i] = "";
						count++;
						break;
					}
				}
			}
		}
		return orderedDLLs;
	}
	
	private static boolean loadDLL(String filename) {
		try {
			System.load(filename);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}
}
