package org.samarBg.controllers;

import org.samarBg.service.SortedSearchingOffers;
import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SearchingController {

    private final SortedSearchingOffers sortedSearchingOffers;

    public SearchingController(SortedSearchingOffers sortedSearchingOffers) {
        this.sortedSearchingOffers = sortedSearchingOffers;
    }


    @GetMapping("/searching")
    public String showSearchingPage(){
        return "searching";
    }

    @GetMapping("/searchingByWords")
    public String searchByWords(@RequestParam(name = "searchingWord") String searchingWord,
                                @RequestParam(name = "filter", required = false) String filter,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "4") int size,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfferViewModel> sortedOffersPage;

        if (filter != null && !filter.isEmpty()) {
            sortedOffersPage = sortedSearchingOffers.sortedSearchingByWord(searchingWord,filter, pageRequest);

        } else {
            sortedOffersPage = sortedSearchingOffers.sortedSearchingByWord(searchingWord,"New",pageRequest);
        }

        if (sortedOffersPage.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Няма намерени резултати за вашето търсене");
            return "redirect:/searching";
        }

        model.addAttribute("offers", sortedOffersPage);
        model.addAttribute("offersSort", filter);
        model.addAttribute("searchingWord", searchingWord);
        return "searching";
    }
}
