package com.sxau.util;

import java.io.IOException;
import java.util.Properties;
/**
 * 连接数据库
 * @author Jaler
 *
 */
public class JDBCProperties {

	public static Properties properties = new Properties();
	
	static {
		try {
			properties.load(JDBCProperties.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return properties.getProperty(key);
	}
}
