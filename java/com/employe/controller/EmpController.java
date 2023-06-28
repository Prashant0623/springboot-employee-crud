package com.employe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employe.DTO.EmpRequest;
import com.employe.repository.EmpRepo;
import com.employe.service.EmpService;

// controller for crud operations 
@Controller
public class EmpController {

	@Autowired
	EmpRepo empRepo;
	@Autowired
	EmpService empService;

	@PostMapping("/save")
	public String empString(@RequestBody EmpRequest empRequest) {
		empService.saveEmpDetail(empRequest);
		return "your record saved successfully";

	}

//	@GetMapping("/fatch")
//	public List<EmpModel> getList(){
//		return empService.getAllStudent();
//	}
//	

	@RequestMapping("/delete/{id}")
	public String deleteByid(@PathVariable("id") long id) {
		empService.deleteEmpByid(id);
		return "redirect:/ShowRegister";
		
	}
	
	// successfully done put method
	@PutMapping("/update/{id}")
	public void update(@PathVariable long id, @RequestBody EmpRequest empRequest) {
		empService.update(empRequest, id);
	}

}