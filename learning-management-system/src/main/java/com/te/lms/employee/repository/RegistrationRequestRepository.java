package com.te.lms.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.employee.entity.RegistrationRequest;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer>{

	List<RegistrationRequest> findByAdminActionTaken(boolean adminActionTaken);

	RegistrationRequest findByEmployeeId(String employeeId);

}
