package com.te.lms.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeProfileDTO {
	private EmployeeDTO employeeDTO;
	private EmployeeSecondaryInfoDTO secondaryInfoDTO;
	private List<EducationDTO> educationDTOs;
	private List<AddressDTO> addressDTOs;
	private BankDetailDTO detailDTO;
	private List<TechnicalSkillDTO> skillDTOs;
	private List<ExperienceDTO> experienceDTOs;
	private List<ContactDTO> contactDTOs;
}
