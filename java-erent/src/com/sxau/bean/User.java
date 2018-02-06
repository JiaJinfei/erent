package com.sxau.bean;

public class User {
	
	private int userId; //用户id
	private String userCode;
	private String userUsername; //用户登录的用户名
	private String userPwd; //用户登录的密码
	private String userName; //用户的姓名
	private String userPhone; //用户的电话号码
	
	private Integer userType; // 用户的类型   0 员工 1 用户
	private Integer userStatus; //用户的状态 0 上线 1 下线

	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public String getUserStatusText() {
		return userStatus == 0 ? "在线":"下线";
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}
