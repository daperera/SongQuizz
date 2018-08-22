package com.telecom.misc;

public class Utils {
	public static String getFileNameExtention(String filename) {
		try {
	        return filename.substring(filename.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
}
