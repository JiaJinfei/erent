package com.sxau.controller.user;


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
import com.sxau.util.StrUtil;

public class UserMachineServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	IMachineService machineService = new MachineService();
	
	IMachineTypeService machineTypeService = new MachineTypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Pager pager = new Pager("/user/UserMachineServlet?1=1", req);
		String typeId = req.getParameter("typeId");
		
		Machine machine = new Machine();
		if(StrUtil.isNotEmpty(typeId)){
			MachineType machineType = machineTypeService.get(Integer.parseInt(typeId));
			machine.setTypeId(Integer.parseInt(typeId)); 
			machine.setType(machineType);
			req.setAttribute("machineList", machineService.list(machine, pager));
		}
		
		req.getRequestDispatcher("/WEB-INF/erent/user/show.jsp").forward(req, resp);
	}

	
	
}
