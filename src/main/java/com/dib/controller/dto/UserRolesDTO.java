package com.dib.controller.dto;


public class UserRolesDTO {
	
	private Long urrId;
	private RoleDTO role;
	private UserDTO user;
	public Long getUrrId() {
		return urrId;
	}
	public void setUrrId(Long urrId) {
		this.urrId = urrId;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	

	
	

}
