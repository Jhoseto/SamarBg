package org.samarBg.controllers;


import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.security.CurrentUser;
import org.samarBg.service.UserService;
import org.samarBg.view.UserLoginViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginPageController {

    private final UserService userService;
    private final UserRepository userRepository;

    public LoginPageController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @ModelAttribute("userModel")
    public UserLoginViewModel userModel (){
        return new UserLoginViewModel();
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/users/login")
    public String login(@Valid @ModelAttribute("userModel") UserLoginViewModel userModel,
                        RedirectAttributes redirectAttributes, CurrentUser currentUser) {

        boolean isAuthenticated = userService.authenticate(userModel.getEmail(), userModel.getPassword());

        if (!isAuthenticated) {
            redirectAttributes.addFlashAttribute("error", "Грешен имейл или парола.");
            return "redirect:/login";
        } else {
            Optional<UserEntity> userOptional = userRepository.findByEmail(userModel.getEmail());

            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                if (user.isActive()) {
                    // Потребителят е активен
                    userService.loginUser(userModel.getEmail());
                    return "redirect:/";
                } else {
                    // Потребителят не е активен
                    redirectAttributes.addFlashAttribute("error", "Вашият акаунт не е активен. За да го активирате трябва да кликнете" +
                            " на изпратения от нас ЛИНК за активация");
                    return "redirect:/login";
                }
            } else {
                // Потребителят не е намерен в системата, добавете съобщение за грешка и пренасочете потребителя към формата за вход
                redirectAttributes.addFlashAttribute("error", "Потребител с този имейл адрес не е намерен.");
                return "redirect:/login";
            }
        }
    }

    @GetMapping("/users/logout")
    public String logout() {
        userService.logoutCurrentUser();
        return "redirect:/index";
    }
}

