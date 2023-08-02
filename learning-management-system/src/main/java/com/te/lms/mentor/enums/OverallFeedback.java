package com.te.lms.mentor.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OverallFeedback {
	EXCELLENT("EXCELLENT"),GOOD("GOOD"),ABOVE_AVERAGE("ABOVE_AVERAGE"),AVERAGE("AVERAGE"),BELOW_AVERAGE("BELOW_AVERAGE");
	
	private final String overallFeedback;
}
