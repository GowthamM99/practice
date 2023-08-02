package com.te.lms.employee.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.te.lms.employee.enums.Designation;

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
@Table(name = "employee_experience_info")
public class Experience {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int experienceId;
	private String companyName;
	private double yearOfExperience;
	private LocalDate dateOfJoining;
	private LocalDate dateOfRelieving;
	@Enumerated(EnumType.STRING)
	private Designation designation;
	private String location;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Employee employee;
}
