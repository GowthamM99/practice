package com.te.lms.employee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.te.lms.employee.enums.MaritalStatus;

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
@Entity
@Table(name = "employee_secondary_info")
public class EmployeeSecondaryInfo {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int employeeSecondaryInfoId;
	private String panNumber;
	private long aadharNumber;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String passportNumber;
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "employeeSecondaryInfo")
	private Employee employee;
}
