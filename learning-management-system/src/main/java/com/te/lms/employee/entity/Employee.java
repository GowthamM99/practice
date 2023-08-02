package com.te.lms.employee.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.checkerframework.common.aliasing.qual.Unique;

import com.te.lms.admin.entity.Batch;
import com.te.lms.employee.enums.Designation;
import com.te.lms.employee.enums.EmployeeStatus;
import com.te.lms.employee.enums.Gender;
import com.te.lms.mentor.entity.MockRating;

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
@Table(name = "employee_primary_info")
public class Employee {
	@Id
	private String employeeId;
	private String employeeName;
	private LocalDate dateOfJoining;
	private double salary;
	private LocalDate dateOfBirth;
	@Unique
	@Email(message = "Invalid Email Format")
	private String emailId;
	private String bloodGroup;
	@Enumerated(EnumType.STRING)
	private Designation designation;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String nationality;
	@Enumerated(EnumType.STRING)
	private EmployeeStatus employeeStatus;
	private boolean isRejected;
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeSecondaryInfo employeeSecondaryInfo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Education> educationDetails;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Address> addressDetails;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BankDetail bankDetail;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<TechnicalSkill> technicalSkills;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Experience> experiences;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Contact> contacts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Attendance> attendances;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<MockRating> mockRatings;
	@ManyToOne(cascade = CascadeType.ALL)
	private Batch batch;
}
