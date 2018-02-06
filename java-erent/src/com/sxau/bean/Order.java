package com.sxau.bean;

public class Order {

	/*order_id           number               not null,
	   order_code         varchar2(50),
	   order_date         varchar2(30),
	   user_id            number,
	   order_status       INTEGER,
	   money_status       INTEGER,
	   order_price        number,
	   pay_type           INTEGER,
	   user_phone         varchar2(20),
	   user_addr   */
	
	private int orderId;
	private String orderCode;
	private String orderDate;
	
	private int userId;
	private User user;
	
	private int machineNum;
	private int machineId;
	private Machine machine;
	
	private String orderAddr;
	
	private Integer orderStatus;
	private double orderPrice;
	
	public String getOrderAddr() {
		return orderAddr;
	}
	public void setOrderAddr(String orderAddr) {
		this.orderAddr = orderAddr;
	}
	public int getMachineNum() {
		return machineNum;
	}
	public void setMachineNum(int machineNum) {
		this.machineNum = machineNum;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	public String getOrderStatusText() {
		return orderStatus ==0 ? "已完成订单" : "未完成订单";
	}
	
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	
}
