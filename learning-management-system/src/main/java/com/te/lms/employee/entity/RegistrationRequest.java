package com.te.lms.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class RegistrationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int registrationId;
	private String employeeId;
	private boolean isApproved;
	private String rejectReason;
	private boolean adminActionTaken;
}
