package com.te.lms.exception;

@SuppressWarnings("serial")
public class EmployeeIdNotRegisteredException extends RuntimeException{
	
	public EmployeeIdNotRegisteredException(String message) {
		super(message);
	}

}
