package com.spring.pms.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response401 {
	//@Schema( example = "401")
	private int statuscode;
	@Schema(example = "Invalid_Username_Or_Password")
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
