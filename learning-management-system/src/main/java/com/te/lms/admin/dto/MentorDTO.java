package com.te.lms.admin.dto;

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
public class MentorDTO {
	private String mentorName;
	private String emailId;
	private String employeeId;
	private List<String> technologies;
}
