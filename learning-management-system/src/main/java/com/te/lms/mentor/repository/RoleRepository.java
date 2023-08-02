package com.te.lms.mentor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.mentor.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRoleName(String string);

}
