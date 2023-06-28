package com.employe.serviceImpl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.employe.Entity.EmpEmailModel;
import com.employe.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired(required = true)
	JavaMailSender javaMailSender;

	@Override
	public String SendSimpleEmail(EmpEmailModel empEmailModel) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("mehtaprqshant@gmail.com"); // sender
			msg.setTo(empEmailModel.getRecipient());
			msg.setSubject(empEmailModel.getSubject());
			msg.setText(empEmailModel.getBody());

			javaMailSender.send(msg);

			return " email sent successfully....!";

		} catch (Exception e) {
			e.printStackTrace();
			return " error while sending mail";
		}

	}

	@Override
	public String SendMailWithAttachment(EmpEmailModel empEmailModel) {

		MimeMessage mineMessage = javaMailSender.createMimeMessage();
		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mineMessage, true);
			mimeMessageHelper.setFrom("mehtaprqshant@gmail.com");
			mimeMessageHelper.setTo(empEmailModel.getRecipient());
			mimeMessageHelper.setSubject(empEmailModel.getSubject());
			mimeMessageHelper.setText(empEmailModel.getBody());

			FileSystemResource fileSystemResource = new FileSystemResource(
					new File("/Users/prashantmehta/Downloads/ResumeNew.pdf"));
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			javaMailSender.send(mineMessage);
			return "mail sent successfully";

		} catch (Exception e) {
			return " error while sending email";
		}

	}
}
