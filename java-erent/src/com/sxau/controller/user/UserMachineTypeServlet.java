package com.sxau.controller.user;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.MachineType;
import com.sxau.service.IMachineTypeService;
import com.sxau.service.impl.MachineTypeService;

public class UserMachineTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IMachineTypeService machineTypeService = new MachineTypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		MachineType machineType = new MachineType();
		machineType.setParentId(-1);
		req.setAttribute("typeList", machineTypeService.list(machineType,null));
		
		req.getRequestDispatcher("/WEB-INF/erent/user/goodstype.jsp").forward(req, resp);
		return;
	}

	
}
