package com.te.lms.mentor.dto;

import com.te.lms.mentor.enums.MockType;
import com.te.lms.mentor.enums.OverallFeedback;
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
public class MockRatingDTO {
	private MockType mockType;
	private String mentor;
	private Technology technology;
	private int practicalKnowledge;
	private int theoreticalKnowledge;
	private OverallFeedback overallFeedback;
	private String detailedFeedback;
}
