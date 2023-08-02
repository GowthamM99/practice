package com.te.lms.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.admin.entity.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Integer>{

	Technology findByTechnologyName(String technologyName);

}
