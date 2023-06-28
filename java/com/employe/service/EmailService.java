package com.employe.service;

import com.employe.Entity.EmpEmailModel;

public interface EmailService {

	String SendSimpleEmail(EmpEmailModel empEmailModel);
	
	String SendMailWithAttachment(EmpEmailModel empEmailModel);

}
