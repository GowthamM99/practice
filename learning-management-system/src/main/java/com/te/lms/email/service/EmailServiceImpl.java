package com.te.lms.email.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.te.lms.email.EmailDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{

	private final JavaMailSender mailSender;

	@Override
	public void sendEmail(EmailDetails emailDetails) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(emailDetails.getReceptientEmail());
		message.setSubject(emailDetails.getSubject());
		message.setText(emailDetails.getBody());
		
		mailSender.send(message);
	}
}
