package com.te.lms.admin.service;

import java.util.List;

import com.te.lms.admin.dto.BatchDTO;
import com.te.lms.admin.dto.MentorDTO;
import com.te.lms.admin.dto.RegistrationRequestDTO;
import com.te.lms.admin.dto.RejectionReasonDTO;

public interface AdminService {

	String addBatch(BatchDTO batchDTO);

	String addMentor(MentorDTO mentorDTO);

	List<BatchDTO> getBatchList();

	List<String> getMentorNames();

	List<String> getTechnologies();

	String updateBatch(BatchDTO batchDTO);

	void deleteBatch(String batchId);

	List<RegistrationRequestDTO> getRegistrationRequests();

	void approveEmployeeRequest(String employeeId);

	void rejectEmployeeRequest(RejectionReasonDTO reasonDTO);

	String updateMentor(MentorDTO mentorDTO);

	void deleteMentor(String mentorName);

	List<MentorDTO> getMentorList();

	List<String> getBatchNames();

	List<String> getBatchIds();

	List<String> getStatus();
	
	void addEmployeeToBatch(String employeeId, String batchId);

}
