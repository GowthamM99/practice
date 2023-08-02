package com.te.lms.mentor.dto;

import java.util.List;

import com.te.lms.employee.dto.AttendanceDTO;
import com.te.lms.employee.enums.EmployeeStatus;

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
public class EmployeeBatchDTO {
	private String employeeId;
	private String employeeName;
	private List<ScheduledMockDTO> mockDTOs;
	private List<MockRatingDTO> mockRatingDTOs;
	private List<AttendanceDTO> attendanceDTOs;
	private EmployeeStatus employeeStatus;
}
