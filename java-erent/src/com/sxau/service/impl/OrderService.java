package com.sxau.service.impl;

import java.util.List;

import com.sxau.bean.Alipay;
import com.sxau.bean.Order;
import com.sxau.dao.IOrderDao;
import com.sxau.dao.impl.OrderDao;
import com.sxau.service.IOrderService;
import com.sxau.util.Pager;

public class OrderService implements IOrderService{

	IOrderDao orderDao = new OrderDao();
	
	@Override
	public void save(Order order) {
		orderDao.save(order);
	}

	@Override
	public void update(Order order) {
		orderDao.update(order);
	}

	@Override
	public List<Order> list(Order order, Pager pager) {
		return orderDao.list(order, pager);
	}

	@Override
	public Order get(int orderId) {
		return orderDao.get(orderId);
	}

	@Override
	public List<Order> listFromUser(int userId) {
		return orderDao.listFromUser(userId);
	}

	@Override
	public void delete(int orderId) {
		orderDao.delete(orderId);
	}

	@Override
	public Boolean pay(Alipay alipay) {
		return orderDao.pay(alipay);
	}

	@Override
	public List<Order> listSameGoods(Order order) {
		return orderDao.listSameGood(order);
	}

	
}
