package com.te.lms.mentor.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Technology {
	MEAN("MEAN"),MERN("MERN"),JAVA("JAVA");
	
	private final String technologyType;
}
