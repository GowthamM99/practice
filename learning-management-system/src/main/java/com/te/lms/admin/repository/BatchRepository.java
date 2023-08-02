package com.te.lms.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.admin.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, String>{

	List<Batch> findByIsDeleted(boolean b);

	Batch findByBatchName(String batchName);

}
