package org.samarBg.controllers;


import org.samarBg.service.OfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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




    @PostMapping("/offerdetail")
    public String handleOfferDetailPost(@RequestParam("offerId") Long offerId, Model model) {
        // Логика за обработка на POST заявката за детайл на офертата
        return "redirect:/offerdetail/";
    }
}


