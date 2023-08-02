package com.te.lms.mentor.service;

import java.util.List;

import com.te.lms.admin.dto.BatchDTO;
import com.te.lms.mentor.dto.AttendanceUpdateDTO;
import com.te.lms.mentor.dto.BatchAttendanceDTO;
import com.te.lms.mentor.dto.BatchDashboardDTO;
import com.te.lms.mentor.dto.EmployeeBatchDTO;

public interface MentorService {

	List<BatchDTO> getBatches(String mentorName);

	List<EmployeeBatchDTO> getEmployeesFromBatch(String batchId);

	List<String> getBatcheNames(String mentorName);

	BatchDashboardDTO getBatchDashboard(String batchName);

	List<AttendanceUpdateDTO> getBatchAttendance(String batchId);

	void updateBatchAttendance(BatchAttendanceDTO attendanceDTO);

}
