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

import com.te.lms.employee.enums.ContactType;

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
@Table(name = "employee_contact_info")
public class Contact {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int contactId;
	@Enumerated(EnumType.STRING)
	private ContactType contactType;
	private long contactNumber;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
}
