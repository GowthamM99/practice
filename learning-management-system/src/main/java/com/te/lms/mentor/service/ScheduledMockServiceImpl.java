package com.te.lms.mentor.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.te.lms.admin.entity.Batch;
import com.te.lms.admin.entity.Mentor;
import com.te.lms.admin.repository.BatchRepository;
import com.te.lms.email.EmailDetails;
import com.te.lms.email.service.EmailService;
import com.te.lms.mentor.dto.ScheduledMockDTO;
import com.te.lms.mentor.entity.ScheduledMock;
import com.te.lms.mentor.repository.MentorRepository;
import com.te.lms.mentor.repository.ScheduledMockRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScheduledMockServiceImpl implements ScheduledMockService {

	private final ScheduledMockRepository mockRepository;
	private final BatchRepository batchRepository;
	private final MentorRepository mentorRepository;
	private final EmailService emailService;

	@Override
	public int scheduleMockToBatch(ScheduledMockDTO scheduledMockDTO) {
		ScheduledMock scheduledMock = ScheduledMock.builder().dateAndTime(scheduledMockDTO.getDateAndTime())
				.mockNumber(scheduledMockDTO.getMockNumber()).technology(scheduledMockDTO.getTechnology()).build();
		Batch batch = batchRepository.findById(scheduledMockDTO.getBatch()).get();
		batch.getMocks().add(scheduledMock);
		scheduledMock.setBatch(batch);
		Function<String, Mentor> function = s -> {
			Mentor mentor = mentorRepository.findByMentorName(s).get();
			mentor.getMocks().add(scheduledMock);
			return mentor;
		};
		List<Mentor> mentors = scheduledMockDTO.getMentors().stream().map(function).collect(Collectors.toList());
		scheduledMock.setMentors(mentors);
		mockRepository.save(scheduledMock);
		batch.getEmployees().stream().forEach(e -> {
			Thread thread = new Thread(() -> emailService.sendEmail(EmailDetails.builder().subject("Mock Scheduled")
					.body("Hii " + e.getEmployeeName() + " this mail is regarding the mock scheduled for technology "
							+ scheduledMockDTO.getTechnology() + " on "
							+ scheduledMockDTO.getDateAndTime().toLocalDate() + " at "
							+ scheduledMockDTO.getDateAndTime().toLocalTime())
					.receptientEmail(e.getEmailId()).build()));
			thread.start();
		});
		return scheduledMock.getMockId();
	}
}
