package com.spring.pms.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequest {
	
	private String message;
    private Date timestamp;
   private HttpStatus status;

}
