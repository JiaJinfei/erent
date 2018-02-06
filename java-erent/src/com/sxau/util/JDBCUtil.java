package com.sxau.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 创建数据库连接，保存数据库方法
 * @author Jaler
 *
 */
public class JDBCUtil {

	//连接
	Connection conn = null;
	
	//statement对象用于执行sql语句
	PreparedStatement pst = null;

	//结果集
	ResultSet rs = null;

	/**
	 * 连接
	 */
	public Connection connect(){
		try {
			Class.forName(JDBCProperties.getValue("driverClassName"));
			conn = DriverManager.getConnection(JDBCProperties.getValue("url"), JDBCProperties.getValue("username"), JDBCProperties.getValue("password"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 创建statement
	 */
	public PreparedStatement createStatment(String sql){
		//连接数据库
		conn = connect();
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 执行带结果集的sql
	 */
	
	public <T> List<T> executeQuery(Class<T> cla,String sql,Object[] objs){
		System.out.println(sql);
		//创建statement
		pst = createStatment(sql);
		List<T> list = null;
		try {
			if (objs != null){
				for (int i = 1; i <= objs.length; i++){
					pst.setObject(i,objs[i - 1]);
				}
			}
			rs = pst.executeQuery();
			list = toList(cla, rs);
		} catch (SQLException e) {
			e.printStackTrace();
			list = new ArrayList<T>();
		}finally{
			close();
		}
		return list;
	}
	
	
	/**
	 * 执行不带结果集的sql
	 */
	public void execute(String sql,Object[] objs){
		
		pst = createStatment(sql);
		try {
			if (objs != null){
				for (int i = 1; i <= objs.length; i++){

					//对java.util.Date的特列处理：转java.sql.Date
					Object obj = objs[i - 1];
					
					if(obj != null && obj instanceof java.util.Date){
						//obj = new java.sql.Date(((java.util.Date)obj).getTime());
						obj = new java.sql.Timestamp(((java.util.Date)obj).getTime());
					}
					
					pst.setObject(i,obj);
				}
			}
		int num = pst.executeUpdate();
		System.out.println(sql);
		System.out.println("受影响的行数为：" + num);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    close();
		}
	}
	
	/**
	 * 执行带结果集的sql,带分页的
	 */
	
	public <T> List<T> executeQuery(Class<T> cla,String sql,Object[] objs,Pager pager){
		
		if(pager != null){
			String countSql = pager.getCountSql(sql);
			System.out.println(countSql);
			pst = createStatment(countSql);
			
			Integer count = 0;
			try {
				if (objs != null){
					for (int i = 1; i <= objs.length; i++){
						pst.setObject(i,objs[i - 1]);
					}
				}
				rs = pst.executeQuery();
				if(rs.next()){
					count = rs.getInt(1);
				}
				System.out.println("count:" + count);
			} catch (SQLException e) {
			}finally{
				try {
					rs.close();
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			pager.setTotalCount(count);
			
			//处理翻页sql
			sql = pager.getPageSql(sql);
		}
		
		System.out.println(sql);
		pst = createStatment(sql);
		List<T> list = null;
		try {
			if (objs != null){
				for (int i = 1; i <= objs.length; i++){
					pst.setObject(i,objs[i - 1]);
				}
			}
			rs = pst.executeQuery();
			list = toList(cla, rs);
		} catch (SQLException e) {
			e.printStackTrace();
			list = new ArrayList<T>();
		}finally{
			close();
		}
		return list;
	}
	
	
	/**
	 * 执行insert语句，执行成功，返回表的主键
	 */
	public int insert(String sql,String idSql,Object[] objs){
		System.out.println(sql);

		int id = 0;
		
		//连接数据库
		connect();
		try {
			pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (objs != null){
				for (int i = 1; i <= objs.length; i++){
					
					//对java.util.Date的特列处理：转java.sql.Date
					Object obj = objs[i - 1];
					
					if(obj != null && obj instanceof java.util.Date){
						obj = new java.sql.Timestamp(((java.util.Date)obj).getTime());
					}
					pst.setObject(i,obj);
				}
			}
		int num = pst.executeUpdate();
		
		ResultSet rs = pst.getGeneratedKeys();
		String rowId = "";
		if(rs.next()){
			rowId = rs.getString(1);
		}
		rs.close();
		pst.close();
		
         //select id from tablename where rowId = ?
		pst = conn.prepareStatement(idSql);
		pst.setString(1,rowId);
		
		rs = pst.executeQuery();
		
		if(rs.next()){
			id = rs.getInt(1);
		}
		
		System.out.println("受影响的行数为：" + num);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    close();
		}
		
		return id;
	}
	
	/**
	 * 关闭所有对象
	 */
	public void close(){
		try {
			if (rs != null){
				rs.close();
			}
			if (pst != null){
				pst.close();
			}
			if (conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将结果集转化为对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(Class<T> cla,ResultSet rs){
		
		List<T> list = new ArrayList<T>();
		
		//1.获取结果集里面所有的列的列名
		try {
			while (rs.next()){
			//5.反射java对象。
			Object obj = cla.newInstance();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++){
				//取列名
				String columnName = rsmd.getColumnName(i).toLowerCase();
				
				//处理外键的情况
				if (columnName.indexOf(".") != -1){
					//列名称用.分隔
					String[] fields = columnName.split("[.]");
					//第一个位置是外键对象名称
					String fieldName = fields[0];
					//第二个位置是外键对象当中的字段名称
					String foreignFieldName = fields[1];
					
					//去下划线，并且下划线后面的首字母变大写
					int _position = foreignFieldName.indexOf("_");
					if(_position != -1){
						String[] foreignFieldNames = foreignFieldName.split("[_]");
						
						String preChar = foreignFieldNames[1].substring(0, 1).toUpperCase();
						
						String otherName = foreignFieldNames[1].substring(1);
						foreignFieldName = foreignFieldNames[0] + preChar + otherName;
					}
					//取到外键对象这个字段
					Field field = cla.getDeclaredField(fieldName);
					//临时名称，用于拼接getter and setter
					String tempName = String.valueOf(fieldName.charAt(0)).toUpperCase()  + fieldName.substring(1);
					//获得getter
					Method getMethod = cla.getMethod("get" + tempName );
					//执行getter，获得外键对象
					Object foreign = getMethod.invoke(obj);
					//如果外键对象为空，则new一个实例，通过setter set到当前对象中
					if (foreign == null){
						foreign = field.getType().newInstance();
						Method setMethod = cla.getMethod("set" + tempName, field.getType());
						setMethod.invoke(obj, foreign);
					}
					//外键对象当中的临时字段名称
					String foreignTempName = String.valueOf(foreignFieldName.charAt(0)).toUpperCase()  + foreignFieldName.substring(1);; 
					//获得外键对象当中的字段
					
					Field foreignField = field.getType().getDeclaredField(foreignFieldName);
					//取外键对象当中字段的setter
					Method foreignSetMethod = field.getType().getMethod("set" + foreignTempName, foreignField.getType());
					
					//setType_name()
					
					//获取字段的值
					Object value = null;
					Class<?> javaType = foreignField.getType();
					if (javaType.equals(int.class) || javaType.equals(Integer.class) ){
						value = rs.getInt(columnName);
					}else if (javaType.equals(String.class)){
						value = rs.getString(columnName);
					}else if (javaType.equals(byte.class) || javaType.equals(Byte.class)){
						value = rs.getByte(columnName);
					}else if (javaType.equals(short.class) || javaType.equals(Short.class)){
						value = rs.getShort(columnName);
					}else if (javaType.equals(long.class) || javaType.equals(Long.class)){
						value = rs.getLong(columnName);
					}else if (javaType.equals(float.class) || javaType.equals(Float.class)){
						value = rs.getFloat(columnName);
					}else if (javaType.equals(double.class) || javaType.equals(Double.class)){
						value = rs.getDouble(columnName);
					}else if (javaType.equals(BigDecimal.class)){
						value = rs.getBigDecimal(columnName);
					}else if (javaType.equals(Date.class)){
//						value = rs.getDate(columnName);
						value = rs.getTimestamp(columnName);
					}else if (javaType.equals(boolean.class) || javaType.equals(Boolean.class)){
						value = rs.getBoolean(columnName);
					}else if (javaType.equals(Object.class)){
						value = rs.getObject(columnName);
					}else{
						System.out.println("未识别的列类型：" + javaType);
					}
					//执行setter
					foreignSetMethod.invoke(foreign, value);
				}
				
				//2.将列名转化为java对象中的属性名
				int position = columnName.indexOf("_");
				
				//属性名
				String fieldName;
				if (position != -1){
					//取下划线后面的首字母
					char firstChar = columnName.charAt(position + 1);
					//将_x格式转化成X
					//给属性名赋值，属性名等于去掉下划后，将下划线后面的首字母大写
					fieldName = columnName.replace("_" + firstChar, String.valueOf(firstChar).toUpperCase());
				}else{
					//给属性名赋值，属性名等于列名
					fieldName = columnName;
				}
				
				//3.根据java属性名找属性名的java类型
				//找到对应的字段
				Field field = null;
				try {
					field = cla.getDeclaredField(fieldName);
				} catch (NoSuchFieldException e) {
					//e.printStackTrace();
				}
				if (field != null){
					//取字段的java类型
					Class<?> javaType = field.getType();
					//根据java类型和set方法名称找到对应的set方法
					String methodName = "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
					Method setMethod = cla.getDeclaredMethod(methodName, javaType);
					
					//4.从结果集取值。（根据属性名的java类型执行对应的rs.getXX方法。）
					Object value = null;
					if (javaType.equals(int.class) || javaType.equals(Integer.class) ){
						value = rs.getInt(columnName);
					}else if (javaType.equals(String.class)){
						value = rs.getString(columnName);
					}else if (javaType.equals(byte.class) || javaType.equals(Byte.class)){
						value = rs.getByte(columnName);
					}else if (javaType.equals(short.class) || javaType.equals(Short.class)){
						value = rs.getShort(columnName);
					}else if (javaType.equals(long.class) || javaType.equals(Long.class)){
						value = rs.getLong(columnName);
					}else if (javaType.equals(float.class) || javaType.equals(Float.class)){
						value = rs.getFloat(columnName);
					}else if (javaType.equals(double.class) || javaType.equals(Double.class)){
						value = rs.getDouble(columnName);
					}else if (javaType.equals(BigDecimal.class)){
						value = rs.getBigDecimal(columnName);
					}else if (javaType.equals(Date.class)){
//						value = rs.getDate(columnName);
						value = rs.getTimestamp(columnName);
					}else if (javaType.equals(boolean.class) || javaType.equals(Boolean.class)){
						value = rs.getBoolean(columnName);
					}else if (javaType.equals(Object.class)){
						value = rs.getObject(columnName);
					}else{
						System.out.println("未识别的列类型：" + javaType);
					}
					//6.将结果集取到的值set到反射的对象中。	
					setMethod.invoke(obj, value);
				}
				
			}
			list.add((T)obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
