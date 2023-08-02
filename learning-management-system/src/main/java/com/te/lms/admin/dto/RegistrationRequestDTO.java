package com.te.lms.admin.dto;

import com.te.lms.mentor.enums.Experience;

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
public class RegistrationRequestDTO {
	private String employeeId;
	private String employeeName;
	private int yearOfPassing;
	private double percentage;
	private Experience experience;
	private long contactNumber;
}
