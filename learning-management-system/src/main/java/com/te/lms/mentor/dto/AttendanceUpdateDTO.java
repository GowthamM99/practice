package com.te.lms.mentor.dto;

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
public class AttendanceUpdateDTO {
	private String employeeId;
	private String employeeName;
	private boolean morning;
	private boolean afternoon;
}
