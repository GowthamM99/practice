package com.te.lms.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.lms.exception.EmployeeIdAlreadyExistException;
import com.te.lms.exception.EmployeeIdNotRegisteredException;
import com.te.lms.response.ErrorResponse;

@RestControllerAdvice
public class LMSControllerAdvice {

	@ExceptionHandler(value = { EmployeeIdAlreadyExistException.class,EmployeeIdNotRegisteredException.class})
	public ErrorResponse handler(RuntimeException exe) {
		return ErrorResponse.builder().error(exe.getMessage()).build();
	}
}
