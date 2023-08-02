package com.te.lms.admin.dto;

import java.time.LocalDate;
import java.util.List;

import com.te.lms.admin.enums.BatchStatus;

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
public class BatchDTO {
	private String batchId;
	private String batchName;
	private List<String> mentorNames;
	private List<String> technologies;
	private LocalDate startDate;
	private LocalDate endDate;
	private BatchStatus batchStatus;
}
