package com.te.lms.employee.dto;

import com.te.lms.employee.enums.EducationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EducationDTO {
	private EducationType educationType;
	private int yearOfPassing;
	private double percentage;
	private String universityName;
	private String instituteName;
	private String specialization;
	private String state;
}
