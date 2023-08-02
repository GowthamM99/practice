package com.te.esd.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {

	private final JavaMailSender mailSender;
	
	public String sendEmail(String toEmail,String subject,String body) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
		return "Mail without attachment Sent Successfully...";
	}
	
	public String sendEmailWithAttachment(String toEmail,String subject,String body,String attachment) throws MessagingException {
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(body);
		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
		mailSender.send(mimeMessage);
		return "Mail with attachment Sent Successfully...";
	}
}
