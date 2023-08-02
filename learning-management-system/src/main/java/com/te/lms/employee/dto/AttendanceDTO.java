package com.te.lms.employee.dto;

import java.time.LocalDate;

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
public class AttendanceDTO {
	private LocalDate attendanceDate;
	private boolean morning;
	private boolean afternoon;
}
