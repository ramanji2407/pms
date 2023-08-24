package com.spring.pms.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response204 {
	//@Schema(example = "204")
	private int statuscode;
	//@Schema(example = "Request_Sucess_But_No_Content_Available")
	private String message;
//	public int getStatuscode() {
//		return statuscode;
//	}
//	public void setStatuscode(int statuscode) {
//		this.statuscode = statuscode;
//	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
