package com.te.lms.employee.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccountType {
	SAVINGS("SAVINGS"),CURRENT("CURRENT");
	
	private final String accountType;
}
