package com.sxau.controller.employee;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.Machine;
import com.sxau.bean.MachineType;
import com.sxau.service.IMachineService;
import com.sxau.service.IMachineTypeService;
import com.sxau.service.impl.MachineService;
import com.sxau.service.impl.MachineTypeService;
import com.sxau.util.Pager;
import com.sxau.util.ParameterUtil;
import com.sxau.util.StrUtil;

public class EmployeeMachineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//商品管理
	IMachineService machineService = new MachineService();
	
	//商品分类管理
	IMachineTypeService machineTypeService = new MachineTypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("list".equals(method)){
			Pager pager = new Pager("/employee/MachineServlet?1=1", req);
			req.setAttribute("machineList", machineService.list(null, pager));
			req.getRequestDispatcher("/WEB-INF/erent/employee/machine/list.jsp").forward(req, resp);
			return;
		}else if("edit".equals(method)){
			
			//所属分类
			MachineType machineType = new MachineType();
			machineType.setParentId(-1);
			req.setAttribute("typeList", machineTypeService.list(machineType,null));
			
			//修改，机械商品回显
			String machineId = req.getParameter("machineId");
			if(StrUtil.isNotEmpty(machineId)){
				Machine machine = machineService.get(Integer.parseInt(machineId));
				req.setAttribute("machine", machine);
			}
			
			req.getRequestDispatcher("/WEB-INF/erent/employee/machine/edit.jsp").forward(req, resp);
			return;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("add".equals(method)){
			Machine machine = ParameterUtil.put(Machine.class, req);
			
			int machineId = machine.getMachineId();
			System.out.println("此时的machineId是：" + machineId);
			if(machineId != 0){
				machineService.update(machine);
			}else{
				System.out.println("此时的machineId是：" + machineId + "判断结果：" + (machineId != 0));
				machineService.save(machine);
			}	
		}
		
		resp.sendRedirect(req.getContextPath() + "/employee/MachineServlet?m=list");
	}

	
}
