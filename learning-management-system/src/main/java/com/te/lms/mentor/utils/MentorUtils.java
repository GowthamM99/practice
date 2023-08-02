package com.te.lms.mentor.utils;

import java.util.stream.Collectors;

import com.te.lms.mentor.dto.MockRatingDTO;
import com.te.lms.mentor.dto.ScheduledMockDTO;
import com.te.lms.mentor.entity.MockRating;
import com.te.lms.mentor.entity.ScheduledMock;

public class MentorUtils {
	public static MockRating convertDTOToEntity(MockRatingDTO mockRatingDTO) {
		return MockRating.builder()
				.detailedFeedback(mockRatingDTO.getDetailedFeedback())
				.mockType(mockRatingDTO.getMockType())
				.overallFeedback(mockRatingDTO.getOverallFeedback())
				.practicalKnowledge(mockRatingDTO.getPracticalKnowledge())
				.theoreticalKnowledge(mockRatingDTO.getTheoreticalKnowledge())
				.technology(mockRatingDTO.getTechnology())
				.build();
	}
	
	public static MockRatingDTO convertEntityToDTO(MockRating mockRating) {
		return MockRatingDTO.builder()
				.detailedFeedback(mockRating.getDetailedFeedback())
				.mentor(mockRating.getMentor().getMentorName())
				.mockType(mockRating.getMockType())
				.overallFeedback(mockRating.getOverallFeedback())
				.practicalKnowledge(mockRating.getPracticalKnowledge())
				.theoreticalKnowledge(mockRating.getTheoreticalKnowledge())
				.technology(mockRating.getTechnology())
				.build();
	}
	
	public static ScheduledMockDTO convertEntityToDTO(ScheduledMock mock) {
		return ScheduledMockDTO.builder()
				.batch(mock.getBatch().getBatchName())
				.dateAndTime(mock.getDateAndTime())
				.mentors(mock.getMentors().stream().map(m->m.getMentorName()).collect(Collectors.toList()))
				.mockNumber(mock.getMockNumber())
				.technology(mock.getTechnology())
				.build();
	}
}
