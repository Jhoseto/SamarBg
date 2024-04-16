package org.samarBg.controllers;

import org.samarBg.models.BaseEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.models.enums.UserRole;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.ConfirmationLinkService;
import org.samarBg.service.EmailService;
import org.samarBg.views.UserRegistrationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.Instant;
import java.util.*;

@Controller
public class RegisterPageController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ConfirmationLinkService confirmationLinkService;
    private final EmailService emailService;

    @Autowired
    public RegisterPageController(PasswordEncoder passwordEncoder,
                                  UserRepository userRepository,
                                  ConfirmationLinkService confirmationLinkService,
                                  EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.confirmationLinkService = confirmationLinkService;
        this.emailService = emailService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userFields", new UserRegistrationViewModel());
        return "registration";
    }


    @PostMapping("/user/registration")
    public String register(@Valid UserRegistrationViewModel userRegistrationViewModel,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        StringBuilder errorMessages = new StringBuilder();

        if (result.hasErrors()) {
            // Проверка за грешки във всички полета и събиране на съобщенията
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            }

            if (!userRegistrationViewModel.isPasswordsMatch()) {
                errorMessages.append("Паролите не съвпадат. ");
            }


            // Добавяне на общото съобщение за грешките към модела
            redirectAttributes.addFlashAttribute("error", errorMessages.toString());

            // Връщаме страницата за регистрация със специфични съобщения за грешките
            return "redirect:/registration";
        } else {
            // Проверка дали вече съществува потребител с дадения имейл
            Optional<UserEntity> existingUserByEmail = userRepository.findByEmail(userRegistrationViewModel.getEmail());
            Optional<UserEntity> existingUserByUsername = userRepository.findByUsername(userRegistrationViewModel.getUsername());

            if (existingUserByEmail.isPresent()) {
                errorMessages.append("Потребител с този имейл адрес вече съществува! Изберете друг.");
                redirectAttributes.addFlashAttribute("error", errorMessages.toString());
                return "redirect:/registration";

            }

            // Проверка дали вече съществува потребител с даденото потребителско име
            if (existingUserByUsername.isPresent()) {
                errorMessages.append("Потребител с това потребителско име вече съществува! Изберете друго.");
                redirectAttributes.addFlashAttribute("error", errorMessages.toString());
                return "redirect:/registration";
            }

            redirectAttributes.addFlashAttribute("successMessage",
                    "Регистрацията е успешна!\nМоля проверете вашия Имейл за да активирате вашия профил.");

            //Запис на Нов профил
            intUsers(userRegistrationViewModel);

        }
        return "redirect:/registration";
    }


    private void intUsers(UserRegistrationViewModel userRegistrationViewModel) {
        // Зареждане на ролята USER
        UserRole userRole = UserRole.USER;

        // Инициализиране на нов потребителски профил
        UserEntity newUser = new UserEntity();
        String confirmationCode = generateConfirmationCode();
        String confirmationLink = confirmationLinkService.generateConfirmationLink(newUser);

        newUser.setUsername(userRegistrationViewModel.getUsername())
                .setPassword(passwordEncoder.encode(userRegistrationViewModel.getRegPassword()))
                .setEmail(userRegistrationViewModel.getEmail())
                .setActive(false)
                .setImageUrl("images/userNameIcon1.png")
                .setUserConfirmationCode(confirmationCode)
                .setRole(userRole); // Задаване на ролята

        setCurrentTimeStamps(newUser);

        // Записване на новия потребителски профил в базата данни
        userRepository.save(newUser);

        // Изпращане на потвърждаващ имейл с целия линк за потвърждение на имейл
        emailService.sendConfirmationEmail(newUser.getEmail(), confirmationLink + newUser.getUserConfirmationCode());

        System.out.println("Email sent to " + newUser.getEmail());
    }

    //Времето на създаване и ъпдейтване
    public static void setCurrentTimeStamps(BaseEntity baseEntity){
        baseEntity.setCreated(Instant.now());
        baseEntity.setModified(Instant.now());
    }

    // Генериране на уникален код за потвърждение на имейл
    private String generateConfirmationCode() {
        // Генериране на уникален код за потвърждение (UUID)
        return UUID.randomUUID().toString();
    }
}


