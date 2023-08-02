package com.te.lms.employee.dto;

import java.time.LocalDate;

import com.te.lms.employee.enums.Designation;
import com.te.lms.employee.enums.EmployeeStatus;
import com.te.lms.employee.enums.Gender;

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
public class EmployeeDTO {
	private String employeeId;
	private String employeeName;
	private LocalDate dateOfJoining;
	private double salary;
	private LocalDate dateOfBirth;
	private String emailId;
	private String bloodGroup;
	private Designation designation;
	private Gender gender;
	private String nationality;
	private EmployeeStatus employeeStatus;
}
