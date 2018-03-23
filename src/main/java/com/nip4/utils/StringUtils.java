package com.nip4.utils;

public class StringUtils {
	
	private StringUtils() {}
	
	public static boolean isBlank(Object obj) {
		if("".equals(obj)||obj==null) {
			return false;
		}
		return true;
	}
}
