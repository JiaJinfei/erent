package com.sxau.service.impl;

import java.util.List;

import com.sxau.bean.Machine;
import com.sxau.dao.IMachineDao;
import com.sxau.dao.impl.MachineDao;
import com.sxau.service.IMachineService;
import com.sxau.util.Pager;

public class MachineService implements IMachineService{

	IMachineDao machineDao = new MachineDao();
	
	@Override
	public void save(Machine machine) {
		machineDao.save(machine);
	}

	@Override
	public void update(Machine machine) {
		machineDao.update(machine);
	}

	@Override
	public List<Machine> list(Machine machine,Pager pager) {
		return machineDao.list(machine, pager);
	}

	@Override
	public Machine get(int machineId) {
		return machineDao.get(machineId);
	}

}
