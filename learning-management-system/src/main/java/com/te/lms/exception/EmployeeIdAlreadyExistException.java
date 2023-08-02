package com.te.lms.exception;

@SuppressWarnings("serial")
public class EmployeeIdAlreadyExistException extends RuntimeException{
	
	public EmployeeIdAlreadyExistException(String message) {
		super(message);
	}

}
