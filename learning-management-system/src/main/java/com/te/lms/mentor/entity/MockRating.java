package com.te.lms.mentor.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.te.lms.admin.entity.Mentor;
import com.te.lms.employee.entity.Employee;
import com.te.lms.mentor.enums.MockType;
import com.te.lms.mentor.enums.OverallFeedback;
import com.te.lms.mentor.enums.Technology;

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
@Table(name = "mock_details")
public class MockRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mockRatingId;
	private MockType mockType;
	@Enumerated(EnumType.STRING)
	private Technology technology;
	@ManyToOne(cascade = CascadeType.ALL)
	private Mentor mentor;
	private int practicalKnowledge;
	private int theoreticalKnowledge;
	private OverallFeedback overallFeedback;
	private String detailedFeedback;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
}
