package com.te.lms.mentor.service;

import com.te.lms.mentor.dto.MockRatingDTO;

public interface MockRatingService {

	int createMockRating(String employeeId, MockRatingDTO mockRatingDTO);

}
