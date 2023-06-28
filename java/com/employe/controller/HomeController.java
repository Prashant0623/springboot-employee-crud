package com.employe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.employe.DTO.EmpRequest;
import com.employe.Entity.EmpModel;
import com.employe.repository.EmpRepo;
import com.employe.service.EmpService;

@Controller
public class HomeController {

	@Autowired
	EmpRepo empRepo;
	@Autowired
	EmpService empService;

	// SHOW REGISTER PAGE
	@GetMapping("/ShowRegister")
	public String index() {
		System.out.println("Index Page");
		return "index";
	}

	// SAVE DATA FROM REGISTER PAGE IN DATABASE
	@PostMapping("/InsertData")
	public String SaveEmp(@ModelAttribute("EmpModel") EmpRequest empRequest, ModelMap modelMap) {
		EmpModel SaveEmp = empService.saveEmpDetail(empRequest);
		String Message = "save with id" + SaveEmp.getId();
		modelMap.addAttribute("msg", Message);
		return "index";
	}

	// SHOW LOGIN PAGE
	@GetMapping("/login")
	public String login() {
		System.out.println("login page ");
		return "login";

	} 
	// CHECK EMPLOYEE EXIST OR NOT VIA LOGIN INFORMATION
	@PostMapping("/checkLogin")
	public String LoginDetail(@ModelAttribute ModelMap modelMap, String email, String password) {

		EmpModel user = empService.findByemail(email);

		if (user.getPassword().equals(password)) {
			modelMap.addAttribute("msg", "successfully login !!");
			return "redirect:/welcome";

		} else {
			modelMap.addAttribute("message", "provide correct username and password");
			return "login";
		}

	}

	// SHOW THE LIST OF EMPLOYEE
	@GetMapping("/welcome")
	public String empList(ModelMap modelMap) {
		System.out.println("welcome page");

		List<EmpModel> allStudent = empService.getAllStudent();
		// System.out.println(allStudent);
		modelMap.addAttribute("emplist", allStudent);

		return "Welcome";
	}

	// CREATE NEW EMPLOYEE USING LINK ON WELCOME PAGE
	@RequestMapping("/AddNew")
	public String NewEmplyoee(ModelMap modelMap) {
		System.out.println("Create New Employee");
		List<EmpModel> employee = empService.getAllStudent();
		modelMap.addAttribute("emplist", employee);
		return "CreateNewEmployee";

	}

	// SHOW UPDATE PAGE VIA UPDATE LINK ON WELCOME PAGE
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView getEditpage(@PathVariable("id") long id) {
		System.out.println("update page");
		ModelAndView mav = new ModelAndView("Update");
		Optional<EmpModel> findById = empRepo.findById(id);
		mav.addObject("student", findById);
		return mav;
	}

	// UPDATE DATA IN EXISTING ID OF EMPLOYEE
	@PostMapping("/edit")
	public String updatedata(EmpModel empModel, long id) {
		System.out.println("edit page");
		EmpModel model = empService.updateEmp(empModel, id);
		return "index";
	}

}
