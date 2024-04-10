package org.samarBg.controllers;

import org.samarBg.service.SortedOfferService;
import org.samarBg.views.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SortOffersController {
    private final SortedOfferService sortedOfferService;

    public SortOffersController(SortedOfferService sortedOfferService) {
        this.sortedOfferService = sortedOfferService;

    }


    @GetMapping("/allOffers")
    public String sortAllOffers(@RequestParam(name = "filter", required = false) String filter,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "8") int size,
                                Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfferViewModel> sortedOffersPage;

        if (filter != null && !filter.isEmpty()) {
            switch (filter) {
                case "New":
                case "Old":
                    sortedOffersPage = sortedOfferService.sortedOffersByDate(filter, pageRequest);
                    break;
                case "Favorites":
                    sortedOffersPage = sortedOfferService.sortedOffersByView(filter, pageRequest);
                    break;
                case "Expensive":
                case "Cheap":
                    sortedOffersPage = sortedOfferService.sortedOffersByPrice(filter, pageRequest);
                    break;
                default:
                    sortedOffersPage = sortedOfferService.sortedOffersByDate("New",pageRequest);
                    break;
            }
        } else {
            sortedOffersPage = sortedOfferService.sortedOffersByDate("New",pageRequest);
        }
        model.addAttribute("offers", sortedOffersPage);
        model.addAttribute("filter", filter);
        return "allOffers";
    }



    @GetMapping("/allHorses")
    public String sortAllHorses(@RequestParam(name = "filter", required = false) String filter,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "4") int size,
                                Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfferViewModel> sortedOffersPage;
        if (filter != null && !filter.isEmpty()) {
            switch (filter) {
                case "New":
                case "Old":
                    sortedOffersPage = sortedOfferService.sortedHorseOffersByDate(filter, pageRequest);
                    break;
                case "Favorites":
                    sortedOffersPage = sortedOfferService.sortedHorseOffersByView(filter, pageRequest);
                    break;
                case "Expensive":
                case "Cheap":
                    sortedOffersPage = sortedOfferService.sortedHorseOffersByPrice(filter, pageRequest);
                    break;
                default:
                    sortedOffersPage = sortedOfferService.sortedHorseOffersByDate("New",pageRequest);
                    break;
            }
        } else {
            sortedOffersPage = sortedOfferService.sortedHorseOffersByDate("New",pageRequest);
        }
        model.addAttribute("offers", sortedOffersPage);
        model.addAttribute("filter", filter);
        return "allHorses";
    }

    @GetMapping("/allAccessories")
    public String sortAllAccessories(@RequestParam(name = "filter", required = false) String filter,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "4") int size,
                                     Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfferViewModel> sortedOffersPage;
        if (filter != null && !filter.isEmpty()) {
            switch (filter) {
                case "New":
                case "Old":
                    sortedOffersPage = sortedOfferService.sortedAccessoriesOffersByDate(filter, pageRequest);
                    break;
                case "Favorites":
                    sortedOffersPage = sortedOfferService.sortedAccessoriesOffersByView(filter, pageRequest);
                    break;
                case "Expensive":
                case "Cheap":
                    sortedOffersPage = sortedOfferService.sortedAccessoriesOffersByPrice(filter, pageRequest);
                    break;
                default:
                    sortedOffersPage = sortedOfferService.sortedAccessoriesOffersByDate("New",pageRequest);
                    break;
            }
        } else {
            sortedOffersPage = sortedOfferService.sortedAccessoriesOffersByDate("New",pageRequest);
        }
        model.addAttribute("offers", sortedOffersPage);
        model.addAttribute("filter", filter);
        return "allAccessories";
    }
}
