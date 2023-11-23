package com.mmailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.thymeleaf.context.Context;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;

@RestController
public class EmailController {

//	@Autowired
//	private EmailService emailService;

	@Autowired
	private JavaMailSender mailSender;

//	@GetMapping("/send-email")
//	public void sendEmail() {
//
//		Context context = new Context();
//		context.setVariable("message", "this is a template email!");
//
//		emailService.sendTemplateEmail("shoaibdar418@gmail.com", "Subject", "email-template", context);
//	}

	@GetMapping("/test")
	public String test() {
		return "working";
	}

	@GetMapping("/sendtextemail")
	public String sendPlainTextEmail(Model model) {
		String from = "darshoaib49@gmail.com";
		String to = "shoaibdar418@gmail.com";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("This is a plain text email");
		message.setText("Hello guys! This is a plain text email.");

		mailSender.send(message);

		model.addAttribute("message", "A plain text email has been sent");
		return "result";
	}

	@GetMapping("/send")
	public void sendOutlook() throws AddressException, MessagingException {
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "outlook.office365.com");
	        props.put("mail.smtp.port", 587);
	
	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("Test.OlamDirectMailer@olamnet.com", "5r53'B56BbK{t~9");
	            }
	        });
	
	       // try {
	            javax.mail.Message messageMail = new MimeMessage(session);
	            messageMail.setFrom(new InternetAddress("Test.OlamDirectMailer@olamnet.com"));
	            messageMail.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("shoaibdar418@gmail.com"));
	            messageMail.setSubject("subject");
	            //messageMail.setText("body");
		     try {
			String htmlContent = new String(Files.readAllBytes(Paths.get("/templates/email-template.html")));
				  messageMail.setContent(htmlContent, "text/html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            Transport.send(messageMail);
	
	            System.out.println("Email sent successfully!");

	}

	@GetMapping("send/textmail")
	public void sendTextEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("shoaibdar418@gmail.com");
		message.setSubject("subject");
		message.setText("body");

		mailSender.send(message);
	}
	
	

	
}
