package com.sxau.service.impl;

import java.util.List;

import com.sxau.bean.User;
import com.sxau.dao.IUserDao;
import com.sxau.dao.impl.UserDao;
import com.sxau.service.IUserService;
import com.sxau.util.Pager;

public class UserService implements IUserService{

	IUserDao userDao = new UserDao();
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> empList(Pager pager) {
		return userDao.empList(pager);
	}

	@Override
	public List<User> UserList() {
		return userDao.empList(null);
	}

	@Override
	public User getUser(int userId) {
		return userDao.getUser(userId);
	}

	@Override
	public User getEmp(int userId) {
		return userDao.getEmp(userId);
	}

	@Override
	public User getEmpUsername(String userUsername) {
		return userDao.getEmpUsername(userUsername);
	}

	@Override
	public User getUsername(String userUsername) {
		return userDao.getUsername(userUsername);
	}

	@Override
	public User getByUserCode(String userCode) {
		return userDao.getByCode(userCode);
	}

}
