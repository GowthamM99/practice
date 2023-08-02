package com.te.lms.employee.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendanceId;
	private LocalDate attendanceDate;
	private boolean isMorning;
	private boolean isAfternoon;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
}
