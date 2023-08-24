package com.spring.pms.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response400 {
//	@Schema( example = "400")
	private int statuscode;
	@Schema(example = "Age_Cannot_Be_In_String")

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
