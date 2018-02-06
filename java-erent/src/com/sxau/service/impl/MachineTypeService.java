package com.sxau.service.impl;

import java.util.List;

import com.sxau.bean.MachineType;
import com.sxau.dao.IMachineTypeDao;
import com.sxau.dao.impl.MachineTypeDao;
import com.sxau.service.IMachineTypeService;
import com.sxau.util.Pager;

public class MachineTypeService implements IMachineTypeService{

	IMachineTypeDao machineDao = new MachineTypeDao();
	
	@Override
	public void save(MachineType machineType) {
		machineDao.save(machineType);
		
	}

	@Override
	public void update(MachineType machineType) {
		machineDao.update(machineType);
	}

	@Override
	public List<MachineType> list(MachineType machineType,Pager pager) {
		return machineDao.list(machineType,pager);
	}

	@Override
	public MachineType get(int typeId) {
		return machineDao.get(typeId);
	}

}
