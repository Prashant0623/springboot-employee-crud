package com.employe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employe.Entity.EmpModel;

@Repository

public interface EmpRepo extends JpaRepository<EmpModel, Long> {
	EmpModel findByemail(String email);
	
	
	

}
