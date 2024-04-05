package org.samarBg.controllers;


import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.implementation.UserServiceImpl;
import org.samarBg.view.UserLoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;

@Controller
public class LoginPageController {

    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;

    // БИсквитка
    private final TokenBasedRememberMeServices rememberMeServices;

    @Autowired
    public LoginPageController(UserServiceImpl userServiceImpl,
                               UserRepository userRepository,
                               TokenBasedRememberMeServices rememberMeServices) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.rememberMeServices = rememberMeServices;
    }

    @ModelAttribute("userModel")
    public UserLoginViewModel userModel() {
        return new UserLoginViewModel();
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        return "login";
    }


    @PostMapping("/user/login")
    public String login(@Valid @ModelAttribute("userModel") UserLoginViewModel userModel,
                        RedirectAttributes redirectAttributes,
                        HttpServletResponse response,
                        HttpServletRequest request) {

        // Търсене на потребител в базата данни по имейла
        Optional<UserEntity> userOptional = userServiceImpl.findUserByEmail(userModel.getEmail());

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Аутентикация на потребителя чрез Spring Security
            Authentication authentication = userServiceImpl.authenticateUser(userModel.getEmail(), userModel.getPassword());

            if (authentication != null) {
                // Потребителят е аутентикиран
                if (user.isActive()) {
                    // Потребителят е активен
                    user.setLastOnline(Instant.now());
                    userRepository.save(user);
                    // Създаване на RememberMe кукито
                    rememberMeServices.loginSuccess(request, response, authentication);
                    return "redirect:/";
                } else {
                    // Потребителят не е активен
                    redirectAttributes.addFlashAttribute("error", "Вашият акаунт не е активен. За да го активирате, " +
                            "кликнете на изпратения от нас ЛИНК за активация");
                    return "redirect:/login";
                }
            } else {
                // Грешна парола
                redirectAttributes.addFlashAttribute("error", "Грешна парола!");
                return "redirect:/login";
            }
        } else {
            // Потребителят не е намерен в системата
            redirectAttributes.addFlashAttribute("error", "Потребил с Имейл -> "+ userModel.getEmail() +" не е намерен в системата!");
            return "redirect:/login";
        }
    }

}

