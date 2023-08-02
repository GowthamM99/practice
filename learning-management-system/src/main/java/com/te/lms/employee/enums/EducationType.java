package com.te.lms.employee.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EducationType {
	SSLC("SSLC"), DIPLOMA("DIPLOMA"), UG("UG"), PG("PG"), PHD("PND");

	private final String educationType;
}
