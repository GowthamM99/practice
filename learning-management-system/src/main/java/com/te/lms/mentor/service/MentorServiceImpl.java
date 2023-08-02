package com.te.lms.mentor.service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.te.lms.admin.dto.BatchDTO;
import com.te.lms.admin.dto.RegistrationRequestDTO;
import com.te.lms.admin.entity.Batch;
import com.te.lms.admin.entity.Mentor;
import com.te.lms.admin.repository.BatchRepository;
import com.te.lms.admin.utils.AdminUtils;
import com.te.lms.employee.entity.Attendance;
import com.te.lms.employee.entity.Education;
import com.te.lms.employee.entity.Employee;
import com.te.lms.employee.enums.EducationType;
import com.te.lms.employee.enums.Gender;
import com.te.lms.employee.utils.EmployeeUtils;
import com.te.lms.mentor.dto.AttendanceUpdateDTO;
import com.te.lms.mentor.dto.BatchAttendanceDTO;
import com.te.lms.mentor.dto.BatchDashboardDTO;
import com.te.lms.mentor.dto.EmployeeBatchDTO;
import com.te.lms.mentor.enums.Experience;
import com.te.lms.mentor.enums.OverallFeedback;
import com.te.lms.mentor.repository.MentorRepository;
import com.te.lms.mentor.utils.MentorUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MentorServiceImpl implements MentorService {

	private final BatchRepository batchRepository;
	private final MentorRepository mentorRepository;

	@Override
	public List<BatchDTO> getBatches(String mentorName) {
		Mentor mentor = mentorRepository.findByMentorName(mentorName).get();
		return mentor.getBatches().stream().map(b -> AdminUtils.covertEntityToDTO(b)).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeBatchDTO> getEmployeesFromBatch(String batchId) {
		Batch batch = batchRepository.findById(batchId).get();
		Function<Employee, EmployeeBatchDTO> function = e -> {
			return EmployeeBatchDTO.builder()
					.attendanceDTOs(e.getAttendances().stream().map(a -> EmployeeUtils.covertEntityToDTO(a))
							.collect(Collectors.toList()))
					.employeeId(e.getEmployeeId()).employeeName(e.getEmployeeName())
					.employeeStatus(e.getEmployeeStatus())
					.mockRatingDTOs(e.getMockRatings().stream().map(a -> MentorUtils.convertEntityToDTO(a))
							.collect(Collectors.toList()))
					.mockDTOs(batch.getMocks().stream().map(m -> MentorUtils.convertEntityToDTO(m))
							.collect(Collectors.toList()))
					.build();
		};

		return batch.getEmployees().stream().map(function).collect(Collectors.toList());
	}

	@Override
	public List<String> getBatcheNames(String mentorName) {
		Mentor mentor = mentorRepository.findByMentorName(mentorName).get();
		return mentor.getBatches().stream().map(b -> b.getBatchName()).collect(Collectors.toList());
	}

	@Override
	public BatchDashboardDTO getBatchDashboard(String batchName) {
		Batch batch = batchRepository.findByBatchName(batchName);
		List<RegistrationRequestDTO> list = batch.getEmployees().stream().map(e ->AdminUtils.convertEntityToDTO(e)).collect(Collectors.toList());
		List<Integer> yearOfPassing = list.stream().map(l->l.getYearOfPassing()).collect(Collectors.toList());
		List<Experience> experience = list.stream().map(l->l.getExperience()).collect(Collectors.toList());
		List<Gender> genderList = batch.getEmployees().stream().map(e->e.getGender()).collect(Collectors.toList());
		List<OverallFeedback> batchPerformance = batch.getEmployees().stream().flatMap(e->e.getMockRatings().stream().map(m->m.getOverallFeedback())).collect(Collectors.toList());
		Predicate<Education> predicate =d->batch.getEmployees().stream().flatMap(a->a.getEducationDetails().stream().map(s->s.getYearOfPassing())).max(Integer::compare).get()==d.getYearOfPassing();
		List<EducationType> employeeDegree = batch.getEmployees().stream().flatMap(e->e.getEducationDetails().stream().filter(predicate).map(s->s.getEducationType())).collect(Collectors.toList());
		return BatchDashboardDTO.builder()
				.employeeDegree(employeeDegree)
				.experience(experience)
				.genderList(genderList)
				.yearOfPassing(yearOfPassing)
				.batchPerformance(batchPerformance)
				.build();
	}

	@Override
	public List<AttendanceUpdateDTO> getBatchAttendance(String batchId) {
		Batch batch = batchRepository.findById(batchId).get();
		batch.getEmployees().stream().forEach(e->e.getAttendances().add(Attendance.builder().build()));
		batchRepository.save(batch);
		Function<Employee, AttendanceUpdateDTO> function=e->{
			return AttendanceUpdateDTO.builder()
					.employeeId(e.getEmployeeId())
					.employeeName(e.getEmployeeName())
					.build();
		};
		return batch.getEmployees().stream().map(function).collect(Collectors.toList());
	}

	@Override
	public void updateBatchAttendance(BatchAttendanceDTO attendanceDTO) {
		Batch batch = batchRepository.findById(attendanceDTO.getBatchId()).get();
		batch.getEmployees().forEach(e -> {
			AttendanceUpdateDTO updateDTO = attendanceDTO.getUpdateDTOs().stream()
					.filter(a -> a.getEmployeeId().equals(e.getEmployeeId())).toList().get(0);
			 Attendance attendance = e.getAttendances().get(e.getAttendances().size()-1);
			 attendance.setMorning(updateDTO.isMorning());
			 attendance.setAfternoon(updateDTO.isAfternoon());
		});
		batchRepository.save(batch);
	}

}
