package org.samarBg.controllers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.view.AddOfferHorseViewModel;
import org.samarBg.view.OfferViewModel;
import org.samarBg.view.UserProfileViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class UserProfileController {

    private final UserService userService;
    private final OfferService offerService;


    public UserProfileController(UserService userService,
                                 OfferService offerService) {
        this.userService = userService;
        this.offerService = offerService;
    }


    @GetMapping("/userProfileDetail")
    public String showUserProfile() {
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
