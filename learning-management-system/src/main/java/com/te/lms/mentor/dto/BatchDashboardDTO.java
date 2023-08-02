package com.te.lms.mentor.dto;

import java.util.List;

import com.te.lms.employee.enums.EducationType;
import com.te.lms.employee.enums.Gender;
import com.te.lms.mentor.enums.Experience;
import com.te.lms.mentor.enums.OverallFeedback;

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
public class BatchDashboardDTO {
	private List<Gender> genderList;
	private List<Experience> experience;	
	private List<Integer> yearOfPassing;
	private List<EducationType> employeeDegree;
	private List<OverallFeedback> batchPerformance;
	
}
