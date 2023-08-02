package com.te.lms.mentor.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.te.lms.admin.entity.Batch;
import com.te.lms.admin.entity.Mentor;
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
public class ScheduledMock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mockId;
	private int mockNumber;
	private Technology technology;
	@ManyToOne(cascade = CascadeType.ALL)
	private Batch batch;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Mentor> mentors;
	private LocalDateTime dateAndTime;
}
