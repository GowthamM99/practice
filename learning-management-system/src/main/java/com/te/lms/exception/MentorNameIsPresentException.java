package com.te.lms.exception;

@SuppressWarnings("serial")
public class MentorNameIsPresentException extends RuntimeException {
	
	public MentorNameIsPresentException(String message) {
		super(message);
	}
}
