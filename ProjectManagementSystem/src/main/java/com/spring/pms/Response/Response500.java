package com.spring.pms.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response500 {
	//@Schema( example = "500")
	private int statuscode;
	@Schema(example = "Internal_Server_Error_Due_To_Wrong_Configurations")
	private String message;
//	public int getstatuscode() {
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
