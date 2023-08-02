package com.te.lms.employee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.te.lms.admin.entity.Mentor;

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
@Table(name = "employee_technicalskills_info")
public class TechnicalSkill {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int technicalSkillId;
	private String skillType;
	private int skillRating;
	private double yearOfExperience;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Employee employee;
	@ManyToOne(cascade = CascadeType.ALL)
	private Mentor mentor;
}
