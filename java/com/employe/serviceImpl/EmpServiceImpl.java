package com.employe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employe.DTO.EmpRequest;
import com.employe.Entity.EmpModel;
import com.employe.repository.EmpRepo;
import com.employe.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpRepo empRepo;
	@Autowired
	EmpService empService;
	@Autowired
	PasswordEncoder passwordEncoder;

	// save data post method
	@Override
	public EmpModel saveEmpDetail(EmpRequest empRequest) {
		EmpModel emp = new EmpModel();
		emp.setAge(empRequest.getAge());
		emp.setEmail(empRequest.getEmail());
		emp.setName(empRequest.getName());
		emp.setPassword(passwordEncoder.encode(empRequest.getPassword()));
		return empRepo.save(emp);

	}

	// fatch data get mehtod
	@Override
	public List<EmpModel> getAllStudent() {

		List<EmpModel> empmodel = empRepo.findAll();

		return empmodel;

	}

	// delete method
	@Override
	public void deleteEmpByid(long id) {
		empRepo.deleteById(id);

	}

	// put declaration
	@Override
	public EmpModel update(EmpRequest empRequest, long id) {
		EmpModel emp = empRepo.findById(id).get();
		System.out.println("update entire row");
		emp.setAge(empRequest.getAge());
		emp.setName(empRequest.getName());
		emp.setEmail(empRequest.getEmail());
		emp.setPassword(passwordEncoder.encode(empRequest.getPassword()));
		return empRepo.save(emp);

	}

	// check user exit or not
	@Override
	public EmpModel findByemail(String email) {
		return empRepo.findByemail(email);

	}

	// put operation for edit data from update page
	@Override
	public EmpModel updateEmp(EmpModel empModel, long id) {
		EmpModel emp = empRepo.findById(id).get();
		emp.getId();
		emp.setName(empModel.getName());
		emp.setAge(empModel.getAge());
		emp.setEmail(empModel.getEmail());
		emp.setPassword(passwordEncoder.encode(empModel.getPassword()));
		return empRepo.save(emp);

	}

}
