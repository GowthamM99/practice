package com.te.lms.mentor.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MockType {
	MOCK("MOCK"),REMOCK("REMOCK");
	
	private final String mockType;
}
