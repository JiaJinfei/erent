package com.sxau.dao.impl;

import java.util.List;

import com.sxau.bean.User;
import com.sxau.dao.IUserDao;
import com.sxau.util.JDBCUtil;
import com.sxau.util.Pager;

public class UserDao implements IUserDao{

	JDBCUtil util = new JDBCUtil();
	@Override
	public void save(User user) {
		String sql = "insert into t_user(user_id,user_code,user_username,user_pwd,user_name,user_phone,user_type,user_status) values(seq_user.nextval,?,?,?,?,?,?,?)";
		util.execute(sql, new Object[]{user.getUserCode(),user.getUserUsername(),user.getUserPwd(),user.getUserName(),user.getUserPhone(),user.getUserType(),user.getUserStatus()});
	}

	@Override
	public void update(User user) {
		String sql = "update t_user set user_code=?, user_username=?, user_pwd=?, user_name=?, user_phone=?, user_status=? where user_id=?";
		util.execute(sql, new Object[]{user.getUserCode(),user.getUserUsername(),user.getUserPwd(),user.getUserName(),user.getUserPhone(),user.getUserStatus(),user.getUserId()});
	}

	@Override
	public List<User> empList(Pager pager) {
		String sql = "select * from t_user where user_type = 1";
		List<User> list =  util.executeQuery(User.class, sql, null, pager);
		return list;
	}

	@Override
	public List<User> UserList() {
		String sql = "select * from t_user where user_type = 0";
		List<User> list =  util.executeQuery(User.class, sql, null);
		return list;
	}

	@Override
	public User getUser(int userId) {
		String sql = "select * from t_user where user_type = 0 and user_id = ?";
		List<User> list = util.executeQuery(User.class, sql, new Object[]{userId});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public User getEmp(int userId) {
		String sql = "select * from t_user where user_type = 1 and user_id = ?";
		List<User> list = util.executeQuery(User.class, sql, new Object[]{userId});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public User getEmpUsername(String userUsername) {
		
		String sql = "select * from t_user where user_type = 1 and user_username= ? ";
		List<User> list = util.executeQuery(User.class, sql, new Object[]{userUsername});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public User getUsername(String userUsername) {
		
		String sql = "select * from t_user where user_type = 0 and user_username= ? ";
		List<User> list = util.executeQuery(User.class, sql, new Object[]{userUsername});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public User getByCode(String userCode) {

		String sql = "select * from t_user where user_type = 0 and user_code = ? ";
		List<User> list = util.executeQuery(User.class, sql, new Object[]{userCode});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	
	
}
