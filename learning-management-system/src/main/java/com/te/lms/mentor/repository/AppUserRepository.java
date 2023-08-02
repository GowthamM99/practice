package com.te.lms.mentor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.mentor.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String>{

}
