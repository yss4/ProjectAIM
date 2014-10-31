package com.cse308.projectaim.beans;

import java.io.Serializable;

public class ResultBean implements Serializable {
	private String requestName;
	private String message = "";
	
	public ResultBean() {
	}

	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getMessage() {
		return message;
	}
	public void setFailureReason(String message) {
		this.message = message;
	}
	
}
