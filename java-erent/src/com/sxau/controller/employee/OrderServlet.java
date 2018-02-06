package com.sxau.controller.employee;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.service.IOrderService;
import com.sxau.service.impl.OrderService;
import com.sxau.util.Pager;

public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//订单管理
	IOrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("list".equals(method)){
			 
			Pager pager = new Pager("/employee/OrderServlet?1=1",req);
			req.setAttribute("orderList", orderService.list(null, pager));
			
			req.getRequestDispatcher("/WEB-INF/erent/employee/order/list.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	
}
