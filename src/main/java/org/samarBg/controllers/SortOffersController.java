package org.samarBg.controllers;

import org.samarBg.service.OfferService;
import org.samarBg.service.SortedOfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SortOffersController {
    private final SortedOfferService sortedOfferService;
    private final OfferService offerService;

    public SortOffersController(SortedOfferService sortedOfferService,
                                OfferService offerService) {
        this.sortedOfferService = sortedOfferService;
        this.offerService = offerService;
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
                    sortedOffersPage = sortedOfferService.sortedAccessoriesOffersByDate("New",pageRequest);
                    break;
            }
        } else {
            sortedOffersPage = sortedOfferService.sortedOffersByDate("New",pageRequest);
        }

        model.addAttribute("offers", sortedOffersPage);
        return "allOffers"; // Връщане на името на шаблона за изглед
    }






//    @PostMapping("/allHorses")
//    public String sortAllHorses(@RequestParam("filter-select") String filter, Model model,
//                                @RequestParam(defaultValue = "0") int page,
//                                @RequestParam(defaultValue = "12") int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        switch (filter) {
//            case "New":
//                Page<OfferViewModel> newSortedOffers = sortedOfferService.sortedHorseOffersByDate(filter, pageRequest);
//                model.addAttribute("offers", newSortedOffers);
//                return "allOffers";
//            case "Old":
//                Page<OfferViewModel> oldSortedOffers = sortedOfferService.sortedHorseOffersByDate(filter, pageRequest);
//                model.addAttribute("offers", oldSortedOffers);
//                return "allOffers";
//            case "Favorites":
//                Page<OfferViewModel> viewSortedOffers = sortedOfferService.sortedHorseOffersByView(filter, pageRequest);
//                model.addAttribute("offers", viewSortedOffers);
//                return "allOffers";
//            case "Expensive":
//                Page<OfferViewModel> expensiveSortedOffers = sortedOfferService.sortedHorseOffersByPrice(filter, pageRequest);
//                model.addAttribute("offers", expensiveSortedOffers);
//                return "allOffers";
//            case "Cheap":
//                Page<OfferViewModel> cheapSortedOffers = sortedOfferService.sortedHorseOffersByPrice(filter, pageRequest);
//                model.addAttribute("offers", cheapSortedOffers);
//                return "allOffers";
//            default:
//                Page<OfferViewModel> allOffers = offerService.getAllOffers(pageRequest);
//                model.addAttribute("offers", allOffers);
//                return "allOffers";
//        }
//    }
//
//    @PostMapping("/allAccessories")
//    public String sortAllAccessories(@RequestParam("filter-select") String filter, Model model,
//                                     @RequestParam(defaultValue = "0") int page,
//                                     @RequestParam(defaultValue = "12") int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        switch (filter) {
//            case "New":
//                Page<OfferViewModel> newSortedOffers = sortedOfferService.sortedAccessoriesOffersByDate(filter, pageRequest);
//                model.addAttribute("offers", newSortedOffers);
//                return "allOffers";
//            case "Old":
//                Page<OfferViewModel> oldSortedOffers = sortedOfferService.sortedAccessoriesOffersByDate(filter, pageRequest);
//                model.addAttribute("offers", oldSortedOffers);
//                return "allOffers";
//            case "Favorites":
//                Page<OfferViewModel> viewSortedOffers = sortedOfferService.sortedAccessoriesOffersByView(filter, pageRequest);
//                model.addAttribute("offers", viewSortedOffers);
//                return "allOffers";
//            case "Expensive":
//                Page<OfferViewModel> expensiveSortedOffers = sortedOfferService.sortedAccessoriesOffersByPrice(filter, pageRequest);
//                model.addAttribute("offers", expensiveSortedOffers);
//                return "allOffers";
//            case "Cheap":
//                Page<OfferViewModel> cheapSortedOffers = sortedOfferService.sortedAccessoriesOffersByPrice(filter, pageRequest);
//                model.addAttribute("offers", cheapSortedOffers);
//                return "allOffers";
//            default:
//                Page<OfferViewModel> allOffers = offerService.getAllOffers(pageRequest);
//                model.addAttribute("offers", allOffers);
//                return "allOffers";
//        }
//    }
}
