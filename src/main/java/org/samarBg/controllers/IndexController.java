package org.samarBg.controllers;

import org.samarBg.models.UserEntity;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.service.WebSocketService;
import org.samarBg.views.ChatMessageViewModel;
import org.samarBg.views.OfferViewModel;
import org.samarBg.views.UserProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final OfferService offerService;
    private final UserService userService;

    @Autowired
    public IndexController(OfferService offerService,
                           UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String homePage(Model model) {
        List<OfferViewModel> offers = offerService.getNewestOffers();
        List<List<OfferViewModel>> groupedOffers = offerService.groupOffers(offers);
        List<UserProfileViewModel> users = userService.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("groupedOffers", groupedOffers);

        return "index";
    }
}
