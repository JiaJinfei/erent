package com.sxau.controller.user;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.User;
import com.sxau.service.IUserService;
import com.sxau.service.impl.UserService;
import com.sxau.util.DateStr;
import com.sxau.util.MD5;
import com.sxau.util.ParameterUtil;

public class UserIndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IUserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/erent/user/home.jsp").forward(req, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("log".equals(method)){
			String username = req.getParameter("userUsername");
			String password = req.getParameter("userPwd");
			
			User user_data = userService.getUsername(username);
			if(user_data != null && user_data.getUserPwd().equals(MD5.createPassword(password))){
				req.getSession().setAttribute("user_session", user_data);
				resp.sendRedirect(req.getContextPath() + "/user/IndexServlet");
				System.out.println("登录成功！用户名：" + username);
				return;
			}
			req.setAttribute("msg", "登录失败，请检查用户名和密码");
			req.getRequestDispatcher("/WEB-INF/erent/user/login.jsp").forward(req, resp);
			return;
		}else if("reg".equals(method)){
			User user = ParameterUtil.put(User.class, req);
			//用户类型
			user.setUserType(0);
			
			//加密密码
			user.setUserPwd(MD5.createPassword(user.getUserPwd()));
			
			//用户编号
			String dateStr = DateStr.format("yyyyMMdd");
			String randomStr = dateStr + (int)(Math.random() * 100);
			user.setUserCode(randomStr);
			
			//用户状态
			user.setUserStatus(1);
			
			//用户姓名
			user.setUserName("新用户" + (int)(Math.random() * 100));
			
			
			userService.save(user);
			User user_tmp = userService.getByUserCode(user.getUserCode());
			req.getSession().setAttribute("user_session", user_tmp);
			resp.sendRedirect(req.getContextPath() + "/user/IndexServlet");
			return;
		}
	}

	
}
