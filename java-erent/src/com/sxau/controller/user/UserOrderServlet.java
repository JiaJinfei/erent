package com.sxau.controller.user;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.Alipay;
import com.sxau.bean.Machine;
import com.sxau.bean.Order;
import com.sxau.bean.User;
import com.sxau.service.IMachineService;
import com.sxau.service.IOrderService;
import com.sxau.service.impl.MachineService;
import com.sxau.service.impl.OrderService;
import com.sxau.util.StrUtil;

public class UserOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IOrderService orderService = new OrderService();
	
	IMachineService machineService = new MachineService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("order".equals(method)){
			User user = (User)req.getSession().getAttribute("user_session");
			
			List<Order> orderList = orderService.listFromUser(user.getUserId());
			
			req.setAttribute("orderList", orderList);
			
			req.getRequestDispatcher("/WEB-INF/erent/user/orderlist.jsp").forward(req, resp);
			return;
		}else if("del".equals(method)){
			String orderId = req.getParameter("orderId");
			if(StrUtil.isNotEmpty(orderId)){
				orderService.delete(Integer.parseInt(orderId));
			}
			resp.sendRedirect(req.getContextPath() + "/user/UserOrderServlet?m=order");
			return;
		}else if("addnum".equals(method)){
			
			String orderId = req.getParameter("orderId");
			Order order = new Order();
			Machine machine = new Machine();
			if(StrUtil.isNotEmpty(orderId)){
				order = orderService.get(Integer.parseInt(orderId));
				order.setOrderId(Integer.parseInt(orderId));
				
				machine = machineService.get(order.getMachineId());
			}
			order.setMachineNum(order.getMachineNum() + 1);
			order.setOrderPrice(order.getOrderPrice() + machine.getMachinePrice());
			orderService.update(order);
			
			resp.sendRedirect(req.getContextPath() + "/user/UserOrderServlet?m=order");
			return;
		}else if("reducenum".equals(method)){
			String orderId = req.getParameter("orderId");
			Order order = new Order();
			Machine machine = new Machine();
			if(StrUtil.isNotEmpty(orderId)){
				order = orderService.get(Integer.parseInt(orderId));
				order.setOrderId(Integer.parseInt(orderId));
				
				machine = machineService.get(order.getMachineId());
			}
			if(order.getMachineNum() >= 1){
				order.setMachineNum(order.getMachineNum() - 1);
				order.setOrderPrice(order.getOrderPrice() - machine.getMachinePrice());
				orderService.update(order);
			}
			resp.sendRedirect(req.getContextPath() + "/user/UserOrderServlet?m=order");
			return;
		}else if("success".equals(method)){
			req.getRequestDispatcher("/WEB-INF/erent/user/pay_success.jsp").forward(req,resp);
			return;
		}else if("failed".equals(method)){
			req.getRequestDispatcher("/WEB-INF/erent/user/pay_failed.jsp").forward(req, resp);
			return;
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("delAll".equals(method)){
			String[] orderIds = req.getParameterValues("deleteId");
			for (String orderId : orderIds){
				if(StrUtil.isNotEmpty(orderId)){
					orderService.delete(Integer.parseInt(orderId));
				}
			}
			resp.sendRedirect(req.getContextPath() + "/user/UserOrderServlet?m=order");
			return;
		}else if("paySuccess".equals(method)){
			//付款方账号
			String payerNo = req.getParameter("payerNo");
			//
			String payerPwd = req.getParameter("payerPwd");
			
			//金额
			String money = req.getParameter("totalPrice");
			
			//订单数组
			String[] orderIds = req.getParameterValues("orderId");
			
			
			Alipay alipay = new Alipay();
			if(StrUtil.isNotEmpty(money)){
				alipay.setMoney(Double.parseDouble(money));
			}
			alipay.setPayerNo(payerNo);
			alipay.setPayerPwd(payerPwd);
			if(orderService.pay(alipay)){
				Order order = new Order();
				if(orderIds.length > 0){
					for (String orderId : orderIds){
						if(StrUtil.isNotEmpty(orderId)){
							order.setOrderId(Integer.parseInt(orderId));
							order.setOrderStatus(0);
							orderService.update(order);
							
							resp.sendRedirect(req.getContextPath() + "/user/UserOrderServlet?m=success");
						}
					}
				}
			}else{
				resp.sendRedirect(req.getContextPath() + "/user/UserOrderServlet?m=failed");
			}
			
			return;
		}else if("pay".equals(method)){
			//orderId集合
			String[] orderIds = req.getParameterValues("deleteId");
			
			//金额
			String totalPrcie = req.getParameter("totalPrcie");
			
			req.setAttribute("orderIds", orderIds);
			req.setAttribute("totalPrcie", totalPrcie);
			
			req.getRequestDispatcher("/WEB-INF/erent/user/pay.jsp").forward(req, resp);
		}
	}

	
}
