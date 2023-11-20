package com.mmailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/test")
	public String test() {
		return "Working...";
	}

	@GetMapping("/send-email")
	public void sendEmail() {
		
		Context context = new Context();
		context.setVariable("message", "this is a template email!");

		emailService.sendTemplateEmail("shoaibdar418@gmail.com", "Subject", "email-template", context);
	}

	@GetMapping("/send")
	public void send() throws IOException {

		final String username = "shoaibahmad400outlook.com";
		final String password = "Mathematics@425";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("shoaibahmad400outlook.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shoaibdar418@gmail.com"));
			message.setSubject("Test");
			message.setText("HI");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
