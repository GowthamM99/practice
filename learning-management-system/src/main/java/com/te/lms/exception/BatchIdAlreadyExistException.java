package com.te.lms.exception;

@SuppressWarnings("serial")
public class BatchIdAlreadyExistException extends RuntimeException{
	
	public BatchIdAlreadyExistException(String message) {
		super(message);
	}
}
