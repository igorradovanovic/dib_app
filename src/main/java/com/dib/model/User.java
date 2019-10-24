package com.dib.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "usr_name")
	private String userName;
	
	@Column(name = "usr_password")
	private String userPassword;
	
	@Column(name = "usr_enabled")
	private Boolean userEnabled;
	
	@Column(name = "usr_email")
	private String userEmail;
	
	@Column(name = "usr_token_exp")
	private Date userTokenExp;
	
	@Column(name = "usr_exp_date")
	private Date userExpDate;
	
	@Column(name = "usr_reset_token")
	private String userResetToken;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usr_sys_create_date")
	private Date userSysCreateDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	


	public Boolean getUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(Boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	
	
}

