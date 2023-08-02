package com.te.lms.employee.entity;

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
@Entity
@Table(name = "employee_address_info")
public class Address {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int addressId;
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	private String doorNumber;
	private String street;
	private String locality;
	private String city;
	private String state;
	private long pinCode;
	private String landMark;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Employee employee;
}
