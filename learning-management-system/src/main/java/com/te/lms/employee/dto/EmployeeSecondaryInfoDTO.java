package com.te.lms.employee.dto;

import com.te.lms.employee.enums.MaritalStatus;

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
public class EmployeeSecondaryInfoDTO {
	private String panNumber;
	private long aadharNumber;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String passportNumber;
	private MaritalStatus maritalStatus;
}
