package com.te.lms.employee.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
import com.te.lms.employee.entity.Attendance;
import com.te.lms.employee.entity.BankDetail;
import com.te.lms.employee.entity.Contact;
import com.te.lms.employee.entity.Education;
import com.te.lms.employee.entity.Employee;
import com.te.lms.employee.entity.EmployeeSecondaryInfo;
import com.te.lms.employee.entity.Experience;
import com.te.lms.employee.entity.TechnicalSkill;
import com.te.lms.mentor.entity.MockRating;

public class EmployeeUtils {

	public static Employee convertDTOToEntity(EmployeeDTO employeeDTO) {
		return Employee.builder().employeeId(employeeDTO.getEmployeeId()).employeeName(employeeDTO.getEmployeeName())
				.dateOfBirth(employeeDTO.getDateOfBirth()).bloodGroup(employeeDTO.getBloodGroup())
				.dateOfJoining(employeeDTO.getDateOfJoining()).salary(employeeDTO.getSalary())
				.emailId(employeeDTO.getEmailId()).designation(employeeDTO.getDesignation())
				.gender(employeeDTO.getGender()).nationality(employeeDTO.getNationality())
				.employeeStatus(employeeDTO.getEmployeeStatus()).attendances(new ArrayList<Attendance>())
				.mockRatings(new ArrayList<MockRating>())
				.build();
	}

	public static EmployeeSecondaryInfo convertDTOToEntity(EmployeeSecondaryInfoDTO secondaryInfoDTO) {
		return EmployeeSecondaryInfo.builder().panNumber(secondaryInfoDTO.getPanNumber())
				.aadharNumber(secondaryInfoDTO.getAadharNumber()).fatherName(secondaryInfoDTO.getFatherName())
				.motherName(secondaryInfoDTO.getMotherName()).spouseName(secondaryInfoDTO.getSpouseName())
				.maritalStatus(secondaryInfoDTO.getMaritalStatus()).passportNumber(secondaryInfoDTO.getPassportNumber())
				.build();
	}

	public static Education convertDTOToEntity(EducationDTO educationDTO) {
		return Education.builder().educationType(educationDTO.getEducationType())
				.instituteName(educationDTO.getInstituteName()).percentage(educationDTO.getPercentage())
				.specialization(educationDTO.getSpecialization()).yearOfPassing(educationDTO.getYearOfPassing())
				.state(educationDTO.getState()).universityName(educationDTO.getUniversityName()).build();
	}

	public static Address convertDTOToEntity(AddressDTO addressDTO) {
		return Address.builder().addressType(addressDTO.getAddressType()).city(addressDTO.getCity())
				.doorNumber(addressDTO.getDoorNumber()).landMark(addressDTO.getLandMark())
				.locality(addressDTO.getLocality()).pinCode(addressDTO.getPinCode()).state(addressDTO.getState())
				.street(addressDTO.getStreet()).build();
	}

	public static BankDetail convertDTOToEntity(BankDetailDTO bankDetailDTO) {
		return BankDetail.builder().accountType(bankDetailDTO.getAccountType())
				.accountNumber(bankDetailDTO.getAccountNumber()).bankName(bankDetailDTO.getBankName())
				.branch(bankDetailDTO.getBranch()).ifscCode(bankDetailDTO.getIfscCode()).state(bankDetailDTO.getState())
				.build();
	}

	public static TechnicalSkill convertDTOToEntity(TechnicalSkillDTO skillDTO) {
		return TechnicalSkill.builder().skillType(skillDTO.getSkillType())
				.yearOfExperience(skillDTO.getYearOfExperience()).skillRating(skillDTO.getSkillRating()).build();
	}

	public static Experience convertDTOToEntity(ExperienceDTO experienceDTO) {
		return Experience.builder().companyName(experienceDTO.getCompanyName())
				.dateOfJoining(experienceDTO.getDateOfJoining()).dateOfRelieving(experienceDTO.getDateOfRelieving())
				.designation(experienceDTO.getDesignation()).location(experienceDTO.getLocation())
				.yearOfExperience(experienceDTO.getYearOfExperience()).build();
	}

	public static Contact convertDTOToEntity(ContactDTO contactDTO) {
		return Contact.builder().contactType(contactDTO.getContactType()).contactNumber(contactDTO.getContactNumber())
				.build();
	}

	/* ------------Entity To DTO------------ */

	public static EmployeeDTO covertEntityToDTO(Employee employee) {
		return EmployeeDTO.builder().bloodGroup(employee.getBloodGroup()).dateOfBirth(employee.getDateOfBirth())
				.dateOfJoining(employee.getDateOfJoining()).designation(employee.getDesignation())
				.emailId(employee.getEmailId()).employeeId(employee.getEmployeeId())
				.employeeName(employee.getEmployeeName()).employeeStatus(employee.getEmployeeStatus())
				.salary(employee.getSalary()).gender(employee.getGender()).nationality(employee.getNationality())
				.build();
	}

	public static EmployeeSecondaryInfoDTO covertEntityToDTO(EmployeeSecondaryInfo employeeSecondaryInfo) {
		return EmployeeSecondaryInfoDTO.builder().aadharNumber(employeeSecondaryInfo.getAadharNumber())
				.fatherName(employeeSecondaryInfo.getFatherName()).motherName(employeeSecondaryInfo.getMotherName())
				.maritalStatus(employeeSecondaryInfo.getMaritalStatus()).panNumber(employeeSecondaryInfo.getPanNumber())
				.passportNumber(employeeSecondaryInfo.getPassportNumber())
				.spouseName(employeeSecondaryInfo.getSpouseName()).build();
	}

	public static EducationDTO covertEntityToDTO(Education education) {
		return EducationDTO.builder().educationType(education.getEducationType())
				.instituteName(education.getInstituteName()).percentage(education.getPercentage())
				.specialization(education.getSpecialization()).state(education.getState())
				.universityName(education.getUniversityName()).yearOfPassing(education.getYearOfPassing()).build();
	}

	public static AddressDTO covertEntityToDTO(Address address) {
		return AddressDTO.builder().addressType(address.getAddressType()).doorNumber(address.getCity())
				.city(address.getCity()).landMark(address.getLandMark()).locality(address.getLocality())
				.pinCode(address.getPinCode()).street(address.getStreet()).state(address.getState()).build();
	}

	public static BankDetailDTO covertEntityToDTO(BankDetail bankDetail) {
		return BankDetailDTO.builder().accountNumber(bankDetail.getAccountNumber())
				.accountType(bankDetail.getAccountType()).bankName(bankDetail.getBankName())
				.branch(bankDetail.getBranch()).ifscCode(bankDetail.getIfscCode()).state(bankDetail.getState()).build();
	}

	public static TechnicalSkillDTO covertEntityToDTO(TechnicalSkill technicalSkill) {
		return TechnicalSkillDTO.builder().skillType(technicalSkill.getSkillType())
				.skillRating(technicalSkill.getSkillRating()).yearOfExperience(technicalSkill.getYearOfExperience())
				.build();
	}

	public static ExperienceDTO covertEntityToDTO(Experience experience) {
		return ExperienceDTO.builder().companyName(experience.getCompanyName())
				.yearOfExperience(experience.getYearOfExperience()).dateOfJoining(experience.getDateOfJoining())
				.dateOfRelieving(experience.getDateOfRelieving()).designation(experience.getDesignation())
				.location(experience.getLocation()).build();
	}

	public static ContactDTO covertEntityToDTO(Contact contact) {
		return ContactDTO.builder().contactType(contact.getContactType()).contactNumber(contact.getContactNumber())
				.build();
	}
	
	public static AttendanceDTO covertEntityToDTO(Attendance attendance) {
		return AttendanceDTO.builder()
				.afternoon(attendance.isAfternoon())
				.morning(attendance.isMorning())
				.attendanceDate(attendance.getAttendanceDate())
				.build();
	}


	public static EmployeeProfileDTO convertEntityToDTO(Employee employee) {
		return EmployeeProfileDTO.builder()
				.addressDTOs(employee.getAddressDetails().stream().map(a->covertEntityToDTO(a)).collect(Collectors.toList()))
				.contactDTOs(employee.getContacts().stream().map(a->covertEntityToDTO(a)).collect(Collectors.toList()))
				.educationDTOs(employee.getEducationDetails().stream().map(a->covertEntityToDTO(a)).collect(Collectors.toList()))
				.experienceDTOs(employee.getExperiences().stream().map(a->covertEntityToDTO(a)).collect(Collectors.toList()))
				.detailDTO(EmployeeUtils.covertEntityToDTO(employee.getBankDetail()))
				.employeeDTO(covertEntityToDTO(employee))
				.secondaryInfoDTO(covertEntityToDTO(employee.getEmployeeSecondaryInfo()))
				.skillDTOs(employee.getTechnicalSkills().stream().map(a->covertEntityToDTO(a)).collect(Collectors.toList()))
				.build();
	}
}
