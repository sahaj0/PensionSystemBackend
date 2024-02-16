package com.processPensionMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.processPensionMicroservice.model.PensionDetail;


public interface ProcessPensionRepository extends JpaRepository<PensionDetail, Long>{

}
