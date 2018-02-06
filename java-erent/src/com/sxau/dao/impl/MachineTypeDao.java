package com.sxau.dao.impl;

import java.util.List;

import com.sxau.bean.MachineType;
import com.sxau.dao.IMachineTypeDao;
import com.sxau.util.JDBCUtil;
import com.sxau.util.Pager;

public class MachineTypeDao implements IMachineTypeDao {

	JDBCUtil util = new JDBCUtil();
	@Override
	public void save(MachineType machineType) {
		String sql = "insert into m_type(type_id,type_name,type_code,type_sort,parent_id,type_status,type_remark) values(seq_type.nextval,?,?,?,?,?,?)";
		util.execute(sql, new Object[]{machineType.getTypeName(),machineType.getTypeCode(),machineType.getTypeSort(),machineType.getParentId(),machineType.getTypeStatus(),machineType.getTypeRemark()});
	}

	@Override
	public void update(MachineType machineType) {
		String sql = "update m_type set type_name=? ,type_code=? ,type_sort=? ,parent_id=? ,type_status=? ,type_remark=? where type_id=?";
		util.execute(sql, new Object[]{machineType.getTypeName(),machineType.getTypeCode(),machineType.getTypeSort(),machineType.getParentId(),machineType.getTypeStatus(),machineType.getTypeRemark(),machineType.getTypeId()});
	}

	@Override
	public List<MachineType> list(MachineType machineType,Pager pager) {
		String sql = "select t.*,mt.type_name as \"parent.type_name\" from m_type t left join m_type mt on t.parent_id = mt.type_id where 1=1 ";
		if(machineType != null){
			if(machineType.getParentId() < 1){
				sql += " and t.parent_id is null or t.parent_id = 0 ";
			}else{
				sql += " and t.parent_id = " + machineType.getParentId();
			}
		}
		
		sql += "order by t.type_sort";
		return util.executeQuery(MachineType.class, sql, null, pager);
	}

	@Override
	public MachineType get(int typeId) {
		String sql = "select * from m_type where type_id = ?";
		List<MachineType> list = util.executeQuery(MachineType.class, sql, new Object[]{typeId});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
}
