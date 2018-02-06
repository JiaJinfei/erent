package com.sxau.dao;

import java.util.List;

import com.sxau.bean.User;
import com.sxau.util.Pager;

public interface IUserDao {

	public void save(User user);
	
	public void update(User user);
	
	//查询员工的list表
	public List<User> empList(Pager pager);
	
	//查询客户的list表
	public List<User> UserList();
	
	//得到客户信息
	public User getUser(int userId);
	
	//得到员工信息
	public User getEmp(int userId);
	
	//通过用户名获得员工
	public User getEmpUsername(String userUsername);
	
	//通过用户名获得用户
	public User getUsername(String userUsername);
	
	//用过用户编号获得用户
	public User getByCode(String userCode);
}
