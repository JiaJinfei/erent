package com.sxau.controller.user;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.Machine;
import com.sxau.bean.Order;
import com.sxau.bean.User;
import com.sxau.service.IMachineService;
import com.sxau.service.IOrderService;
import com.sxau.service.impl.MachineService;
import com.sxau.service.impl.OrderService;
import com.sxau.util.DateStr;
import com.sxau.util.StrUtil;

public class UserMachineDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IMachineService machineService = new MachineService();
	
	IOrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 String method = req.getParameter("m");
		 if("detail".equals(method)){
			 String machineId = req.getParameter("machineId");
			 if(StrUtil.isNotEmpty(machineId)){
				 req.setAttribute("mdetail", machineService.get(Integer.parseInt(machineId)));
			 }
			 req.getRequestDispatcher("/WEB-INF/erent/user/detail.jsp").forward(req, resp);
			 return;
		 }else if("back".equals(method)){
				
			resp.sendRedirect(req.getContextPath() + "/user/IndexServlet");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("order".equals(method)){
			String orderAddr = req.getParameter("orderAddr");
			String machineNum = req.getParameter("machineNum");
			String m_id = req.getParameter("machineId");
			
			int machineId = 0;
			if(StrUtil.isNotEmpty(m_id)){
				machineId = Integer.parseInt(m_id);
			}
			
			//根据id查出机械
			Machine machine = new Machine();
			
			machine = machineService.get(machineId);
		
			//把得到的值放到order中
			Order order = new Order();
			//订单工作目的地址
			order.setOrderAddr(orderAddr);
			//订单的商品id
			order.setMachineId(machineId);
			//订单的机械数量
			if(StrUtil.isNotEmpty(machineNum)){
				order.setMachineNum(Integer.parseInt(machineNum));
			}
			//订单的编号
			String dateStr = DateStr.format("yyyyMMdd");
			String randomCode = dateStr + (int)(Math.random() * 100); 
			order.setOrderCode(randomCode);
			//订单日期
			order.setOrderDate(DateStr.format("yyyy-MM-dd"));
			//订单总价
			if(StrUtil.isNotEmpty(machineNum)){
				if(machine != null && machine.getMachinePrice() != 0){
					double price = machine.getMachinePrice() * Integer.parseInt(machineNum);
					order.setOrderPrice(price);
				}
			}
			//订单的状态
			order.setOrderStatus(1);
			
			
			//提取用户信息
			User user = (User)req.getSession().getAttribute("user_session");
			Order orderDetail = new Order();
			orderDetail.setMachineId(machineId);
			orderDetail.setUserId(user.getUserId());
			orderDetail.setOrderAddr(orderAddr);
			orderDetail.setOrderStatus(1);
			//根据用户信息得到订单列表判断是否更新
			List<Order> updateList = orderService.listSameGoods(orderDetail);
			
			//订单的userId
			order.setUserId(user.getUserId());
			
			if(updateList.size() > 0){
				Order data_detail = updateList.get(0);
				data_detail.setMachineNum(Integer.parseInt(machineNum) + data_detail.getMachineNum());
				data_detail.setOrderPrice(order.getOrderPrice() + data_detail.getOrderPrice());
				orderService.update(data_detail);
			}else{
				orderService.save(order);
			}
			
			//修改机械的剩余数量
			if(StrUtil.isNotEmpty(machineNum)){
				machine.setMachineNum(machine.getMachineNum() - Integer.parseInt(machineNum));
			}
			machineService.update(machine);
			
			req.getRequestDispatcher("/WEB-INF/erent/user/tips.jsp").forward(req, resp);
			return;
		}
	}

	
}
