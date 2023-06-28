package com.employe.service;

import java.util.List;

import com.employe.DTO.EmpRequest;
import com.employe.Entity.EmpModel;

public interface EmpService {

	// post
	EmpModel saveEmpDetail(EmpRequest empRequest);

//	 get
	List<EmpModel> getAllStudent();

	// delete
	void deleteEmpByid(long id);

	// put operation for postman api for check
	EmpModel update(EmpRequest empRequest, long id);
	
	// PUT OPERATION USING UPDATE PAGE
	EmpModel updateEmp(EmpModel empModel, long id);

	// check user exit or not
	EmpModel findByemail(String email);

}
