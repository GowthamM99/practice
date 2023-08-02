package com.te.lms.admin.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BatchStatus {
	In_Progress("In progress"),Completed("completed"),To_Be_Started("To be started");
	
	private final String statusType;
}
