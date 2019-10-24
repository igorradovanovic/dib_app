package com.dib.controller.dto.system;

import java.util.List;

public class ResponseWrapper {
	
	private Object data;

	private String message;

	public ResponseWrapper() {

	}

	public ResponseWrapper(Object data) {
		this.data = data;
	}
	
	public ResponseWrapper(String message) {
		this.message = message;
	}
	
	public ResponseWrapper(Object data, String message) {
		this.data = data;
		this.message = message;
	}

	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}
