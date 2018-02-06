package com.sxau.controller.employee;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.User;
import com.sxau.service.IUserService;
import com.sxau.service.impl.UserService;
import com.sxau.util.MD5;
import com.sxau.util.Pager;
import com.sxau.util.ParameterUtil;
import com.sxau.util.StrUtil;
/**
 * 员工管理业务servlet
 * @author Jaler
 *
 */
public class EmployeeUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//员工管理
	IUserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		
		if("list".equals(method)){
			Pager pager = new Pager("/employee/UserServlet?1=1", req);
			req.setAttribute("users", userService.empList(pager));
			req.getRequestDispatcher("/WEB-INF/erent/employee/user/list.jsp").forward(req, resp);
			return;
		}else if("edit".equals(method)){
			
			String userId = req.getParameter("userId");
			if(StrUtil.isNotEmpty(userId)){
				User user = userService.getEmp(Integer.parseInt(userId));
				req.setAttribute("user", user);
			}
			req.getRequestDispatcher("/WEB-INF/erent/employee/user/edit.jsp").forward(req, resp);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		
		if("add".equals(method)){
			User user = ParameterUtil.put(User.class, req);
			user.setUserType(1);
			user.setUserStatus(1);
			user.setUserPwd(MD5.createPassword(user.getUserUsername()));
			int userId = user.getUserId();
			
			if(userId != 0){
				userService.update(user);
			}else{
				userService.save(user);
			}
			resp.sendRedirect(req.getContextPath() + "/employee/UserServlet?m=list");
			return;
		}else if("validUserName".equals(method)){
			String username = req.getParameter("username");
			
			User user = userService.getEmpUsername(username);
			resp.setCharacterEncoding("UTF-8");
			if(user != null){
				resp.getWriter().write("{code:-1}");
			}
			resp.getWriter().close();
			return;
		}
	}

	
}
