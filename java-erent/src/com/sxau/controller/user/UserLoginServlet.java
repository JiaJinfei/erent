package com.sxau.controller.user;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("login".equals(method)){
			req.getRequestDispatcher("/WEB-INF/erent/user/login.jsp").forward(req, resp);
			return;
		}else if("regpage".equals(method)){
			req.getRequestDispatcher("/WEB-INF/erent/user/regpage.jsp").forward(req, resp);
			return;
		}else if("out".equals(method)){
			req.getSession().removeAttribute("user_session");
			req.getSession().invalidate();
			
			resp.sendRedirect(req.getContextPath() + "/user/UserLoginServlet?m=login");
			
		}
	}

	
}
