package com.te.lms.employee.dto;

import com.te.lms.employee.enums.AddressType;

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
public class AddressDTO {
	private AddressType addressType;
	private String doorNumber;
	private String street;
	private String locality;
	private String city;
	private String state;
	private long pinCode;
	private String landMark;
}
