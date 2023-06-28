package com.employe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employe.Entity.EmpEmailModel;
import com.employe.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmpEmailModel empEmailModel) {
		String status = emailService.SendSimpleEmail(empEmailModel);
		return status;

	}

	@PostMapping("/sendEmailAttachment")
	public String sendAttachment(@RequestBody EmpEmailModel empEmailModel) {
		String status = emailService.SendMailWithAttachment(empEmailModel);
		return status;
	}

}
