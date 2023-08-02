package com.te.lms.exception;

@SuppressWarnings("serial")
public class UsernameNotRegisteredException extends RuntimeException {
	
	 public UsernameNotRegisteredException(String message) {
		 super(message);
	 }
}
