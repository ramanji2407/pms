package com.spring.pms.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response200 {
	
	//@Schema( example = "200")
	private int statuscode;
	@Schema(example = "Deleted_detatils_Sucessfully")
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
