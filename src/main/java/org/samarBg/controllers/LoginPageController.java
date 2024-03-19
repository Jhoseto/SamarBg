package org.samarBg.controllers;


import org.samarBg.model.entities.UserEntity;
import org.samarBg.service.UserService;
import org.samarBg.view.UserLoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginPageController {

    private final UserService userService;


    @Autowired
    private TokenBasedRememberMeServices rememberMeServices;

    @Autowired
    public LoginPageController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserLoginViewModel userModel() {
        return new UserLoginViewModel();
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }


    @PostMapping("/user/login")
    public String login(@Valid @ModelAttribute("userModel") UserLoginViewModel userModel,
                        RedirectAttributes redirectAttributes,
                        HttpServletResponse response,
                        HttpServletRequest request) {

        // Аутентикация на потребителя чрез Spring Security
        Authentication authentication = userService.authenticateUser(userModel.getEmail(), userModel.getPassword());
        Optional<UserEntity> userOptional = userService.findUserByEmail(userModel.getEmail());
        UserEntity user = userOptional.get();

        if (authentication != null) {
            // Потребителят е аутентикиран


            if (user.isActive()) {
                // Потребителят е активен
                // Създаване на кукито за сесия
                rememberMeServices.loginSuccess(request, response, authentication);

                return "redirect:/";
            } else {
                // Потребителят не е активен
                redirectAttributes.addFlashAttribute("error", "Вашият акаунт не е активен. За да го активирате, " +
                        "кликнете на изпратения от нас ЛИНК за активация");
                return "redirect:/login";
            }
        }else {

            redirectAttributes.addFlashAttribute("error", "Грешенa парола !");
            return "redirect:/login";
        }

    }

//    @PostMapping ("/user/logout")
//    public String logout() {
//        userService.logoutCurrentUser();
//        return "redirect:/index";
//    }
}

