package org.samarBg.controllers;

import org.samarBg.service.OfferService;
import org.samarBg.views.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final OfferService offerService;

    @Autowired
    public IndexController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping("/")
    public String homePage() {
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String homePage(Model model) {
        List<OfferViewModel> offers = offerService.getNewestOffers(); // Вземете списък с всички обяви
        List<List<OfferViewModel>> groupedOffers = groupOffers(offers); // Групирайте обявите по 4 за всяка група

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
