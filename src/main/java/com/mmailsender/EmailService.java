// package com.mmailsender;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;
// import org.thymeleaf.TemplateEngine;
// import org.thymeleaf.context.Context;

// import javax.mail.MessagingException;
// import javax.mail.internet.MimeMessage;

// @Service
// public class EmailService {

//     @Autowired
//     private JavaMailSender emailSender;

//     @Autowired
//     private TemplateEngine templateEngine;

//     public void sendTemplateEmail(String to, String subject, String templateName, Context context) {
//         MimeMessage message = emailSender.createMimeMessage();
//         MimeMessageHelper helper = new MimeMessageHelper(message);

//         try {
//             helper.setTo(to);
//             helper.setSubject(subject);
           
//             String content = templateEngine.process(templateName, context);
//             helper.setText(content, true);

//             emailSender.send(message);
//         } catch (MessagingException e) {
//             e.printStackTrace(); 
//         }
//     }
// }
