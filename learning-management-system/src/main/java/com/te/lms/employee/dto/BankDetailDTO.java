package com.te.lms.employee.dto;

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
public class BankDetailDTO {
	private long accountNumber;
	private String bankName;
	private AccountType accountType;
	private String ifscCode;
	private String branch;
	private String state;
}
