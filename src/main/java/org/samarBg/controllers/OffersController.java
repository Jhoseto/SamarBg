package org.samarBg.controllers;


import org.samarBg.models.OfferImageEntity;
import org.samarBg.models.enums.OfferCategory;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.service.OfferService;
import org.samarBg.service.UpdateOffers;
import org.samarBg.views.OfferViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OffersController {

    private final OfferService offerService;
    private final OfferImageRepository offerImageRepository;
    private final UpdateOffers updateOffer;


    public OffersController(OfferService offerService,
                            OfferImageRepository offerImageRepository,
                            UpdateOffers updateOffer) {
        this.offerService = offerService;
        this.offerImageRepository = offerImageRepository;
        this.updateOffer = updateOffer;
    }


    @GetMapping("/offerdetail/{offerId}")
    public String showAllOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Запазване на обявата с новия брой на прегледите
            offerService.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allOffers"; // или друга страница за грешка
        }
    }

    @GetMapping("/horsesOffersDetail/{offerId}")
    public String showHorseOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Запазване на обявата с новия брой на прегледите
            offerService.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allHorses"; // или друга страница за грешка
        }
    }

    @GetMapping("/allAccessories/{offerId}")
    public String showAccessoryOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Запазване на обявата с новия брой на прегледите
            offerService.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allAccessories"; // или друга страница за грешка
        }
    }

    @PostMapping("/activate-offer")
    public String publishNewOffer(@RequestParam Long offerId, RedirectAttributes redirectAttributes) {
        try {
            offerService.activateOffer(offerId);

            redirectAttributes.addFlashAttribute("successMessage", "Обявата беше успешно активирана.");
            return "redirect:/index";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("successMessage", "Грешка при публикуване на обявата.");
            return "offerDetailPreview";
        }
    }

    @GetMapping("/offerDetailPreview/{offerId}")
    public String showOfferDetailPreview(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);

        // Намиране на снимките за съответната обява
        List<OfferImageEntity> images = offerImageRepository.findByHorseOfferId(offerId);

        if (offer != null) {
            model.addAttribute("images", images);
            model.addAttribute("offers", offer);
            return "offerDetailPreview";
        } else {
            return "redirect:/addOffers"; // страница за грешка
        }
    }

    @PostMapping("/deleteOfferOptional/{offerId}")
    public String deleteOfferOptional(@PathVariable Long offerId, HttpServletRequest request) {
        offerService.deleteOffer(offerId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @GetMapping("/editOffer/{offerId}")
    public String showEditOffer(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);
        // Намиране на снимките за съответната обява
        List<OfferImageEntity> images;
        if (offer.getOfferCategory().equals(OfferCategory.HORSES)){
            images = offerImageRepository.findByHorseOfferId(offerId);
        }else {
            images = offerImageRepository.findByAccessoryOfferId(offerId);
        }
        model.addAttribute("offer", offer);
        model.addAttribute("images", images);
        return "editOffer";
    }


    @ModelAttribute("saveEditOffer")
    public OfferViewModel saveEditOffer() {
        return new OfferViewModel();
    }


    @PostMapping("/editOffer/saveEditOffer")
    public String saveEditOffer(@ModelAttribute("saveEditOffer")
                                OfferViewModel offerViewModel,
                                @RequestParam("offerId") Long offerId,
                                @RequestParam("files") MultipartFile[] files,
                                RedirectAttributes redirectAttributes) {

        System.out.println("Updating Offer with name ->"+offerViewModel.getOfferName());
        try {

            updateOffer.updateOffers(offerId, offerViewModel, files);

            redirectAttributes.addFlashAttribute("successMessage", "Вашата обява е актуализирана успешно ");
            return "redirect:/editOffer/"+offerId;
        } catch (Exception e) {
            System.out.println("Error update Offer"+e);
            redirectAttributes.addFlashAttribute("error", "Failed to update offer: " + e.getMessage());
            return "redirect:/login";
        }
    }
}

