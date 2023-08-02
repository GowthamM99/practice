package com.te.lms.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.employee.dto.AddressDTO;
import com.te.lms.employee.dto.BankDetailDTO;
import com.te.lms.employee.dto.ContactDTO;
import com.te.lms.employee.dto.EducationDTO;
import com.te.lms.employee.dto.EmployeeDTO;
import com.te.lms.employee.dto.EmployeeSecondaryInfoDTO;
import com.te.lms.employee.dto.ExperienceDTO;
import com.te.lms.employee.dto.LoginDTO;
import com.te.lms.employee.dto.TechnicalSkillDTO;
import com.te.lms.employee.service.EmployeeService;
import com.te.lms.mentor.dto.PasswordDTO;
import com.te.lms.response.SuccessResponse;
import com.te.lms.utils.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "api/v1/public/employee")
@RestController
public class LMSController {

	private final EmployeeService employeeService;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;

	@PostMapping(path = "/primary")
	public ResponseEntity<SuccessResponse<String>> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		String id = employeeService.saveEmployee(employeeDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(id).message("SAVED").build());
	}

	@PutMapping(path = "/secondary")
	public ResponseEntity<SuccessResponse<String>> addSecondaryInfoToEmployee(@RequestParam("id") String employeeId,
			@RequestBody EmployeeSecondaryInfoDTO employeeSecondaryInfoDTO) {
		employeeService.addSecondaryInfoToEmployee(employeeId, employeeSecondaryInfoDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("SAVED").build());
	}

	@PutMapping(path = "/education")
	public ResponseEntity<SuccessResponse<String>> addEducationToEmployee(@RequestParam("id") String employeeId,
			@RequestBody List<EducationDTO> educationDTOs) {

		employeeService.addEducationToEmployee(employeeId, educationDTOs);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("SAVED").build());
	}

	@PutMapping(path = "/address")
	public ResponseEntity<SuccessResponse<String>> addAddressToEmployee(@RequestParam("id") String employeeId,
			@RequestBody List<AddressDTO> addressDTOs) {

		employeeService.addAddressToEmployee(employeeId, addressDTOs);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("SAVED").build());
	}

	@PutMapping(path = "/bank")
	public ResponseEntity<SuccessResponse<String>> addBankDetailToEmployee(@RequestParam("id") String employeeId,
			@RequestBody BankDetailDTO bankDetailDTO) {
		employeeService.addBankDetailToEmployee(employeeId, bankDetailDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("SAVED").build());
	}

	@PutMapping(path = "/technicalskill")
	public ResponseEntity<SuccessResponse<String>> addTechnicalSkillToEmployee(@RequestParam("id") String employeeId,
			@RequestBody List<TechnicalSkillDTO> skillDTOs) {

		employeeService.addTechnicalSkillToEmployee(employeeId, skillDTOs);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("SAVED").build());
	}

	@PutMapping(path = "/experience")
	public ResponseEntity<SuccessResponse<String>> addExperienceToEmployee(@RequestParam("id") String employeeId,
			@RequestBody List<ExperienceDTO> experienceDTOs) {

		employeeService.addExperienceToEmployee(employeeId, experienceDTOs);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("SAVED").build());
	}

	@PutMapping(path = "/contact")
	public ResponseEntity<SuccessResponse<String>> addContactToEmployee(@RequestParam("id") String employeeId,
			@RequestBody List<ContactDTO> contactDTOs) {

		employeeService.addContactToEmployee(employeeId, contactDTOs);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("SAVED").build());
	}

	/* ------------Get API's------------ */

	@GetMapping(path = "/primary")
	public ResponseEntity<SuccessResponse<EmployeeDTO>> getPrimaryInfoFromEmployee(
			@RequestParam("id") String employeeId) {
		EmployeeDTO employeeDTO = employeeService.getPrimaryInfoFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<EmployeeDTO>builder().data(employeeDTO).message("RETRIEVED").build());
	}

	@GetMapping(path = "/secondary")
	public ResponseEntity<SuccessResponse<EmployeeSecondaryInfoDTO>> getSecondaryInfoFromEmployee(
			@RequestParam("id") String employeeId) {
		EmployeeSecondaryInfoDTO secondaryInfoDTO = employeeService.getSecondaryInfoFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponse.<EmployeeSecondaryInfoDTO>builder()
				.data(secondaryInfoDTO).message("RETRIEVED").build());
	}

	@GetMapping(path = "/education")
	public ResponseEntity<SuccessResponse<List<EducationDTO>>> getEducationFromEmployee(
			@RequestParam("id") String employeeId) {
		List<EducationDTO> educationDTOs = employeeService.getEducationFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<EducationDTO>>builder().data(educationDTOs).message("RETRIEVED").build());
	}

	@GetMapping(path = "/address")
	public ResponseEntity<SuccessResponse<List<AddressDTO>>> getAddressFromEmployee(
			@RequestParam("id") String employeeId) {
		List<AddressDTO> addressDTOs = employeeService.getAddressFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<AddressDTO>>builder().data(addressDTOs).message("RETRIEVED").build());
	}

	@GetMapping(path = "/bank")
	public ResponseEntity<SuccessResponse<BankDetailDTO>> getBankDetailFromEmployee(
			@RequestParam("id") String employeeId) {
		BankDetailDTO bankDetailDTO = employeeService.getBankDetailFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<BankDetailDTO>builder().data(bankDetailDTO).message("RETRIEVED").build());
	}

	@GetMapping(path = "/technicalskill")
	public ResponseEntity<SuccessResponse<List<TechnicalSkillDTO>>> getTechnicalSkillFromEmployee(
			@RequestParam("id") String employeeId) {
		List<TechnicalSkillDTO> skillDTOs = employeeService.getTechnicalSkillFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<TechnicalSkillDTO>>builder().data(skillDTOs).message("RETRIEVED").build());
	}

	@GetMapping(path = "/experience")
	public ResponseEntity<SuccessResponse<List<ExperienceDTO>>> getExperienceFromEmployee(
			@RequestParam("id") String employeeId) {
		List<ExperienceDTO> experienceDTOs = employeeService.getExperienceFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<ExperienceDTO>>builder().data(experienceDTOs).message("RETRIEVED").build());
	}

	@GetMapping(path = "/contact")
	public ResponseEntity<SuccessResponse<List<ContactDTO>>> getContactFromEmployee(
			@RequestParam("id") String employeeId) {
		List<ContactDTO> contactDTOs = employeeService.getContactFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<ContactDTO>>builder().data(contactDTOs).message("RETRIEVED").build());
	}

	@PostMapping(path = "/login")
	public ResponseEntity<SuccessResponse<String>> login(@RequestBody LoginDTO loginDto) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		String token = jwtUtils.generateToken(loginDto.getUsername());
		if(loginDto.getPassword().equals("qwerty123@")){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponse.<String>builder()
				.data("Login Successfull").message("Reseting Password is Required").build());}
		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponse.<String>builder()
					.data(token).message("Login Successfull").build());
		}
	}
	
	@PutMapping(path = "/resetpassword")
	public ResponseEntity<SuccessResponse<String>> resetPassword(@RequestParam("username") String username,@RequestBody PasswordDTO passwordDTO) {
		employeeService.resetPassword(username,passwordDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data("Password Changed").message("Login Again using New Password").build());
	} 
	
	@GetMapping(path = "/otp")
	public ResponseEntity<SuccessResponse<Long>> getOTP(@RequestParam("email") String email) {
		long otp= employeeService.getOTP(email);
		return ResponseEntity.status(HttpStatus.OK)
				.body(SuccessResponse.<Long>builder().data(otp).message("Login Again using New Password").build());
	} 
}
