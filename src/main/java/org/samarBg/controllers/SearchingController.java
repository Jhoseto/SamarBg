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


    @GetMapping("/searchingByWordsAndCategory")
    public String searchByWords(@RequestParam(name = "searchingWord", required = false) String searchingWord,
                                @RequestParam(name = "filter", required = false) String filter,
                                @RequestParam(name = "mainCategory", required = false) String mainCategory,
                                @RequestParam(name = "cityCategory", required = false) String cityCategory,
                                @RequestParam(name = "horseCategory", required = false) String horseCategory,
                                @RequestParam(name = "genderCategory", required = false) String genderCategory,
                                @RequestParam(name = "accessoriesCategory", required = false) String accessoriesCategory,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "4") int size,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfferViewModel> sortedOffersPage;

        if (filter != null && !filter.isEmpty()) {
            sortedOffersPage = sortedSearchingOffers.sortedSearchingByWordAndCategory(searchingWord,filter,
                    pageRequest, mainCategory, cityCategory, horseCategory, genderCategory, accessoriesCategory);

        } else {
            sortedOffersPage = sortedSearchingOffers.sortedSearchingByWordAndCategory(searchingWord,"New",
                    pageRequest, mainCategory, cityCategory, horseCategory, genderCategory, accessoriesCategory);
        }

        if (sortedOffersPage.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Няма намерени резултати за вашето търсене");
            return "redirect:/searching";
        }
        model.addAttribute("searchingWord", searchingWord);
        model.addAttribute("offers", sortedOffersPage);
        model.addAttribute("offersSort", filter);
        model.addAttribute("mainCategory", mainCategory);
        model.addAttribute("cityCategory", cityCategory);
        model.addAttribute("horseCategory", horseCategory);
        model.addAttribute("genderCategory", genderCategory);
        model.addAttribute("accessoriesCategory", accessoriesCategory);
        return "searching";
    }

}
