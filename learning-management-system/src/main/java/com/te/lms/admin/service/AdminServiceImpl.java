package com.te.lms.admin.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.lms.admin.dto.BatchDTO;
import com.te.lms.admin.dto.MentorDTO;
import com.te.lms.admin.dto.RegistrationRequestDTO;
import com.te.lms.admin.dto.RejectionReasonDTO;
import com.te.lms.admin.entity.Batch;
import com.te.lms.admin.entity.Mentor;
import com.te.lms.admin.entity.Technology;
import com.te.lms.admin.enums.BatchStatus;
import com.te.lms.admin.repository.BatchRepository;
import com.te.lms.admin.repository.TechnologyRepository;
import com.te.lms.admin.utils.AdminUtils;
import com.te.lms.email.EmailDetails;
import com.te.lms.email.service.EmailService;
import com.te.lms.employee.entity.Employee;
import com.te.lms.employee.entity.RegistrationRequest;
import com.te.lms.employee.repository.EmployeeRepository;
import com.te.lms.employee.repository.RegistrationRequestRepository;
import com.te.lms.exception.BatchIdAlreadyExistException;
import com.te.lms.exception.EmployeeIdNotRegisteredException;
import com.te.lms.exception.MentorNameIsPresentException;
import com.te.lms.mentor.entity.AppUser;
import com.te.lms.mentor.entity.Role;
import com.te.lms.mentor.repository.AppUserRepository;
import com.te.lms.mentor.repository.MentorRepository;
import com.te.lms.mentor.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

	private final BatchRepository batchRepository;
	private final MentorRepository mentorRepository;
	private final EmployeeRepository employeeRepository;
	private final TechnologyRepository technologyRepository;
	private final RegistrationRequestRepository requestRepository;
	private final EmailService emailService;
	private final AppUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Override
	public String addBatch(BatchDTO batchDTO) {
		Optional<Batch> optional = batchRepository.findById(batchDTO.getBatchId());
		if (!optional.isPresent()) {
			Batch batch = AdminUtils.convertDTOToEntity(batchDTO);
			Optional<Mentor> mentor = mentorRepository.findByMentorName(batchDTO.getMentorNames().get(0));
			batch.getMentors().add(mentor.get());
			mentor.get().getBatches().add(batch);
			Function<String, Technology> function = t -> {
				Technology technology = technologyRepository.findByTechnologyName(t);
				technology.getBatches().add(batch);
				return technology;
			};
			batch.setTechnologies(batchDTO.getTechnologies().stream().map(function).collect(Collectors.toList()));
			batchRepository.save(batch);
			return batch.getBatchName();
		}
		throw new BatchIdAlreadyExistException("Batch Id entered exists in Database");
	}

	@Override
	public String addMentor(MentorDTO mentorDTO) {
		employeeRepository.findById(mentorDTO.getEmployeeId()).orElseThrow(()->new EmployeeIdNotRegisteredException("EmployeeId Not Present in Database"));
		Optional<Mentor> mentorOptional = mentorRepository.findByMentorName(mentorDTO.getMentorName());
		if (mentorOptional.isEmpty()) {
			Mentor mentor = AdminUtils.convertDTOToEntity(mentorDTO);
			Function<String, Technology> function = t -> {
				Technology technology = technologyRepository.findByTechnologyName(t);
				technology.getMentors().add(mentor);
				return technology;
			};
			mentor.setTechnologies(mentorDTO.getTechnologies().stream().map(function).collect(Collectors.toList()));
			mentorRepository.save(mentor);
			Thread thread = new Thread(() -> {
				emailService.sendEmail(EmailDetails.builder().body("Hii " + mentor.getMentorName()
						+ " you have been assigned as mentor. You can now login using username:"
						+ mentor.getMentorName() + " and password: qwerty123@" + " to get details about your batch."
						+" Do use this login details for co-ordinating employees in your batch.")
						.receptientEmail(mentor.getEmailId()).subject("Assigned As Mentor").build());
			});
			thread.start();
			AppUser appUser = AppUser.builder().username(mentor.getMentorName())
					.password(passwordEncoder.encode("qwerty123@")).build();
			Role role = roleRepository.findByRoleName("ROLE_MENTOR").get();
			appUser.setRoles(List.of(role));
			userRepository.save(appUser);

			return mentorDTO.getMentorName();
		}
		throw new MentorNameIsPresentException("Mentor Name is present in Database");
	}

	@Override
	public List<BatchDTO> getBatchList() {
		List<Batch> batches = batchRepository.findByIsDeleted(false);
		return batches.stream().map(b -> AdminUtils.covertEntityToDTO(b)).collect(Collectors.toList());
	}

	@Override
	public List<String> getMentorNames() {
		List<Mentor> mentors = mentorRepository.findByIsDeleted(false);
		return mentors.stream().map(m -> m.getMentorName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getTechnologies() {
		return technologyRepository.findAll().stream().map(t -> t.getTechnologyName()).collect(Collectors.toList());
	}

	@Override
	public String updateBatch(BatchDTO batchDTO) {
		Optional<Batch> optional = batchRepository.findById(batchDTO.getBatchId());
		if (optional.isPresent()) {
			Batch batch = AdminUtils.convertDTOToEntity(batchDTO);
			batchRepository.save(batch);
			return batch.getBatchId();
		}
		return null;
	}

	@Override
	public void deleteBatch(String batchId) {
		Batch batch = batchRepository.findById(batchId).get();
		batch.setDeleted(true);
		batchRepository.save(batch);
	}

	@Override
	public List<RegistrationRequestDTO> getRegistrationRequests() {
		List<RegistrationRequest> registrationRequests = requestRepository.findByAdminActionTaken(false);
		List<Optional<Employee>> optionalEmployees = registrationRequests.stream()
				.map(r -> employeeRepository.findById(r.getEmployeeId())).collect(Collectors.toList());
		return optionalEmployees.stream().map(o -> AdminUtils.convertEntityToDTO(o.get()))
				.collect(Collectors.toList());
	}

	@Override
	public void approveEmployeeRequest(String employeeId) {
		RegistrationRequest request = requestRepository.findByEmployeeId(employeeId);
		request.setAdminActionTaken(true);
		request.setApproved(true);
		requestRepository.save(request);
		Thread thread = new Thread(() -> {
			Employee employee = employeeRepository.findById(employeeId).get();
			emailService.sendEmail(EmailDetails.builder()
					.body("Hii " + employee.getEmployeeName()
							+ " your request has been approved by the Admin. You can now login using username:"
							+ employeeId + " and password: qwerty123@")
					.receptientEmail(employee.getEmailId()).subject("Employee Approved").build());
		});
		thread.start();
		AppUser appUser = AppUser.builder().username(employeeId).password(passwordEncoder.encode("qwerty123@")).build();
		Role role = roleRepository.findByRoleName("ROLE_EMPLOYEE").get();
		appUser.setRoles(List.of(role));
		userRepository.save(appUser);
	}

	@Override
	public void rejectEmployeeRequest(RejectionReasonDTO reasonDTO) {
		RegistrationRequest request = requestRepository.findByEmployeeId(reasonDTO.getEmployeeId());
		request.setAdminActionTaken(true);
		request.setRejectReason(reasonDTO.getRejectionReason());
		requestRepository.save(request);
		Employee employee = employeeRepository.findById(reasonDTO.getEmployeeId()).get();
		employee.setRejected(true);
		employeeRepository.save(employee);
	}

	@Override
	public String updateMentor(MentorDTO mentorDTO) {
		Mentor mentor = mentorRepository.findByMentorName(mentorDTO.getMentorName()).get();
		Function<String, Technology> function = t -> {
			Technology technology = technologyRepository.findByTechnologyName(t);
			technology.getMentors().add(mentor);
			return technology;
		};
		mentor.setTechnologies(mentorDTO.getTechnologies().stream().map(function).collect(Collectors.toList()));
		mentor.setEmailId(mentorDTO.getEmailId());
		mentorRepository.save(mentor);
		return mentor.getMentorName();
	}

	@Override
	public void deleteMentor(String mentorName) {
		Mentor mentor = mentorRepository.findByMentorName(mentorName).get();
		mentor.setDeleted(true);
		mentorRepository.save(mentor);
	}

	@Override
	public List<MentorDTO> getMentorList() {
		List<Mentor> mentors = mentorRepository.findByIsDeleted(false);
		return mentors.stream().map(m -> AdminUtils.covertEntityToDTO(m)).collect(Collectors.toList());
	}

	@Override
	public List<String> getBatchNames() {
		List<Batch> batches = batchRepository.findByIsDeleted(false);
		return batches.stream().map(b -> b.getBatchName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getBatchIds() {
		List<Batch> batches = batchRepository.findByIsDeleted(false);
		return batches.stream().map(b -> b.getBatchId()).collect(Collectors.toList());
	}

	@Override
	public List<String> getStatus() {
		return Arrays.stream(BatchStatus.values()).map(b -> b.toString()).collect(Collectors.toList());
	}
	
	@Override
	public void addEmployeeToBatch(String employeeId, String batchId) {
		Batch batch = batchRepository.findById(batchId).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		batch.getEmployees().add(employee);
		employee.setBatch(batch);
		batchRepository.save(batch);
	}

}
