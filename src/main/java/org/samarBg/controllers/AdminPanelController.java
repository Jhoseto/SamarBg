package org.samarBg.controllers;

import org.samarBg.service.UserService;
import org.samarBg.views.UserProfileViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminPanelController {

    private final UserService userService;

    public AdminPanelController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/adminPanel")
    public String showAllOfferDetailPage(Model model) {
        List<UserProfileViewModel> users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "adminPanel";
    }


    @PostMapping("/changeUserRole/{userId}")
    public String changeUserRole(@PathVariable Long userId) {
        userService.changeUserRole(userId);

        return "redirect:/adminPanel";
    }

    @PostMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return "redirect:/adminPanel";
    }
}
