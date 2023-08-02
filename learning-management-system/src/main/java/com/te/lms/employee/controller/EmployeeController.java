package com.te.lms.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.employee.dto.AttendanceDTO;
import com.te.lms.employee.dto.EmployeeProfileDTO;
import com.te.lms.employee.service.EmployeeService;
import com.te.lms.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/private/employee")
@RestController
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping(path = "/attendance")
	public ResponseEntity<SuccessResponse<List<AttendanceDTO>>> getAttendances(@RequestParam("id") String employeeId) {
		List<AttendanceDTO> attendanceDTOs = employeeService.getAttendanceFromEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<AttendanceDTO>>builder().data(attendanceDTOs).message("FETCHED").build());
	}
	
	@PutMapping(path = "/designation")
	public ResponseEntity<SuccessResponse<String>> updateDesignation(){
		employeeService.updateDesignation();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data("Designation Updated").message("UPDATED").build());
	}
	
	@GetMapping(path = "/profile")
	public ResponseEntity<SuccessResponse<EmployeeProfileDTO>> getEmployee(@RequestParam("id") String employeeId) {
		EmployeeProfileDTO employeeDTO = employeeService.getEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<EmployeeProfileDTO>builder().data(employeeDTO).message("FETCHED").build());
	}
}
