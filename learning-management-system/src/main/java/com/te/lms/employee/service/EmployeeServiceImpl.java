package com.te.lms.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.lms.email.EmailDetails;
import com.te.lms.email.service.EmailService;
import com.te.lms.employee.dto.AddressDTO;
import com.te.lms.employee.dto.AttendanceDTO;
import com.te.lms.employee.dto.BankDetailDTO;
import com.te.lms.employee.dto.ContactDTO;
import com.te.lms.employee.dto.EducationDTO;
import com.te.lms.employee.dto.EmployeeDTO;
import com.te.lms.employee.dto.EmployeeProfileDTO;
import com.te.lms.employee.dto.EmployeeSecondaryInfoDTO;
import com.te.lms.employee.dto.ExperienceDTO;
import com.te.lms.employee.dto.TechnicalSkillDTO;
import com.te.lms.employee.entity.Address;
import com.te.lms.employee.entity.BankDetail;
import com.te.lms.employee.entity.Contact;
import com.te.lms.employee.entity.Education;
import com.te.lms.employee.entity.Employee;
import com.te.lms.employee.entity.EmployeeSecondaryInfo;
import com.te.lms.employee.entity.Experience;
import com.te.lms.employee.entity.RegistrationRequest;
import com.te.lms.employee.entity.TechnicalSkill;
import com.te.lms.employee.enums.Designation;
import com.te.lms.employee.repository.AddressRepository;
import com.te.lms.employee.repository.ContactRepository;
import com.te.lms.employee.repository.EducationRepository;
import com.te.lms.employee.repository.EmployeeRepository;
import com.te.lms.employee.repository.ExperienceRepository;
import com.te.lms.employee.repository.RegistrationRequestRepository;
import com.te.lms.employee.repository.TechnicalSkillRepository;
import com.te.lms.employee.utils.EmployeeUtils;
import com.te.lms.exception.CheckNewAndReenterPasswordException;
import com.te.lms.exception.CheckOldPasswordEnteredException;
import com.te.lms.exception.EmployeeIdAlreadyExistException;
import com.te.lms.exception.EmployeeIdNotRegisteredException;
import com.te.lms.mentor.dto.PasswordDTO;
import com.te.lms.mentor.entity.AppUser;
import com.te.lms.mentor.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EducationRepository educationRepository;
	private final AddressRepository addressRepository;
	private final TechnicalSkillRepository skillRepository;
	private final ExperienceRepository experienceRepository;
	private final ContactRepository contactRepository;
	private final RegistrationRequestRepository requestRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final EmailService emailService;

	@Override
	public String saveEmployee(EmployeeDTO employeeDTO) {
		if (!employeeRepository.findById(employeeDTO.getEmployeeId()).isPresent()) {
			Employee employee = employeeRepository.save(EmployeeUtils.convertDTOToEntity(employeeDTO));
			return employee.getEmployeeId();
		}
		throw new EmployeeIdAlreadyExistException("Check EmployeeId Entered!!!");
	}

	@Override
	public void addSecondaryInfoToEmployee(String employeeId, EmployeeSecondaryInfoDTO employeeSecondaryInfoDTO) {
		Employee employee = employeeRepository.findById(employeeId).get();
		if (employee.getEmployeeSecondaryInfo() == null) {
			EmployeeSecondaryInfo employeeSecondaryInfo = EmployeeUtils.convertDTOToEntity(employeeSecondaryInfoDTO);
			employee.setEmployeeSecondaryInfo(employeeSecondaryInfo);
			employeeSecondaryInfo.setEmployee(employee);
		} else {
			BeanUtils.copyProperties(employeeSecondaryInfoDTO, employee.getEmployeeSecondaryInfo());
		}
		employeeRepository.save(employee);
	}

	@Override
	public void addEducationToEmployee(String employeeId, List<EducationDTO> educationDTOs) {
		Employee employee = employeeRepository.findById(employeeId).get();
		if (employee.getEducationDetails() != null) {
			employee.getEducationDetails().stream().forEach(e -> educationRepository.delete(e));
		}
		Function<EducationDTO, Education> function = s -> {
			Education education = EmployeeUtils.convertDTOToEntity(s);
			education.setEmployee(employee);
			return education;
		};
		employee.setEducationDetails(educationDTOs.stream().map(function).collect(Collectors.toList()));
		employeeRepository.save(employee);
	}

	@Override
	public void addAddressToEmployee(String employeeId, List<AddressDTO> addressDTOs) {
		Employee employee = employeeRepository.findById(employeeId).get();
		if (employee.getAddressDetails() != null) {
			employee.getAddressDetails().stream().forEach(e -> addressRepository.delete(e));
		}
		Function<AddressDTO, Address> function = s -> {
			Address address = EmployeeUtils.convertDTOToEntity(s);
			address.setEmployee(employee);
			return address;
		};
		employee.setAddressDetails(addressDTOs.stream().map(function).collect(Collectors.toList()));
		employeeRepository.save(employee);

	}

	@Override
	public void addBankDetailToEmployee(String employeeId, BankDetailDTO bankDetailDTO) {
		Employee employee = employeeRepository.findById(employeeId).get();
		if (employee.getBankDetail() == null) {
			BankDetail bankDetail = EmployeeUtils.convertDTOToEntity(bankDetailDTO);
			employee.setBankDetail(bankDetail);
			bankDetail.setEmployee(employee);
		} else {
			BeanUtils.copyProperties(bankDetailDTO, employee.getBankDetail());
		}
		employeeRepository.save(employee);
	}

	@Override
	public void addTechnicalSkillToEmployee(String employeeId, List<TechnicalSkillDTO> skillDTOs) {
		Employee employee = employeeRepository.findById(employeeId).get();
		if (employee.getTechnicalSkills() != null) {
			employee.getTechnicalSkills().stream().forEach(e -> skillRepository.delete(e));
		}
		Function<TechnicalSkillDTO, TechnicalSkill> function = s -> {
			TechnicalSkill skill = EmployeeUtils.convertDTOToEntity(s);
			skill.setEmployee(employee);
			return skill;
		};
		employee.setTechnicalSkills(skillDTOs.stream().map(function).collect(Collectors.toList()));
		employeeRepository.save(employee);
	}

	@Override
	public void addExperienceToEmployee(String employeeId, List<ExperienceDTO> experienceDTOs) {
		Employee employee = employeeRepository.findById(employeeId).get();
		if (!employee.getExperiences().isEmpty()) {
			employee.getExperiences().stream().forEach(e -> experienceRepository.delete(e));
		}
		Function<ExperienceDTO, Experience> function = s -> {
			Experience experience = EmployeeUtils.convertDTOToEntity(s);
			experience.setEmployee(employee);
			return experience;
		};
		employee.setExperiences(experienceDTOs.stream().map(function).collect(Collectors.toList()));
		employeeRepository.save(employee);

	}

	@Override
	public void addContactToEmployee(String employeeId, List<ContactDTO> contactDTOs) {
		Employee employee = employeeRepository.findById(employeeId).get();
		if (!employee.getContacts().isEmpty()) {
			employee.getContacts().stream().forEach(e -> contactRepository.delete(e));
		}
		Function<ContactDTO, Contact> function = s -> {
			Contact contact = EmployeeUtils.convertDTOToEntity(s);
			contact.setEmployee(employee);
			return contact;
		};
		employee.setContacts(contactDTOs.stream().map(function).collect(Collectors.toList()));
		employeeRepository.save(employee);
		requestRepository.save(RegistrationRequest.builder().employeeId(employeeId).build());
	}

	@Override
	public EmployeeDTO getPrimaryInfoFromEmployee(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();
		return EmployeeUtils.covertEntityToDTO(employee);
	}

	@Override
	public EmployeeSecondaryInfoDTO getSecondaryInfoFromEmployee(String employeeId) {
		EmployeeSecondaryInfo employeeSecondaryInfo = employeeRepository.findById(employeeId).get()
				.getEmployeeSecondaryInfo();
		return EmployeeUtils.covertEntityToDTO(employeeSecondaryInfo);
	}

	@Override
	public List<EducationDTO> getEducationFromEmployee(String employeeId) {
		List<Education> educationDetails = employeeRepository.findById(employeeId).get().getEducationDetails();
		return educationDetails.stream().map(e -> EmployeeUtils.covertEntityToDTO(e)).toList();
	}

	@Override
	public List<AddressDTO> getAddressFromEmployee(String employeeId) {
		List<Address> addressDetails = employeeRepository.findById(employeeId).get().getAddressDetails();
		return addressDetails.stream().map(e -> EmployeeUtils.covertEntityToDTO(e)).toList();
	}

	@Override
	public BankDetailDTO getBankDetailFromEmployee(String employeeId) {
		BankDetail bankDetail = employeeRepository.findById(employeeId).get().getBankDetail();
		return EmployeeUtils.covertEntityToDTO(bankDetail);
	}

	@Override
	public List<TechnicalSkillDTO> getTechnicalSkillFromEmployee(String employeeId) {
		List<TechnicalSkill> technicalSkills = employeeRepository.findById(employeeId).get().getTechnicalSkills();
		return technicalSkills.stream().map(e -> EmployeeUtils.covertEntityToDTO(e)).toList();
	}

	@Override
	public List<ExperienceDTO> getExperienceFromEmployee(String employeeId) {
		List<Experience> experiences = employeeRepository.findById(employeeId).get().getExperiences();
		return experiences.stream().map(e -> EmployeeUtils.covertEntityToDTO(e)).toList();
	}

	@Override
	public List<ContactDTO> getContactFromEmployee(String employeeId) {
		List<Contact> contacts = employeeRepository.findById(employeeId).get().getContacts();
		return contacts.stream().map(e -> EmployeeUtils.covertEntityToDTO(e)).toList();
	}

	@Override
	public EmployeeProfileDTO getEmployee(String employeeId) {
		Optional<Employee> optional = employeeRepository.findById(employeeId);
		if (optional.isPresent()) {
			return EmployeeUtils.convertEntityToDTO(optional.get());
		}
		throw new EmployeeIdNotRegisteredException("Check the EmployeeId");
	}

	@Override
	public List<AttendanceDTO> getAttendanceFromEmployee(String employeeId) {
		Optional<Employee> optional = employeeRepository.findById(employeeId);
		if (optional.isPresent()) {
			Employee employee = optional.get();
			return employee.getAttendances().stream().map(a -> EmployeeUtils.covertEntityToDTO(a))
					.collect(Collectors.toList());
		}
		throw new EmployeeIdNotRegisteredException("Check the EmployeeId");
	}

	@Override
	public void updateDesignation() {
		List<Employee> employees = employeeRepository.findByDesignation(Designation.TRAINEE);
		List<Employee> collect = employees.stream().map(e -> {
			e.setDesignation(Designation.DEVELOPER);
			return e;
		}).collect(Collectors.toList());
		employeeRepository.saveAll(collect);
	}

	@Override
	public void resetPassword(String username, PasswordDTO passwordDTO) {
		AppUser appUser = appUserRepository.findById(username).get();
		if (passwordEncoder.matches(passwordDTO.getOldPassword(), appUser.getPassword())) {
			if (passwordDTO.getNewPassword().equals(passwordDTO.getReEnterNewPassword())
					&& !passwordDTO.getNewPassword().equals("qwerty123@")) {
				appUser.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
				appUserRepository.save(appUser);
			} else {
				throw new CheckOldPasswordEnteredException("Old Password entered does not match with password in database");
			}
		} else {
			throw new CheckNewAndReenterPasswordException("New Password And Re-Enter Password entered does not match");
		}
	}

	@Override
	public Long getOTP(String email) {
		long otp= Math.round(Math.random()*(899999)+100000);
		new Thread(()->{
			emailService.sendEmail(EmailDetails.builder().receptientEmail(email).subject("OTP").body(otp+" this is the OTP generated for further process").build());
		}).start();
		return otp;
	}

}
