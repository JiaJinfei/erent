package com.sxau.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

/**
 * 自动添加请求参数到类中
 * @author Jaler
 *
 */
public class ParameterUtil {

	
	public static <T> T put(Class<T> cla,HttpServletRequest request){
		T obj = null;
		try {
			obj = cla.newInstance();
			Field[] fields = cla.getDeclaredFields();
			
			for (Field field : fields){
				//取属性名
				String fieldName = field.getName();
				
				//从request当中取参数
				String value = request.getParameter(fieldName);
				
				if(value != null && !"".equals(value)){
					//首字母变大写
					String firstChar = fieldName.substring(0, 1).toUpperCase();
					
					//属性名去掉首字母
					String newFieldName = fieldName.substring(1);
					
					String setterName = "set" + firstChar + newFieldName;
					
					Class<?> type = field.getType();
					Method setter =  cla.getMethod(setterName,type);
						
					Object val = null;
					//转换参数
				    if(type.equals(int.class) || type.equals(Integer.class)){
				    	
				    	val = Integer.parseInt(value);
				    	
					}else if(type.equals(String.class)){
						
						val = value;
						
					}else if(type.equals(char.class) || type.equals(Character.class)){
						
						val = value.charAt(0);
						
					}else if(type.equals(byte.class) || type.equals(Byte.class)){
						
						val = Byte.parseByte(value);
						
					}else if(type.equals(short.class) || type.equals(Short.class)){
						
						val = Short.parseShort(value);
						
					}else if(type.equals(float.class) || type.equals(Float.class)){
						
						val = Float.parseFloat(value);
						
					}else if(type.equals(double.class) || type.equals(Double.class)){
						
						val = Double.parseDouble(value);
						
					}else if(type.equals(long.class) || type.equals(Long.class)){
						
						val = Long.parseLong(value);
						
					}else if(type.equals(boolean.class) || type.equals(Boolean.class)){
						
						if("true".equals(value) || "1".equals(value) ){
							val = true;
						}else{
							val = false;
						}
					}else if(type.equals(BigDecimal.class)){
						
						val = new BigDecimal(value);
						
					}
				    
				    setter.invoke(obj,val);
				}
				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
		
	} 
	
}
