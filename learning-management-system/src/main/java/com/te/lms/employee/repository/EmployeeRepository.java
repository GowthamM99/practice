package com.te.lms.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.employee.entity.Employee;
import com.te.lms.employee.enums.Designation;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

	List<Employee> findByDesignation(Designation trainee);

}
