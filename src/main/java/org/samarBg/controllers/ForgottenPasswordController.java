package org.samarBg.controllers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.view.ForgottenPasswordViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Random;

@Controller
public class ForgottenPasswordController {

    private final UserRepository userRepository;
    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ForgottenPasswordController(UserRepository userRepository, JavaMailSender emailSender, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("userModel")
    public ForgottenPasswordViewModel userModel (){
        return new ForgottenPasswordViewModel();
    }

    @GetMapping("/forgotten_password")
    public String showForgottenPasswordForm(){
        return "forgotten_password";
    }

    @PostMapping("/forgotten_password")
    public ModelAndView processForgottenPassword(@RequestParam("email") String email) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            modelAndView.addObject("error", "Грешка: Потребителят с този имейл адрес не съществува.");
            modelAndView.setViewName("forgotten_password");
            return modelAndView;
        }

        UserEntity user = userOptional.get();

        String newPassword = generateRandomPassword();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Вашата нова парола");
        message.setText("Вашата нова парола е: " + newPassword);
        emailSender.send(message);

        userRepository.save(user);

        modelAndView.addObject("successMessage", "Новата парола е изпратена успешно на вашия имейл адрес.");
        modelAndView.setViewName("forgotten_password");
        return modelAndView;
    }

    private String generateRandomPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));

        for (int i = 0; i < 2; i++) {
            char randomChar = upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length()));
            password.insert(random.nextInt(password.length()), randomChar);
        }

        return password.toString();
    }
}
