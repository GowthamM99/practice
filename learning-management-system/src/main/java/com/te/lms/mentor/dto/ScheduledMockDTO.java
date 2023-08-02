package com.te.lms.mentor.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.te.lms.mentor.enums.Technology;

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
public class ScheduledMockDTO {
	private int mockNumber;
	private Technology technology;
	private String batch;
	private List<String> mentors;
	private LocalDateTime dateAndTime;
}
