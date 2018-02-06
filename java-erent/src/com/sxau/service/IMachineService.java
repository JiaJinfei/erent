package com.sxau.service;

import java.util.List;

import com.sxau.bean.Machine;
import com.sxau.util.Pager;

public interface IMachineService {

public void save(Machine machine);
	
	public void update(Machine machine);
	
	public List<Machine> list(Machine machine,Pager pager);
	
	public Machine get(int machineId);
}
