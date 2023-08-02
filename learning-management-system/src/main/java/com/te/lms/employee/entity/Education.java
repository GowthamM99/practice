package com.te.lms.employee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.te.lms.employee.enums.EducationType;

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
@Table(name = "employee_education_info")
public class Education {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int educationId;
	@Enumerated(EnumType.STRING)
	private EducationType educationType;
	private int yearOfPassing;
	private double percentage;
	private String universityName;
	private String instituteName;
	private String specialization;
	private String state;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
}
