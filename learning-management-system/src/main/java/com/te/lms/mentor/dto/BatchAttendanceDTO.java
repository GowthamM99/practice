package com.te.lms.mentor.dto;

import java.util.List;

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
public class BatchAttendanceDTO {
	private String batchId;
	private List<AttendanceUpdateDTO> updateDTOs;
}
