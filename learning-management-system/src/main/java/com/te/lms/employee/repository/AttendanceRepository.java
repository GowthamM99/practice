package com.te.lms.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.employee.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{

}
