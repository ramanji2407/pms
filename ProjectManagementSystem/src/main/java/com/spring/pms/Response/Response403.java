package com.spring.pms.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response403 {
//	@Schema( example = "403")
	private int statuscode;
	@Schema(example = "Your_Not_Authorized")
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
