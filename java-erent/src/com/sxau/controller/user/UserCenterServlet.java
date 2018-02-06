package com.sxau.controller.user;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.User;
import com.sxau.service.IUserService;
import com.sxau.service.impl.UserService;
import com.sxau.util.MD5;

public class UserCenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IUserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("repwd".equals(method)){
			req.getRequestDispatcher("/WEB-INF/erent/user/pass.jsp").forward(req, resp);
		}else if("rename".equals(method)){
			req.getRequestDispatcher("/WEB-INF/erent/user/rename.jsp").forward(req, resp);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("repwd".equals(method)){
			String mpass = req.getParameter("mpass");
			String newpass = req.getParameter("newpass");
			
			User user = (User)req.getSession().getAttribute("user_session");
			if(user.getUserPwd().equals(MD5.createPassword(mpass))){
				user.setUserPwd(MD5.createPassword(newpass));
				userService.update(user);
				resp.sendRedirect(req.getContextPath() + "/user/IndexServlet");
				return;
			}else{
				req.setAttribute("msg", "原始密码不正确！请重新输入！");
				req.getRequestDispatcher("/WEB-INF/erent/user/pass.jsp").forward(req, resp);
				return;
			}
			
		}else if("newName".equals(method)){
			String newName = req.getParameter("newName");
			
			User user = (User)req.getSession().getAttribute("user_session");
			
			user.setUserName(newName);
			
			userService.update(user);
			
			resp.sendRedirect(req.getContextPath()+ "/user/IndexServlet");
			
			return;
		}
	}

	
}
