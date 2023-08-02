package com.te.lms.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.employee.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
