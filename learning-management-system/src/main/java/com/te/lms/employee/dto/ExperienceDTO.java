package com.te.lms.employee.dto;

import java.time.LocalDate;

import com.te.lms.employee.enums.Designation;

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
public class ExperienceDTO {
	private String companyName;
	private double yearOfExperience;
	private LocalDate dateOfJoining;
	private LocalDate dateOfRelieving;
	private Designation designation;
	private String location;
}
