package org.samarBg.controllers;

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

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final OfferService offerService;
    private final WebSocketService webSocketService;
    private final UserService userService;

    @Autowired
    public IndexController(OfferService offerService,
                           WebSocketService webSocketService,
                           UserService userService) {
        this.offerService = offerService;
        this.webSocketService = webSocketService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String homePage(Model model) {
        List<OfferViewModel> offers = offerService.getNewestOffers(); // Вземете списък с всички обяви
        List<List<OfferViewModel>> groupedOffers = groupOffers(offers); // Групирайте обявите по 4 за всяка група
        List<UserProfileViewModel> users = userService.getAllUsers();

        UserProfileViewModel selectedUser = users.get(0);
        List<ChatMessageViewModel> chatMessages = webSocketService.getChatMessages("selectedUserName");

        model.addAttribute("users", users);
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("groupedOffers", groupedOffers);
        return "index";
    }

    private List<List<OfferViewModel>> groupOffers(List<OfferViewModel> offers) {
        List<List<OfferViewModel>> grouped = new ArrayList<>();
        int groupSize = 4;
        for (int i = 0; i < offers.size(); i += groupSize) {
            grouped.add(offers.subList(i, Math.min(i + groupSize, offers.size())));
        }
        return grouped;
    }
}
