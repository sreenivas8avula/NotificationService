package com.sree.banking.notification.service;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sree.banking.notification.exceptions.EmailNotificationException;
import com.sree.banking.notification.model.EmailDetails;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
/**
 * @author Sreenivasulu.Avula
 *
 */
@Service
public class EmailService {

	private static final Logger logger = LogManager.getLogger(EmailService.class);
		
	@Value("$spring.mail.username")
	private String sender;
	
	
	public String sendEmail(EmailDetails emailDetails)  {
	
		try {
			
			Session session = Session.getInstance(this.getProperties(), new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	            	return new PasswordAuthentication("your_email@gmail.com", "email_app_password");
	            }
	        });

	        session.setDebug(true);
	        
	        MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDetails.getRecipients()));

            message.setSubject(emailDetails.getSubject());

            message.setText(emailDetails.getMsgBody());

            logger.info("sending...");

            Transport.send(message);
           			
			logger.info("Mail Sent Successfully..");
			
			return "Mail Sent Successfully..";
			
		}catch(Exception exception) {
			logger.error(exception.getMessage());
			throw new EmailNotificationException(exception.getMessage());
		}
		
		
	}
	
	
	
	private Properties getProperties() {
		
		Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        return properties;
	}
	
}
