package com.spring.pms.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
	@Schema(example = "ram")
	private String username;
	@Schema(example = "ram")
	private String password;
	
		
}
