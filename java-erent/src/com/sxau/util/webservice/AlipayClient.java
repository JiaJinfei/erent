package com.sxau.util.webservice;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import net.sf.json.JSONObject;

import com.sxau.bean.Alipay;

public class AlipayClient {

	/**
	 * 向httpUrl发送get请求
	 * @param httpUrl
	 * @param alipay
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> sendGet(String httpUrl,Alipay alipay){
		
		StringBuffer buffer = new StringBuffer();
		
		try {
			//处理参数
			httpUrl += "?payerNo=" + alipay.getPayerNo();
			httpUrl += "&receiverNo=" + alipay.getReceiverNo();
			httpUrl += "&money=" + alipay.getMoney();
			httpUrl += "&payerPwd=" + alipay.getPayerPwd();
			
			URL url = new URL(httpUrl);
			//发送，并接收返回的参数
			InputStream inputStream = url.openStream();
			Reader reader = new InputStreamReader(inputStream);
			
			char[] chars = new char[1024];
			int length;
			do{
				length = reader.read(chars);
				buffer.append(new String(chars,0,length));
			}while(length == 1024);
			
			inputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(buffer.toString());
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> sendPost(String httpUrl,Alipay alipay){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		try {
			URL url = new URL(httpUrl);
			
			//处理url
			String parameter = "payerNo=" + alipay.getPayerNo() + "&receiverNo=" + alipay.getReceiverNo() + "&money=" + alipay.getMoney() + "&payerPwd=" + alipay.getPayerPwd();
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);//打开允许向外的口子
			
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(parameter.getBytes());//将参数转为二进制并输出
			outputStream.close();
			
			InputStream inputStream = connection.getInputStream();
			Reader reader = new InputStreamReader(inputStream);
			
			char[] chars = new char[1024];
			int length;
			do{
				length = reader.read(chars);
				buffer.append(new String(chars,0,length));
			}while(length == 1024);
		} catch (Exception e) {
			e.printStackTrace();
		}
		buffer.append("}");
		return JSONObject.fromObject(buffer.toString());
	}
	
	public static void main(String[] args) {
		Alipay alipay = new Alipay();
		alipay.setMoney(1000);
		alipay.setPayerNo("jaler");
		alipay.setReceiverNo("anli");
		System.out.println(sendGet("http://127.0.0.1/alipay/pay",alipay));
		//System.out.println(sendPost("http://127.0.0.1/alipay/pay",alipay));
	}
}
