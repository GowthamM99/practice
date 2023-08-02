package com.te.lms.mentor.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Experience {
	Fresher("Fresher"),OneYear("1 Yr"),TwoYears("2 Yrs"),ThreeYears("3 Yrs"),FourYearsPlus("4 Yrs");
	
	private final String experienceType;
}
