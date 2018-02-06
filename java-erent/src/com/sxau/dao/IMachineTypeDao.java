package com.sxau.dao;

import java.util.List;

import com.sxau.bean.MachineType;
import com.sxau.util.Pager;

public interface IMachineTypeDao {

	public void save(MachineType machineType);
	
	public void update(MachineType machineType);
	
	public List<MachineType> list(MachineType machineType,Pager pager);
	
	public MachineType get(int typeId);
	
}
