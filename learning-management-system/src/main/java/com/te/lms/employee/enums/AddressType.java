package com.te.lms.employee.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AddressType {
	TEMPORARY("TEMPORARY"), PERMANENT("PERMANENT");

	private final String addressType;
}
