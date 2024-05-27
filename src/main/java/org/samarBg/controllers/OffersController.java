package org.samarBg.controllers;


import org.samarBg.models.OfferImageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.models.enums.OfferCategory;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.service.OfferService;
import org.samarBg.service.UpdateOffers;
import org.samarBg.service.UserService;
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
    private final UserService userService;


    public OffersController(OfferService offerService,
                            OfferImageRepository offerImageRepository,
                            UpdateOffers updateOffer,
                            UserService userService) {
        this.offerService = offerService;
        this.offerImageRepository = offerImageRepository;
        this.updateOffer = updateOffer;
        this.userService = userService;
    }


    @GetMapping("/offerdetail/{offerId}")
    public String showAllOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);
        UserEntity user = userService.getCurrentUser();

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Update offer view counter
            offerService.saveInExistOffers(offer);

            model.addAttribute("user", user);
            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allOffers";
        }
    }

    @GetMapping("/horsesOffersDetail/{offerId}")
    public String showHorseOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Update offer view counter
            offerService.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allHorses";
        }
    }

    @GetMapping("/allAccessories/{offerId}")
    public String showAccessoryOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Update offer view counter
            offerService.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allAccessories";
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

        // Find all pictures for current offer
        List<OfferImageEntity> images = offerImageRepository.findByHorseOfferId(offerId);

        if (offer != null) {
            model.addAttribute("images", images);
            model.addAttribute("offers", offer);
            return "offerDetailPreview";
        } else {
            return "redirect:/addOffers";
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
        // Find all pictures for current offer
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

