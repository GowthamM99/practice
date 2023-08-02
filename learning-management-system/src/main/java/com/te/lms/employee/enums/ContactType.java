package com.te.lms.employee.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContactType {
	PERSONAL("PERSONAL"),OFFICE("OFFICE");
	
	private final String contactType;
}
