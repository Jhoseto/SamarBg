package org.samarBg.controllers;

import org.samarBg.service.implementation.OfferServiceImpl;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SearchingController {

    private final OfferServiceImpl offerServiceImpl;

    public SearchingController(OfferServiceImpl offerServiceImpl) {
        this.offerServiceImpl = offerServiceImpl;
    }

    @GetMapping("/searching")
    public String showSearchingPage(){
        return "searching";
    }

    @GetMapping("/searchingByWords")
    public String searchByWords(@RequestParam("searchingWord") String searchingWord, Model model,
                                RedirectAttributes redirectAttributes) {
        List<OfferViewModel> offers = offerServiceImpl.getOffersBySearchingWord(searchingWord);

        if (offers.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Няма намерени резултати за вашето търсене");
        }
        model.addAttribute("offers", offers);
        return "searching";
    }
}
