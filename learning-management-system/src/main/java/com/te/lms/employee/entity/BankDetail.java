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

import com.te.lms.employee.enums.AccountType;

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
@Table(name = "employee_bank_details_info")
public class BankDetail {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int bankDetailId;
	private long accountNumber;
	private String bankName;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private String ifscCode;
	private String branch;
	private String state;
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;
}
