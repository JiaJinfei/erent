package com.sxau.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.sxau.bean.Machine;
import com.sxau.dao.IMachineDao;
import com.sxau.util.JDBCUtil;
import com.sxau.util.Pager;
import com.sxau.util.StrUtil;

public class MachineDao implements IMachineDao{

	JDBCUtil util = new JDBCUtil();
	
	@Override
	public void save(Machine machine) {
		String sql = "insert into machine(machine_id,machine_name,machine_price,type_id,machine_code,machine_status,machine_num,machine_remark,machine_pic,machine_pic2) values(seq_machine.nextval,?,?,?,?,?,?,?,?,?)";
		util.execute(sql, new Object[]{machine.getMachineName(),machine.getMachinePrice(),machine.getTypeId(),machine.getMachineCode(),machine.getMachineStatus(),machine.getMachineNum(),machine.getMachineRemark(),machine.getMachinePic(),machine.getMachinePic2()});
	}

	@Override
	public void update(Machine machine) {
		String sql = "update machine set machine_name=?, machine_price=?,type_id=?,machine_code=?,machine_status=?,machine_num=?,machine_remark=?,machine_pic=?,machine_pic2=? where machine_id=?";
		util.execute(sql, new Object[]{machine.getMachineName(),machine.getMachinePrice(),machine.getTypeId(),machine.getMachineCode(),machine.getMachineStatus(),machine.getMachineNum(),machine.getMachineRemark(),machine.getMachinePic(),machine.getMachinePic2(),machine.getMachineId()});
	}

	@Override
	public List<Machine> list(Machine machine,Pager pager) {
		String sql = "select m.*,t.type_name as \"type.type_name\" from machine m left join m_type t on m.type_id = t.type_id where 1=1";

		List<Object> parameters = new ArrayList<Object>();
		if(machine != null){
			if(StrUtil.isNotEmpty(machine.getMachineCode())){
				sql += " and t.machine_code is like '%'|| ? || '%'";
				parameters.add(machine.getMachineCode());
			}
			if(StrUtil.isNotEmpty(machine.getMachineName())){
				sql += " and t.machine_name is like '%'|| ? || '%'";
				parameters.add(machine.getMachineName());
			}
			if(machine.getTypeId() != 0){
				sql += " and t.type_id = ? ";
				parameters.add(machine.getTypeId());
			}
		}
		
		List<Machine> list = util.executeQuery(Machine.class, sql, parameters.toArray(), pager);
		return list;
	}

	@Override
	public Machine get(int machineId) {
		String sql = "select * from machine where machine_id = ?";
		List<Machine> list = util.executeQuery(Machine.class, sql, new Object[]{machineId});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	public static void main(String[] args) {
		MachineDao machineDao = new MachineDao();
		List<Machine> list = machineDao.list(null, null);
		for(Machine m : list){
			System.out.println(m);
		}
	}
	
}
