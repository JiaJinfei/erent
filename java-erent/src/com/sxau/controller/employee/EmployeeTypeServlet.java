package com.sxau.controller.employee;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxau.bean.MachineType;
import com.sxau.service.IMachineTypeService;
import com.sxau.service.impl.MachineTypeService;
import com.sxau.util.Pager;
import com.sxau.util.ParameterUtil;
import com.sxau.util.StrUtil;

public class EmployeeTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IMachineTypeService machineService = new MachineTypeService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		
		if("list".equals(method)){
			Pager pager = new Pager("/employee/TypeServlet?1=1", req);
			List<MachineType> list = machineService.list(null, pager);
			
			req.setAttribute("machineTypes", list);
			
			req.getRequestDispatcher("/WEB-INF/erent/employee/emptype/list.jsp").forward(req, resp);
			return;
		}else if("edit".equals(method)){
			//父分类下拉框
			MachineType machineType = new MachineType();
			machineType.setParentId(-1);
			req.setAttribute("parentList", machineService.list(machineType,null));
			
			//修改 回显
			String typeId = req.getParameter("typeId");
			if(StrUtil.isNotEmpty(typeId)){
				machineType = machineService.get(Integer.parseInt(typeId));
			}
			req.setAttribute("machineType", machineType);
			
			req.getRequestDispatcher("/WEB-INF/erent/employee/emptype/edit.jsp").forward(req, resp);
			return;
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		
		if("add".equals(method)){
			MachineType machineType = ParameterUtil.put(MachineType.class, req);
			
			int typeId = machineType.getTypeId();
			
			if(typeId != 0){
				machineService.update(machineType);
			}else{
				machineService.save(machineType);
			}
			
			resp.sendRedirect(req.getContextPath() + "/employee/TypeServlet?m=list");
			return;
		}
	}
	
	
}
