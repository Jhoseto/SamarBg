package org.samarBg.controllers;

import org.samarBg.service.OfferService;
import org.samarBg.service.SortedOfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SortOffersController {
    private final SortedOfferService sortedOfferService;
    private final OfferService offerService;

    public SortOffersController(SortedOfferService sortedOfferService,
                                OfferService offerService) {
        this.sortedOfferService = sortedOfferService;
        this.offerService = offerService;
    }

    @PostMapping("/sortAllOffers")
    public String sortAllOffers(@RequestParam("filter-select") String filter,
                                Model model) {



        switch (filter) {
            case "New":
                List<OfferViewModel> newSortedOffers = sortedOfferService.sortedOffersByDate(filter);
                model.addAttribute("offers", newSortedOffers);
                return "allOffers";
            case "Old":
                List<OfferViewModel> oldSortedOffers = sortedOfferService.sortedOffersByDate(filter);
                model.addAttribute("offers", oldSortedOffers);
                return "allOffers";
            case "Favorites":
                List<OfferViewModel> viewSortedOffers = sortedOfferService.sortedOffersByView(filter);
                model.addAttribute("offers", viewSortedOffers);
                return "allOffers";
            case "Expensive":
                List<OfferViewModel> expensiveSortedOffers = sortedOfferService.sortedOffersByPrice(filter);
                model.addAttribute("offers", expensiveSortedOffers);
                return "allOffers";
            case "Cheap":
                List<OfferViewModel> cheapSortedOffers = sortedOfferService.sortedOffersByPrice(filter);
                model.addAttribute("offers", cheapSortedOffers);
                return "allOffers";
            default:
                List<OfferViewModel> allOffers = offerService.getAllOffers();
                model.addAttribute("offers", allOffers);
                return "allOffers";
        }
    }
}
