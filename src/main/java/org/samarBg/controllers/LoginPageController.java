package org.samarBg.controllers;


import org.samarBg.models.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.UserService;
import org.samarBg.service.serviceImpl.UserServiceImpl;
import org.samarBg.views.UserLoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;

@Controller
public class LoginPageController {

    private final UserService userService;
    private final UserRepository userRepository;

    // Cookie
    private final TokenBasedRememberMeServices rememberMeServices;

    @Autowired
    public LoginPageController(UserService userService,
                               UserRepository userRepository,
                               TokenBasedRememberMeServices rememberMeServices) {
        this.userService = userService;
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

        Optional<UserEntity> userOptional = userService.findUserByEmail(userModel.getEmail());

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            if (user.isActive()) {
                Authentication authentication = userService.authenticateUser(userModel.getEmail(), userModel.getPassword());

                if (authentication != null) {
                    // User is autenticate
                    // User is active
                    user.setLastOnline(Instant.now());
                    userRepository.save(user);
                } else {
                    // Wrong password
                    redirectAttributes.addFlashAttribute("error", "Грешна парола!");
                    return "redirect:/login";
                }
                // Create RememberMe cooke
                rememberMeServices.loginSuccess(request, response, authentication);
                return "redirect:/";
            } else {
                // User is not active
                redirectAttributes.addFlashAttribute("error", "Вашият акаунт не е активен. За да го активирате, " +
                        "кликнете на изпратения от нас ЛИНК за активация");
                return "redirect:/login";
            }
        } else {
            // User not found in DB
            redirectAttributes.addFlashAttribute("error", "Потребил с Имейл -> "+ userModel.getEmail() +" не е намерен в системата!");
            return "redirect:/login";
        }
    }
}

