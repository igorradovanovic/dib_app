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
import org.hibernate.annotations.Type;

@Entity
@Table(name = "beer")
public class Beer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ber_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer berId;

	@Column(name = "ber_name")
	private String berName;

	@Column(name = "ber_ext_id")
	private Integer berExtId;

	@Column(name = "ber_description")
	@Type(type = "text")
	private String berDescription;

	@Column(name = "ber_temp")
	private Float berTemp;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ber_sys_create_date")
	private Date berSysCreateDate;

	public Integer getBerId() {
		return berId;
	}

	public void setBerId(Integer berId) {
		this.berId = berId;
	}

	public String getBerName() {
		return berName;
	}

	public void setBerName(String berName) {
		this.berName = berName;
	}

	public Integer getBerExtId() {
		return berExtId;
	}

	public void setBerExtId(Integer berExtId) {
		this.berExtId = berExtId;
	}

	public String getBerDescription() {
		return berDescription;
	}

	public void setBerDescription(String berDescription) {
		this.berDescription = berDescription;
	}

	public Float getBerTemp() {
		return berTemp;
	}

	public void setBerTemp(Float berTemp) {
		this.berTemp = berTemp;
	}

	public Date getBerSysCreateDate() {
		return berSysCreateDate;
	}

	public void setBerSysCreateDate(Date berSysCreateDate) {
		this.berSysCreateDate = berSysCreateDate;
	}

}
