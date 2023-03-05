package com.sree.banking.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sree.banking.notification.model.EmailDetails;
import com.sree.banking.notification.service.EmailService;
/**
 * @author Sreenivasulu.Avula
 *
 */

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping(value="/notification/sendmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendMail(@RequestBody EmailDetails emailDetails) {
		
		String status = emailService.sendEmail(emailDetails);
		
		return status;
	}

	@GetMapping("/notification/ping")
	public String ping() {
		return "Health check success.";
	}
}
