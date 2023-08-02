package com.te.lms.exception;

@SuppressWarnings("serial")
public class CheckOldPasswordEnteredException extends RuntimeException {
	
	public CheckOldPasswordEnteredException(String message) {
		super(message);
	}
}
