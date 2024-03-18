package org.samarBg.controllers;

import org.samarBg.model.entities.BaseEntity;
import org.samarBg.model.entities.UserEntity;
import org.samarBg.model.entities.UserRoleEntity;
import org.samarBg.model.entities.enums.UserRoleEnum;
import org.samarBg.repository.UserRepository;
import org.samarBg.repository.UserRoleRepository;
import org.samarBg.service.ConfirmationLinkService;
import org.samarBg.service.EmailService;
import org.samarBg.view.UserRegistrationViewModel;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class RegisterPageController {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private ConfirmationLinkService confirmationLinkService;


    @Autowired
    private EmailService emailService;

    @Autowired
    public RegisterPageController(PasswordEncoder passwordEncoder,
                                  UserRepository userRepository,
                                  UserRoleRepository userRoleRepository,
                                  ConfirmationLinkService confirmationLinkService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.confirmationLinkService = confirmationLinkService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationViewModel());
        return "registration";
    }


    @PostMapping("/user/registration")
    public String register(@Valid UserRegistrationViewModel userRegistrationViewModel,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        StringBuilder errorMessages = new StringBuilder();

        if (result.hasErrors()) {
            // Проверка за грешки във всички полета и събиране на съобщенията
            if (result.hasFieldErrors("username")) {
                FieldError error = result.getFieldError("username");
                if (error != null) {
                    errorMessages.append(error.getDefaultMessage());
                }
            }
            if (result.hasFieldErrors("email")) {
                FieldError error = result.getFieldError("email");
                if (error != null) {
                    errorMessages.append(error.getDefaultMessage());
                }
            }
            if (result.hasFieldErrors("regPassword")) {
                FieldError error = result.getFieldError("regPassword");
                if (error != null) {
                    errorMessages.append(error.getDefaultMessage());
                }
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
                    "Регистрацията е успешна!\nМоля преверете вашия Имейл за да активирате вашия профил.");

            //Запис на Нов профил
            intUsers(userRegistrationViewModel);

            return "redirect:/registration";
        }
    }


    //инцилизация на нов профил
    private void intUsers(UserRegistrationViewModel userRegistrationViewModel){
        // Създаване на потребителски роли
        UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
        userRoleRepository.saveAll(List.of(adminRole, userRole));

        UserEntity newUser = new UserEntity();
        String confirmationLink = confirmationLinkService.generateConfirmationLink(newUser); // Генериране на целия линк
        String confirmationCode = generateConfirmationCode();

        newUser.setUsername(userRegistrationViewModel.getUsername())
                .setPassword(passwordEncoder.encode(userRegistrationViewModel.getRegPassword()))
                .setEmail(userRegistrationViewModel.getEmail())
                .setActive(false)
                .setImageUrl("images/userNameIcon1.png")
                .setUserConfirmationCode(confirmationCode)
                .setUserRoles(Collections.singleton(UserRoleEnum.USER));
        setCurrentTimeStamps(newUser);


        // Записване на новия потребителски профил в базата данни
        userRepository.save(newUser);

        // Изпращане на потвърждаващ имейл с целия линк за потвърждение на имейл
        emailService.sendConfirmationEmail(newUser.getEmail(), confirmationLink+newUser.getUserConfirmationCode());

        System.out.println("Email sended to "+newUser.getEmail());
    }

    //Времето на създаване и ъпдейтване на профила
    public static void setCurrentTimeStamps(BaseEntity baseEntity){
        baseEntity.setCreated(Instant.now());
        baseEntity.setModified(Instant.now());
    }

    // Генериране на уникален код за потвърждение на имейл
    private String generateConfirmationCode() {
        // Генериране на уникален код за потвърждение (например, UUID)
        return UUID.randomUUID().toString();
    }
}


