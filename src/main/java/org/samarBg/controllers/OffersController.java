package org.samarBg.controllers;


import org.samarBg.model.entities.UserEntity;
import org.samarBg.service.OfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OffersController {

    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/allads")
    public String getAllOffers(Model model) {
        List<OfferViewModel> offers = offerService.getAllOffers();


        model.addAttribute("offers", offers);
        return "allads";
    }



    @GetMapping("/offerdetail/{offerId}")
    public String showOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findById(offerId);
        if (offer != null) {
            model.addAttribute("offer", offer);
           offer.setOfferViewCount(offer.getUserOffersCount()+1);
            return "offerdetail";
        } else {
            return "redirect:/allads"; // или друга страница за грешка
        }
    }



}


