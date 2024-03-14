package org.samarBg.controllers;


import org.samarBg.service.OfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


        // Извеждане на предаваните данни в конзолата
        for (OfferViewModel offer : offers) {
            System.out.println(offer.toString());
        }

        model.addAttribute("offers", offers);
        return "allads";
    }
}


