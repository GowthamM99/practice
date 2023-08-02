package com.te.esd.controller;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.esd.service.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/send")
@RestController
public class EmailAndSmsController {
	
	private final EmailService emailService;
	
	@PostMapping(path = "/mail")
	public ResponseEntity<String> sendEmailWithoutAttachment(@RequestParam String toEmail,
			@RequestParam String subject,@RequestParam String body){
		String message = emailService.sendEmail(toEmail, subject, body);
		return ResponseEntity.<String>ok(message);
	}
	
	@PostMapping(path = "/mail/attachment")
	public ResponseEntity<String> sendEmailWithAttachment(@RequestParam String toEmail,
			@RequestParam String subject,@RequestParam String body,@RequestParam String attachment) throws MessagingException{
		String message = emailService.sendEmailWithAttachment(toEmail, subject, body,attachment);
		return ResponseEntity.<String>ok(message);
	}
}
