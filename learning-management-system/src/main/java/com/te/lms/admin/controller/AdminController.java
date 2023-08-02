package com.te.lms.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.admin.dto.BatchDTO;
import com.te.lms.admin.dto.MentorDTO;
import com.te.lms.admin.dto.RegistrationRequestDTO;
import com.te.lms.admin.dto.RejectionReasonDTO;
import com.te.lms.admin.service.AdminService;
import com.te.lms.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/private/admin")
@RestController
public class AdminController {

	private final AdminService adminService;

	@PostMapping(path = "/batch")
	public ResponseEntity<SuccessResponse<String>> addBatch(@RequestBody BatchDTO batchDTO) {
		String batchName = adminService.addBatch(batchDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(batchName).message("Batch Added").build());
	}

	@PostMapping(path = "/mentor")
	public ResponseEntity<SuccessResponse<String>> addMentor(@RequestBody MentorDTO mentorDTO) {
		String mentorName = adminService.addMentor(mentorDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(mentorName).message("Mentor Added").build());
	}

	@GetMapping(path = "/batchlist")
	public ResponseEntity<SuccessResponse<List<BatchDTO>>> getBatchList() {
		List<BatchDTO> batchDTOs = adminService.getBatchList();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<BatchDTO>>builder().data(batchDTOs).message("Batch List Fetched").build());
	}

	@GetMapping(path = "/mentor")
	public ResponseEntity<SuccessResponse<List<String>>> getMentorNames() {
		List<String> mentorNames = adminService.getMentorNames();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
				SuccessResponse.<List<String>>builder().data(mentorNames).message("Mentor Names Fetched").build());
	}

	@GetMapping(path = "/technology")
	public ResponseEntity<SuccessResponse<List<String>>> getTechnologies() {
		List<String> technologies = adminService.getTechnologies();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
				SuccessResponse.<List<String>>builder().data(technologies).message("Technologies Fetched").build());
	}

	@PutMapping(path = "/batch")
	public ResponseEntity<SuccessResponse<String>> updateBatch(@RequestBody BatchDTO batchDTO) {
		String batchName = adminService.updateBatch(batchDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(batchName).message("Batch Updated").build());
	}

	@DeleteMapping(path = "/batch")
	public ResponseEntity<SuccessResponse<String>> deleteBatch(@RequestParam("id") String batchId) {
		adminService.deleteBatch(batchId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(batchId).message("Batch Deleted").build());
	}

	@GetMapping(path = "/request")
	public ResponseEntity<SuccessResponse<List<RegistrationRequestDTO>>> getRegistrationRequests() {
		List<RegistrationRequestDTO> requestDTOs = adminService.getRegistrationRequests();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponse.<List<RegistrationRequestDTO>>builder()
				.data(requestDTOs).message("Registration Request Fetched").build());
	}

	@PutMapping(path = "/approve")
	public ResponseEntity<SuccessResponse<String>> approveEmployeeRequest(@RequestParam("empid") String employeeId) {
		adminService.approveEmployeeRequest(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("Employee Request Approved").build());
	}

	@PutMapping(path = "/reject")
	public ResponseEntity<SuccessResponse<String>> rejectEmployeeRequest(@RequestBody RejectionReasonDTO reasonDTO) {
		adminService.rejectEmployeeRequest(reasonDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(reasonDTO.getEmployeeId()).message("Employee Request Rejected").build());
	}
	
	@PutMapping(path = "/mentor")
	public ResponseEntity<SuccessResponse<String>> updateMentor(@RequestBody MentorDTO mentorDTO) {
		String mentorName = adminService.updateMentor(mentorDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(mentorName).message("Mentor Updated").build());
	} 
	
	@DeleteMapping(path = "/mentor")
	public ResponseEntity<SuccessResponse<String>> deleteMentor(@RequestParam("name") String mentorName) {
		adminService.deleteMentor(mentorName);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(mentorName).message("Mentor Deleted").build());
	}
	
	@GetMapping(path = "/mentorlist")
	public ResponseEntity<SuccessResponse<List<MentorDTO>>> getMentorList() {
		List<MentorDTO> mentorDTOs = adminService.getMentorList();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<MentorDTO>>builder().data(mentorDTOs).message("Mentor List Fetched").build());
	}
	
	@GetMapping(path = "/batchnames")
	public ResponseEntity<SuccessResponse<List<String>>> getBatchNames() {
		List<String> batchNames = adminService.getBatchNames();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<String>>builder().data(batchNames).message("Batch Names Fetched").build());
	}
	
	@GetMapping(path = "/batchids")
	public ResponseEntity<SuccessResponse<List<String>>> getBatchIds() {
		List<String> batchIds = adminService.getBatchIds();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<String>>builder().data(batchIds).message("Batch Ids Fetched").build());
	}
	
	@GetMapping(path = "/status")
	public ResponseEntity<SuccessResponse<List<String>>> getStatus() {
		List<String> statusList = adminService.getStatus();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<String>>builder().data(statusList).message("Batch Status Fetched").build());
	}
	
	@PutMapping(path = "/add")
	public ResponseEntity<SuccessResponse<String>> addEmployeeToBatch(@RequestParam("empid") String employeeId,
			@RequestParam("batchid") String batchId) {
		adminService.addEmployeeToBatch(employeeId, batchId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<String>builder().data(employeeId).message("Employee Added").build());
	}
}
