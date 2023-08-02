package com.te.lms.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.te.lms.mentor.entity.MockRating;
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
public class Mentor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mentorId;
	private String employeeId;
	@Column(unique = true)
	private String mentorName;
	private String emailId;
	private boolean isDeleted;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Technology> technologies;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "mentors")
	private List<ScheduledMock> mocks;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "mentor")
	private List<MockRating> ratings;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "mentors")
	private List<Batch> batches;
}
