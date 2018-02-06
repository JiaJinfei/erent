package com.sxau.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sxau.bean.Attachment;
import com.sxau.service.IAttachmentService;
import com.sxau.service.impl.AttachmentService;
import com.sxau.util.ConfigProperties;

@MultipartConfig
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String _uploadPath;
	
	private String[] _allowedTypes;
	
	private Long _allowedSize;
	
	public void init() throws ServletException {
		_uploadPath = ConfigProperties.getValue("uploadPath"); 
		
		String types = ConfigProperties.getValue("allowedType");
		_allowedTypes = types.split(",");
		
		_allowedSize = Long.parseLong(ConfigProperties.getValue("allowedSize")); 
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 接收上传的图片
		Part uploadImg = req.getPart("upload");
		//上传图片的大小
		long size = uploadImg.getSize();
		//做一个判断，只有file组件不为空，并且当前上传的图片的大小不为0，才执行上传操作
		if (size > 0) {

			long allowedSize = _allowedSize;
			
			String maxSize = req.getParameter("size");
			
			if(maxSize != null && !"".equals(maxSize)){
				if(Long.parseLong(maxSize) < allowedSize){
					allowedSize = Long.parseLong(maxSize);
				}
			}
			
			if(size > allowedSize){
				req.setAttribute("msg", "上传失败，文件超也了允许的大小[" + allowedSize + "B]");
				req.setAttribute("code", "failed");
				req.getRequestDispatcher("/page/inc/upload.jsp").forward(req, resp);
				return;
			}
			
			List<String> allowedTypes = new ArrayList<String>();
			String allowedType_str = "";
			
			String type = req.getParameter("type");
			if(type != null && !"".equals(type)){
				type = type.toLowerCase();
				String[] types = type.split(",");
				for(String t : types){
					for(String _allowedType : _allowedTypes){
						if(t.equals(_allowedType)){
							allowedTypes.add("." + t);
							allowedType_str += t + ",";
							break;
						}
						
					}
				}
			}else{
				
				for(String _allowedType : _allowedTypes){
					allowedTypes.add("." + _allowedType);
					allowedType_str += _allowedType + ",";
				}
			}
			allowedType_str = allowedType_str.substring(0,allowedType_str.length() - 1);
			
			// 从头文件中提取信息
			String header = uploadImg.getHeader("content-disposition");
			// 截取文件名
			String fileName = header.substring(
					header.indexOf("filename=") + 10,
					header.length() - 1);
			// 截取扩展名
			String extendName = fileName.substring(fileName
					.lastIndexOf("."));
			
			if(allowedTypes.indexOf(extendName) == -1){
				req.setAttribute("msg", "上传失败，文件类型超出了允许的范围[" + allowedType_str + "]");
				req.setAttribute("code", "failed");
				req.getRequestDispatcher("/page/inc/upload.jsp").forward(req, resp);
				return;
			}
			//得到当前时间的特定字符串
			String nowDate = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			//修改后的文件名为：  --> 时间字符串 + 5位的随机数 + 文件扩展名
			String randomFileName = nowDate
					+ (int) (Math.random() * Math.pow(10, 5)) + extendName;
			System.out.println("文件名为：----------------》" + randomFileName);
			
			//得到上传的文件的输入流InputStream
			InputStream inputStream = uploadImg.getInputStream();
			//将这个文件读取到指定的文件中
			OutputStream outputStream = new FileOutputStream(new File(
					_uploadPath + "/" + randomFileName));
			int b;
			while ((b = inputStream.read()) != -1) {
				outputStream.write(b);
			}
			//关闭输入流和输出流
			outputStream.close();
			inputStream.close();
			
			req.setAttribute("msg", "上传成功");
			req.setAttribute("code", "success");
			req.setAttribute("path", randomFileName);
			
			//如果是附件，则向附件表写值，并返回id给客户端
			String isAtta = req.getParameter("isAtta");
			if("1".equals(isAtta)){
				//向附件表写值
				IAttachmentService sttachmentService = new AttachmentService();
				
				Attachment attachment = new Attachment();
				attachment.setName(fileName);
				attachment.setPath(randomFileName);
				int id = sttachmentService.save(attachment);
				req.setAttribute("path",id);
				req.setAttribute("fileName", fileName);
			}
		}
		
		req.getRequestDispatcher("/page/inc/upload.jsp").forward(req, resp);
		
		/*Part uploadAttachment = req.getPart("uploadAttachment");
		//上传图片的大小
		long attachmentSize = uploadAttachment.getSize();
		//做一个判断，只有file组件不为空，并且当前上传的图片的大小不为0，才执行上传操作
		if (attachmentSize > 0) {
			//获取当前tomcat的真实路径下的upload文件
			String uploadPath = req.getServletContext().getRealPath(
					"upload");

			System.out.println(uploadPath);
			// 从头文件中提取信息
			String attachmentHeader = uploadAttachment.getHeader("content-disposition");
			// 截取文件名
			String fileName = attachmentHeader.substring(
					attachmentHeader.indexOf("filename=") + 10,
					attachmentHeader.length() - 1);
			String realFileName = fileName;
			
			// 截取扩展名
			String extendName = fileName.substring(fileName
					.lastIndexOf("."));
			//得到当前时间的特定字符串
			String nowDate = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			//修改后的文件名为：  --> 时间字符串 + 5位的随机数 + 文件扩展名
			String randomFileName = nowDate
					+ (int) (Math.random() * Math.pow(10, 5)) + extendName;
			System.out.println("文件名为：----------------》" + randomFileName);
			
			//得到上传的文件的输入流InputStream
			InputStream inputStream = uploadAttachment.getInputStream();
			//将这个文件读取到指定的文件中
			OutputStream outputStream = new FileOutputStream(new File(
					uploadPath + "/" + randomFileName));
			int b;
			while ((b = inputStream.read()) != -1) {
				outputStream.write(b);
			}
			//关闭输入流和输出流
			outputStream.close();
			inputStream.close();
			
			//将拿到的随机文件全名传给student的img属性
			student.setAttachment(randomFileName);
			
			student.setAttachmentName(realFileName);
		}*/
	}

	

}
