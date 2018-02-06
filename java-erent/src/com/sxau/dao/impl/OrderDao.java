package com.sxau.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sxau.bean.Alipay;
import com.sxau.bean.Order;
import com.sxau.dao.IOrderDao;
import com.sxau.util.ConfigProperties;
import com.sxau.util.JDBCUtil;
import com.sxau.util.Pager;
import com.sxau.util.StrUtil;
import com.sxau.util.webservice.AlipayClient;

public class OrderDao implements IOrderDao{

	JDBCUtil util = new JDBCUtil();
	
	/*order_id           number               not null,
	   order_code         varchar2(50),
	   order_date         varchar2(30),
	   user_id            number,
	   order_status       INTEGER,
	   money_status       INTEGER,
	   order_price        FLOAT(10),
	   pay_type           INTEGER,
	  */
	@Override
	public void save(Order order) {
		String sql = "insert into t_order (order_id,order_code,order_date,user_id,order_status,order_price,order_addr,machine_num,machine_id) values(seq_order.nextval,?,?,?,?,?,?,?,?)";
		util.execute(sql, new Object[]{order.getOrderCode(),order.getOrderDate(),order.getUserId(),order.getOrderStatus(),order.getOrderPrice(),order.getOrderAddr(),order.getMachineNum(),order.getMachineId()});
	}

	@Override
	public void update(Order order) {
		String sql = "update t_order set ";
		
		List<Object> parameters = new ArrayList<Object>();
		
		if(StrUtil.isNotEmpty(order.getOrderCode())){
			sql += " order_code = ?,";
			parameters.add(order.getOrderCode());
		}
		if(StrUtil.isNotEmpty(order.getOrderDate())){
			sql += " order_date = ?,";
			parameters.add(order.getOrderDate());
		}
		if(order.getUserId() != 0){
			sql += " user_id = ?,";
			parameters.add(order.getUserId());
		}
		if(order.getOrderStatus() != null && !"".equals(order.getOrderStatus())){
			sql += " order_status = ?,";
			parameters.add(order.getOrderStatus());
		}
		if(order.getOrderPrice() != 0){
			sql += " order_price = ?,";
			parameters.add(order.getOrderPrice());
		}
		if(order.getOrderAddr() != null){
			sql += " order_addr = ?,";
			parameters.add(order.getOrderAddr());
		}
		if(order.getMachineNum() != 0){
			sql += " machine_num = ?,";
			parameters.add(order.getMachineNum());
		}
		if(order.getMachineId() != 0){
			sql += " machine_id = ?,";
			parameters.add(order.getMachineId());
		}
		
		 sql = sql.substring(0,sql.length() - 1);
		 
		 sql += " where order_id=?";
		 System.out.println("update 的 sql 语句：" + sql);
		 parameters.add(order.getOrderId());
		 
		util.execute(sql, parameters.toArray());
	}

	@Override
	public List<Order> list(Order order, Pager pager) {
		String sql = "select o.*,u.user_name as \"user.user_name\",u.user_phone as \"user.user_phone\" " +
				"from t_order o left join t_user u on o.user_id = u.user_id where 1=1 ";
		List<Object> parameters = new ArrayList<Object>();
		if(order != null){
			int machineId = order.getMachineId();
			if(machineId != 0){
				sql += " and o.machine_id = ? ";
				parameters.add(machineId);
			}
			int userId = order.getUserId();
			if(userId != 0){
				sql += " and o.user_id = ? ";
				parameters.add(userId);
			}
			String orderAddr = order.getOrderAddr();
			if(StrUtil.isNotEmpty(orderAddr)){
				sql += " and o.order_addr = ? ";
				parameters.add(orderAddr);
			}
			Integer orderStatus = order.getOrderStatus();
			if(orderStatus != null && !"".equals(orderStatus)){
				sql += " and o.order_status = ?";
			}
		}
		
		sql += " order by order_date";
		return util.executeQuery(Order.class, sql, parameters.toArray(), pager);
	}

	@Override
	public Order get(int orderId) {
		String sql = "select * from t_order where order_id=?";
		List<Order> list = util.executeQuery(Order.class, sql, new Object[]{orderId});
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Order> listFromUser(int userId) {
		String sql = "select t.*,m.machine_price as \"machine.machine_price\",m.machine_pic as \"machine.machine_pic\",m.machine_name as \"machine.machine_name\" " +
				"from t_order t left join machine m on t.machine_id = m.machine_id where t.order_status = 1 and t.user_id = ?";
		return util.executeQuery(Order.class, sql, new Object[]{userId});
	}

	@Override
	public void delete(int orderId) {
		String sql = "delete from t_order where order_id = ?";
		util.execute(sql, new Object[]{orderId});
	}

	@Override
	public Boolean pay(Alipay alipay) {
		alipay.setReceiverNo(ConfigProperties.getValue("receiverNo"));
		Map<String,Object> result = AlipayClient.sendPost(ConfigProperties.getValue("alipayURL"), alipay);
		if("success".equals(result.get("code"))){
			return true;
		}
		return false;
	}

	@Override
	public List<Order> listSameGood(Order order) {
		String sql = "select * from t_order where user_id = ? and machine_id = ? and order_addr = ?";
		
		return util.executeQuery(Order.class, sql, new Object[]{order.getUserId(),order.getMachineId(),order.getOrderAddr()});
	}

	
	
}
