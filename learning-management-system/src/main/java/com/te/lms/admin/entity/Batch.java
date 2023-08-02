package com.te.lms.admin.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.te.lms.admin.enums.BatchStatus;
import com.te.lms.employee.entity.Employee;
import com.te.lms.mentor.entity.ScheduledMock;

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
public class Batch {
	@Id
	private String batchId;
	private String batchName;
	private LocalDate startDate;
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	private BatchStatus status;
	private boolean isDeleted;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Technology> technologies;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "batch")
	private List<Employee> employees;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "batch")
	private List<ScheduledMock> mocks;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Mentor> mentors;
}
