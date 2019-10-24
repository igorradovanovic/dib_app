package com.dib.controller.dto;

import java.util.Date;

public class BeerDTO {

	// MILAN i dalje koristis ber ... kazem ti, nekako mi to ime promenljive bangavo, mada nije strasno, ajde...
	private Integer berId;
	private String berName;
	private Integer berExtId;
	private String berDescription;
	private Float berTemp;
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
