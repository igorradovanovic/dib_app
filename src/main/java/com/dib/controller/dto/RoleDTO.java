package com.dib.controller.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

public class RoleDTO {
	
	private Long rolId;
	private String rolName;
	private String rolDesc;
	private Date rolSysCreateDate;
	public Long getRolId() {
		return rolId;
	}
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	public String getRolName() {
		return rolName;
	}
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	public String getRolDesc() {
		return rolDesc;
	}
	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}
	public Date getRolSysCreateDate() {
		return rolSysCreateDate;
	}
	public void setRolSysCreateDate(Date rolSysCreateDate) {
		this.rolSysCreateDate = rolSysCreateDate;
	}
	
	

}
