package com.te.lms.mentor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.admin.entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, String>{

	Optional<Mentor> findByMentorName(String mentorName);

	List<Mentor> findByIsDeleted(boolean b);

}
