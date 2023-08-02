package com.te.lms.mentor.service;

import org.springframework.stereotype.Service;

import com.te.lms.admin.entity.Mentor;
import com.te.lms.employee.entity.Employee;
import com.te.lms.employee.repository.EmployeeRepository;
import com.te.lms.mentor.dto.MockRatingDTO;
import com.te.lms.mentor.entity.MockRating;
import com.te.lms.mentor.repository.MentorRepository;
import com.te.lms.mentor.repository.MockRatingRepository;
import com.te.lms.mentor.utils.MentorUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MockRatingServiceImpl implements MockRatingService{
	
	private final EmployeeRepository employeeRepository;
	private final MockRatingRepository ratingRepository;
	private final MentorRepository mentorRepository;
	
	@Override
	public int createMockRating(String employeeId, MockRatingDTO mockRatingDTO) {
		Employee employee = employeeRepository.findById(employeeId).get();
		Mentor mentor = mentorRepository.findByMentorName(mockRatingDTO.getMentor()).get();
		MockRating mockRating = MentorUtils.convertDTOToEntity(mockRatingDTO);
		mockRating.setEmployee(employee);
		mockRating.setMentor(mentor);
		employee.getMockRatings().add(mockRating);
		mentor.getRatings().add(mockRating);
		ratingRepository.save(mockRating);
		return mockRating.getMockRatingId();
	}

}
