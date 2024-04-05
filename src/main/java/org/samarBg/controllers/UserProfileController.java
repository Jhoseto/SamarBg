package org.samarBg.controllers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.service.CurrentUserService;
import org.samarBg.service.implementation.OfferServiceImpl;
import org.samarBg.service.implementation.UserServiceImpl;
import org.samarBg.view.OfferViewModel;
import org.samarBg.view.UserProfileViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class UserProfileController {

    private final UserServiceImpl userService;
    private final OfferServiceImpl offerService;
    private final CurrentUserService currentUserService;


    public UserProfileController(UserServiceImpl userService,
                                 OfferServiceImpl offerService,
                                 CurrentUserService currentUserService) {
        this.userService = userService;
        this.offerService = offerService;
        this.currentUserService = currentUserService;
    }

    @GetMapping("/user-detail")
    public String showUserDetail(Model model) {
        String username = currentUserService.getCurrentUser().getUsername();
        List<OfferViewModel> userOffers = offerService.getAllOffersForUser(username);
        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();

        userProfileViewModel.setUserOffers(userOffers);
        model.addAttribute("offer", userProfileViewModel);
        return "user-detail";
    }
    @GetMapping("/userProfileDetail")
    public String showUserProfile(Model model) {
        // Извличане на атрибута "user" от модела
        UserProfileViewModel userProfileViewModel = (UserProfileViewModel) model.getAttribute("user");
        model.addAttribute("user",userProfileViewModel);
        return "userProfileDetail";
    }
    @PostMapping("/getProfileDetail/{username}")
    public String viewUserProfileDetail(@PathVariable String username, RedirectAttributes redirectAttributes) {
        // Намираме потребителя по потребителското име
        UserEntity user = userService.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.setUserName(user.getUsername());
        userProfileViewModel.setRealName(user.getRealName());
        userProfileViewModel.setEmail(user.getEmail());
        userProfileViewModel.setPhone(user.getPhone());
        userProfileViewModel.setCity(user.getCity());
        userProfileViewModel.setProfileImageUrl(user.getImageUrl());
        userProfileViewModel.setLastOnline(user.getLastOnline());

        // Вземаме обявите на потребителя от сервиза за обяви
        List<OfferViewModel> userOffers = offerService.getAllOffersForUser(username);
        userProfileViewModel.setUserOffers(userOffers);

        // Пренасочване с добавяне на атрибут "user"
        redirectAttributes.addFlashAttribute("user", userProfileViewModel);
        return "redirect:/userProfileDetail";
    }
}
