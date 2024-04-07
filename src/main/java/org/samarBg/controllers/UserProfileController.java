package org.samarBg.controllers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.service.CurrentUserService;
import org.samarBg.service.serviceImpl.OfferServiceImpl;
import org.samarBg.service.serviceImpl.UserServiceImpl;
import org.samarBg.view.OfferViewModel;
import org.samarBg.view.UserProfileViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.print.Pageable;
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
    public String showUserDetail(Model model,
                                 @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) org.springframework.data.domain.Pageable pageable) {
        String username = currentUserService.getCurrentUser().getUsername();
        List<OfferViewModel> userOffersPage = offerService.getAllOffersForUser(username);

        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.setUserOffers(userOffersPage); // Получаваме списъка с обяви от текущата страница

        model.addAttribute("userProfile", userProfileViewModel);
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

        // Създаваме нов Pageable обект за извличане на първата страница с обяви
        Pageable pageable = (Pageable) PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        userProfileViewModel.setUserName(user.getUsername());
        userProfileViewModel.setRealName(user.getRealName());
        userProfileViewModel.setEmail(user.getEmail());
        userProfileViewModel.setPhone(user.getPhone());
        userProfileViewModel.setCity(user.getCity());
        userProfileViewModel.setProfileImageUrl(user.getImageUrl());
        userProfileViewModel.setLastOnline(user.getLastOnline());

        // Вземаме обявите на потребителя от сервиза за обяви с подадения Pageable
        List<OfferViewModel> userOffersPage = offerService.getAllOffersForUser(username);
        List<OfferViewModel> userOffers = userOffersPage; // Вземаме списъка с обяви от текущата страница

        userProfileViewModel.setUserOffers(userOffers);

        // Пренасочваме с добавяне на атрибут "user"
        redirectAttributes.addFlashAttribute("user", userProfileViewModel);
        return "redirect:/userProfileDetail";
    }

}
