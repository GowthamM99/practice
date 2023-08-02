package com.te.lms.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class Technology {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int technologyId;
	private String technologyName;
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "technologies")
	private List<Batch> batches;
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "technologies")
	private List<Mentor> mentors;
}
