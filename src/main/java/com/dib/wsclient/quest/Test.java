package com.dib.wsclient.quest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Test {
	Test(){}
	
	List<RestResponse> listRestResponse;

	public List<RestResponse> getListRestResponse() {
		return listRestResponse;
	}

	public void setListRestResponse(List<RestResponse> listRestResponse) {
		this.listRestResponse = listRestResponse;
	}
	
	
}
