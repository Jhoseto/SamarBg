package org.samarBg.controllers;


import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
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
    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;

    public OffersController(OfferService offerService,
                            HorseOfferRepository horseOfferRepository,
                            AccessoriesOfferRepository accessoriesOfferRepository) {
        this.offerService = offerService;
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
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
        HorseOfferEntity horse = new HorseOfferEntity();

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Запазване на обявата с новия брой на прегледите
            offerService.saveInOffers(offer);

            model.addAttribute("offer", offer);

            return "offerdetail";
        } else {
            return "redirect:/allads"; // или друга страница за грешка
        }
    }



}


