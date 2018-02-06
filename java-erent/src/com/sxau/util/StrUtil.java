package com.sxau.util;

public class StrUtil {

	public static boolean isEmpty(String str){
		
		if(str != null && !"".equals(str)){
			return false;
		}
		return true;
	}
	
	public static boolean isNotEmpty(String str){
		if(StrUtil.isEmpty(str)){
			return false;
		}
		return true;
	}
	
	public static Integer toInt(Number number){
		return number.intValue();
	}
	
	/**
	 * 返回length位随要数
	 * @param length
	 * @return
	 */
	public static long random(int length){
		
		return (long)(Math.random() * Math.pow(10, length));
		
	}
	
	
}
