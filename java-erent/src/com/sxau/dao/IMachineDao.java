package com.sxau.dao;

import java.util.List;

import com.sxau.bean.Machine;
import com.sxau.util.Pager;

public interface IMachineDao {

	public void save(Machine machine);
	
	public void update(Machine machine);
	
	public List<Machine> list(Machine machine,Pager pager);
	
	public Machine get(int machineId);
	
}
