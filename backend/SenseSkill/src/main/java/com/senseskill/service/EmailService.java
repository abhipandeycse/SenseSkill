package com.senseskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("SenseSkill - Verify your Email");
        message.setText("Hello,\n\nYour verification code is: " + code + "\n\nThanks,\nSenseSkill Team");
        mailSender.send(message);
    }
    
    public void sendEmail(String to, String code) {
    	String resetUrl = "http://localhost:3000/reset-password?token=" + code;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("SenseSkill - Recover the password");
        message.setText("Hello,\n\nClick the link below to reset your password:\n" + resetUrl + "\n\nThis link is valid for 15 minutes.\n\nThanks,\nSenseSkill Team");
        mailSender.send(message);
    }
}
