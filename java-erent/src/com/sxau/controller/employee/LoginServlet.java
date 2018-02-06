package com.sxau.controller.employee;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxau.bean.User;
import com.sxau.dao.IUserDao;
import com.sxau.dao.impl.UserDao;
import com.sxau.util.MD5;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//用户验证
	IUserDao userDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("out".equals(method)){
			User user = (User)req.getSession().getAttribute("employee_user");
			if(user != null){
				user.setUserStatus(1);
				userDao.update(user);
				req.getSession().removeAttribute("employee_user");
				req.getSession().invalidate();
			}
			resp.sendRedirect(req.getContextPath() + "/employee/LoginServlet?m=login");
		}else if("login".equals(method)){
			req.getRequestDispatcher("/WEB-INF/erent/employee/login.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("log".equals(method)){
			String username = req.getParameter("userUsername");
			String password = req.getParameter("userPwd");
			
			User user_data = userDao.getEmpUsername(username);
			
			if(user_data != null && user_data.getUserPwd().equals(MD5.createPassword(password))){
				//登录
				HttpSession session = req.getSession();
				user_data.setUserStatus(0);
				userDao.update(user_data);
				session.setAttribute("employee_user", user_data);
				resp.sendRedirect(req.getContextPath() + "/employee/index");
				return;
			}
			req.setAttribute("msg", "登录失败，请检查用户名和密码");
			req.getRequestDispatcher("/WEB-INF/erent/employee/login.jsp").forward(req, resp);
			return;
		}
	}

	
}
