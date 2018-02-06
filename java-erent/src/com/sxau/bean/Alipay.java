package com.sxau.bean;

/**
 * 支付宝类
 * @author Jaler
 *
 */
public class Alipay {
	
	private String payerNo;//付款方账号
	
	private String receiverNo;//收款方账号
	
	private double money;//付款金额
	
	private String payerPwd;

	public String getPayerNo() {
		return payerNo;
	}

	public void setPayerNo(String payerNo) {
		this.payerNo = payerNo;
	}

	public String getReceiverNo() {
		return receiverNo;
	}

	public void setReceiverNo(String receiverNo) {
		this.receiverNo = receiverNo;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getPayerPwd() {
		return payerPwd;
	}

	public void setPayerPwd(String payerPwd) {
		this.payerPwd = payerPwd;
	}
	
	
}
