package com.te.lms.employee.service;

import java.util.List;

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
import com.te.lms.mentor.dto.PasswordDTO;

public interface EmployeeService {

	String saveEmployee(EmployeeDTO employeeDTO);

	void addSecondaryInfoToEmployee(String employeeId, EmployeeSecondaryInfoDTO employeeSecondaryInfoDTO);

	void addEducationToEmployee(String employeeId, List<EducationDTO> educationDTOs);

	void addAddressToEmployee(String employeeId, List<AddressDTO> addressDTOs);

	void addBankDetailToEmployee(String employeeId, BankDetailDTO bankDetailDTO);

	void addTechnicalSkillToEmployee(String employeeId, List<TechnicalSkillDTO> skillDTOs);

	void addExperienceToEmployee(String employeeId, List<ExperienceDTO> experienceDTOs);

	void addContactToEmployee(String employeeId, List<ContactDTO> contactDTOs);

	EmployeeDTO getPrimaryInfoFromEmployee(String employeeId);

	EmployeeSecondaryInfoDTO getSecondaryInfoFromEmployee(String employeeId);

	List<EducationDTO> getEducationFromEmployee(String employeeId);

	List<AddressDTO> getAddressFromEmployee(String employeeId);

	BankDetailDTO getBankDetailFromEmployee(String employeeId);

	List<TechnicalSkillDTO> getTechnicalSkillFromEmployee(String employeeId);

	List<ExperienceDTO> getExperienceFromEmployee(String employeeId);

	List<ContactDTO> getContactFromEmployee(String employeeId);

	EmployeeProfileDTO getEmployee(String employeeId);

	List<AttendanceDTO> getAttendanceFromEmployee(String employeeId);

	void updateDesignation();

	void resetPassword(String username, PasswordDTO passwordDTO);

	Long getOTP(String email);

}
