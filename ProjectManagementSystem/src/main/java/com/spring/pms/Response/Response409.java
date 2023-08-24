package com.spring.pms.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response409 {
	//@Schema( example = "409")

	private int statuscode;
	@Schema( example = "Id_Was_Trying_To_Add_Already_Present_In_Database")

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
