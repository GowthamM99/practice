package com.te.lms.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.employee.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
