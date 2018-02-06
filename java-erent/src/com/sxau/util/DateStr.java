package com.sxau.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStr {

	public static String format(Date date,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	public static String format(String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}
}
