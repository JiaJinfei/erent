package com.sxau.service;

import java.util.List;

import com.sxau.bean.Alipay;
import com.sxau.bean.Order;
import com.sxau.util.Pager;

public interface IOrderService {
	
	public void save(Order order);
	
	public void update(Order order);
	
	public List<Order> list(Order order,Pager pager);
	
	public Order get(int orderId);
	
	public List<Order> listFromUser(int userId);
	
	public void delete(int orderId);
	
	public Boolean pay(Alipay alipay);
	
	public List<Order> listSameGoods(Order order);
}
