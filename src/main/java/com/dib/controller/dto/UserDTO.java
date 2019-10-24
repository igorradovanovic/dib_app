package com.dib.controller.dto;

import java.util.Date;

public class UserDTO {

	private Long userId;
	private String userName;
	private String userPassword;
	private Boolean userEnabled;
	private String userEmail;
	private Date userTokenExp;
	private Date userExpDate;
	private String userResetToken;
	private Date userSysCreateDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserTokenExp() {
		return userTokenExp;
	}

	public void setUserTokenExp(Date userTokenExp) {
		this.userTokenExp = userTokenExp;
	}

	public Date getUserExpDate() {
		return userExpDate;
	}

	public void setUserExpDate(Date userExpDate) {
		this.userExpDate = userExpDate;
	}

	public String getUserResetToken() {
		return userResetToken;
	}

	public void setUserResetToken(String userResetToken) {
		this.userResetToken = userResetToken;
	}

	public Date getUserSysCreateDate() {
		return userSysCreateDate;
	}

	public void setUserSysCreateDate(Date userSysCreateDate) {
		this.userSysCreateDate = userSysCreateDate;
	}

	public Boolean getUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(Boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

}
