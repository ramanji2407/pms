package com.spring.pms.Exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionalHandler {
	@ExceptionHandler(DetailsNotFoundException.class)
	 public ResponseEntity<?> handleStudentNotFoundRxception(DetailsNotFoundException detailsNotFoundException)
	 {
		DetailNotfound notfound=new DetailNotfound();
		notfound.setMessage(detailsNotFoundException.getMessage());
		notfound.setTimestamp(new Date());
		notfound.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(notfound,HttpStatus.NOT_FOUND);
	 }
	@ExceptionHandler(UserAlreadyExistException.class)
	 public ResponseEntity<?> handleUserAlreadyExist(UserAlreadyExistException alreadyExist)
	 {
		UserAlreadexist notfound=new UserAlreadexist();
		notfound.setMessage(alreadyExist.getMessage());
		notfound.setTimestamp(new Date());
		notfound.setStatus(HttpStatus.CONFLICT);
		return new ResponseEntity<>(notfound,HttpStatus.CONFLICT);
	 }
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
	Map<String, String>response=new HashMap<String, String>();	
	ex.getBindingResult().getAllErrors().forEach(
			(error)->{
				String fieldname=((FieldError)error).getField();
				String message=error.getDefaultMessage();
				response.put(fieldname, message);
			});
		return new  ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BadRequestException.class)
	 public ResponseEntity<?> handleBadrequeste(BadRequestException alreadyExist)
	 {
		BadRequest notfound=new BadRequest();
		notfound.setMessage(alreadyExist.getMessage());
		notfound.setTimestamp(new Date());
		notfound.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(notfound,HttpStatus.BAD_REQUEST);
	 }


}
