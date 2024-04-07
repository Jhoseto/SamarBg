package org.samarBg.controllers;

import org.samarBg.service.serviceImpl.OfferServiceImpl;
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

    private final OfferServiceImpl offerService;

    public SearchingController( OfferServiceImpl offerService) {
        this.offerService = offerService;

    }

    @GetMapping("/searching")
    public String showSearchingPage(){
        return "searching";
    }

    @GetMapping("/searchingByWords")
    public String searchByWords(@RequestParam("searchingWord") String searchingWord, Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "12") int size,
                                RedirectAttributes redirectAttributes) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfferViewModel> offers = (Page<OfferViewModel>) offerService.getOffersBySearchingWord(searchingWord);

        if (offers.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Няма намерени резултати за вашето търсене");
            return "redirect:/searching";
        }

        model.addAttribute("offers", offers);
        return "searching";
    }
}
