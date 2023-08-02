package com.te.lms.admin.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.te.lms.admin.dto.BatchDTO;
import com.te.lms.admin.dto.MentorDTO;
import com.te.lms.admin.dto.RegistrationRequestDTO;
import com.te.lms.admin.entity.Batch;
import com.te.lms.admin.entity.Mentor;
import com.te.lms.admin.enums.BatchStatus;
import com.te.lms.employee.entity.Employee;
import com.te.lms.mentor.entity.MockRating;
import com.te.lms.mentor.entity.ScheduledMock;
import com.te.lms.mentor.enums.Experience;

public class AdminUtils {
	
	public static Batch convertDTOToEntity(BatchDTO batchDTO) {
		return Batch.builder()
				.batchId(batchDTO.getBatchId())
				.batchName(batchDTO.getBatchName())
				.endDate(batchDTO.getEndDate())
				.startDate(batchDTO.getStartDate())
				.status(BatchStatus.To_Be_Started)
				.mentors(new ArrayList<Mentor>())
				.mocks(new ArrayList<ScheduledMock>())
				.employees(new ArrayList<Employee>())
				.build();
	}
	
	public static Mentor convertDTOToEntity(MentorDTO mentorDTO) {
		return Mentor.builder()
				.employeeId(mentorDTO.getEmployeeId())
				.emailId(mentorDTO.getEmailId())
				.mentorName(mentorDTO.getMentorName())
				.batches(new ArrayList<Batch>())
				.mocks(new ArrayList<ScheduledMock>())
				.ratings(new ArrayList<MockRating>())
				.build();
	}
	
	public static BatchDTO covertEntityToDTO(Batch batch) {
		return BatchDTO.builder()
				.batchId(batch.getBatchId())
				.batchName(batch.getBatchName())
				.batchStatus(batch.getStatus())
				.endDate(batch.getEndDate())
				.mentorNames(batch.getMentors().stream().map(b->b.getMentorName()).collect(Collectors.toList()))
				.startDate(batch.getStartDate())
				.technologies(batch.getTechnologies().stream().map(t->t.getTechnologyName()).collect(Collectors.toList()))
				.build();
	}
	
	public static RegistrationRequestDTO convertEntityToDTO(Employee employee) {
		double sum = employee.getExperiences().stream().map(e->e.getYearOfExperience()).mapToDouble(Double::doubleValue).sum();
		int yop=employee.getEducationDetails().stream().map(e->e.getYearOfPassing()).max(Integer::compare).get();
		 RegistrationRequestDTO requestDTO = RegistrationRequestDTO.builder()
				.employeeId(employee.getEmployeeId())
				.employeeName(employee.getEmployeeName())
				.yearOfPassing(yop)
				.percentage(employee.getEducationDetails().stream().filter(e->e.getYearOfPassing()==yop).collect(Collectors.toList()).get(0).getPercentage())
				.contactNumber(employee.getContacts().get(0).getContactNumber())
				.build();
		 if(sum>=4) {
			 requestDTO.setExperience(Experience.FourYearsPlus);
		 }
		 else if(sum>=3) {
			 requestDTO.setExperience(Experience.ThreeYears);
		 }
		 else if(sum>=2) {
			 requestDTO.setExperience(Experience.TwoYears);
		 }
		 else if(sum>=1) {
			 requestDTO.setExperience(Experience.OneYear);
		 }
		 else {
			 requestDTO.setExperience(Experience.Fresher);
		 }
		 return requestDTO;
	}
	
	public static MentorDTO covertEntityToDTO(Mentor mentor) {
		return MentorDTO.builder()
				.emailId(mentor.getEmailId())
				.employeeId(mentor.getEmployeeId())
				.mentorName(mentor.getMentorName())
				.technologies(mentor.getTechnologies().stream().map(t->t.getTechnologyName()).collect(Collectors.toList()))
				.build();
	}
}
