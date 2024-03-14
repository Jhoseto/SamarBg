package org.samarBg.service;

import org.samarBg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender emailSender;
    private UserRepository userRepository;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendConfirmationEmail(String recipientEmail, String confirmationLink) throws MailException {
        // Създаване на обект за изпращане на имейл
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // Задаване на адреса на получателя
        mailMessage.setTo(recipientEmail);
        // Задаване на темата на имейла
        mailMessage.setSubject("Samar.bg - Потвърждение на регистрация");
        // Задаване на съдържанието на имейла с целия линк за потвърждение
        mailMessage.setText("Кликнете на линка за да потвърдите вашият Email адрес.\n"+confirmationLink);

        // Изпращане на имейла
        emailSender.send(mailMessage);
    }


    public void sendForgottenPasswordEmail(String recipientEmail, String confirmationLink) throws MailException {
        // Създаване на обект за изпращане на имейл
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // Задаване на адреса на получателя
        mailMessage.setTo(recipientEmail);
        // Задаване на темата на имейла
        mailMessage.setSubject("Samar.bg - Потвърждение на регистрация");
        // Задаване на съдържанието на имейла с целия линк за потвърждение
        mailMessage.setText("Кликнете на линка за да потвърдите вашият Email адрес.\n"+confirmationLink);

        // Изпращане на имейла
        emailSender.send(mailMessage);
    }
}
