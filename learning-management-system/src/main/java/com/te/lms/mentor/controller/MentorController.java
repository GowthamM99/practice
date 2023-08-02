package com.te.lms.mentor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.admin.dto.BatchDTO;
import com.te.lms.mentor.dto.AttendanceUpdateDTO;
import com.te.lms.mentor.dto.BatchAttendanceDTO;
import com.te.lms.mentor.dto.BatchDashboardDTO;
import com.te.lms.mentor.dto.EmployeeBatchDTO;
import com.te.lms.mentor.dto.MockRatingDTO;
import com.te.lms.mentor.dto.ScheduledMockDTO;
import com.te.lms.mentor.service.MentorService;
import com.te.lms.mentor.service.MockRatingService;
import com.te.lms.mentor.service.ScheduledMockService;
import com.te.lms.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/private/mentor")
@RestController
public class MentorController {
	private final ScheduledMockService mockService;
	private final MentorService mentorService;
	private final MockRatingService ratingService;

	@GetMapping("/get")
	public MockRatingDTO getStructure() {
		return MockRatingDTO.builder().build();
	}

	@PostMapping(path = "/mock")
	public ResponseEntity<SuccessResponse<Integer>> scheduleMockToBatch(
			@RequestBody ScheduledMockDTO scheduledMockDTO) {
		int mockId = mockService.scheduleMockToBatch(scheduledMockDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<Integer>builder().data(mockId).message("MOCK SCHEDULED").build());
	}

	@PostMapping(path = "/rating")
	public ResponseEntity<SuccessResponse<Integer>> createMockRating(@RequestParam("empid") String employeeId,
			@RequestBody MockRatingDTO mockRatingDTO) {
		int mockRatingId = ratingService.createMockRating(employeeId, mockRatingDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<Integer>builder().data(mockRatingId).message("MOCK RATING UPDATED").build());
	}

	@GetMapping(path = "/batch")
	public ResponseEntity<SuccessResponse<List<BatchDTO>>> getBatches(@RequestParam("name") String mentorName) {
		List<BatchDTO> batchDTOs = mentorService.getBatches(mentorName);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<List<BatchDTO>>builder().data(batchDTOs).message("Batches Fetched").build());
	}

	@GetMapping(path = "/employee")
	public ResponseEntity<SuccessResponse<List<EmployeeBatchDTO>>> getEmployeesFromBatch(
			@RequestParam("batchId") String batchId) {
		List<EmployeeBatchDTO> employeeBatchDTOs = mentorService.getEmployeesFromBatch(batchId);
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<List<EmployeeBatchDTO>>builder()
				.data(employeeBatchDTOs).message("Employees From Batch").build());
	}

	@GetMapping(path = "/batchname")
	public ResponseEntity<SuccessResponse<List<String>>> getBatcheNames(@RequestParam("name") String mentorName) {
		List<String> batchNames = mentorService.getBatcheNames(mentorName);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<List<String>>builder().data(batchNames).message("Batch Names Fetched").build());
	}

	@GetMapping(path = "/dashboard")
	public ResponseEntity<SuccessResponse<BatchDashboardDTO>> getBatchDashboard(
			@RequestParam("name") String batchName) {
		BatchDashboardDTO dashboardDTO = mentorService.getBatchDashboard(batchName);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				SuccessResponse.<BatchDashboardDTO>builder().data(dashboardDTO).message("Employee Details Fetched From Batch").build());
	}

	@GetMapping(path = "/attendance")
	public ResponseEntity<SuccessResponse<List<AttendanceUpdateDTO>>> getBatchAttendance(@RequestParam("batchId") String batchId) {
		List<AttendanceUpdateDTO> dtos = mentorService.getBatchAttendance(batchId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<List<AttendanceUpdateDTO>>builder().data(dtos).message("Attendance Updation DTO").build());
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<SuccessResponse<String>> updateBatchAttendance(@RequestBody BatchAttendanceDTO attendanceDTO) {
		mentorService.updateBatchAttendance(attendanceDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<String>builder().data("Attendance Updated For Batch").message("Successful").build());
	}
}
