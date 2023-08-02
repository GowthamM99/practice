package com.te.lms.employee.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmployeeStatus {
	ACTIVE("ACTIVE"),TERMINATED("TERMINATED"),ABSCONDED("ABSCONDED");
	
	private final String employeeStatus;
}
