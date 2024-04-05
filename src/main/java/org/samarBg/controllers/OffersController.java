package org.samarBg.controllers;


import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.service.implementation.OfferServiceImpl;
import org.samarBg.service.implementation.UserServiceImpl;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OffersController {

    private final OfferServiceImpl offerServiceImpl;
    private final OfferImageRepository offerImageRepository;
    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final UserServiceImpl userServiceImpl;


    public OffersController(OfferServiceImpl offerServiceImpl,
                            OfferImageRepository offerImageRepository,
                            HorseOfferRepository horseOfferRepository,
                            AccessoriesOfferRepository accessoriesOfferRepository,
                            UserServiceImpl userServiceImpl) {
        this.offerServiceImpl = offerServiceImpl;
        this.offerImageRepository = offerImageRepository;
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/allOffers")
    public String getAllOffers(Model model) throws Exception {
        List<OfferViewModel> offers = offerServiceImpl.getAllOffers();

        model.addAttribute("offers", offers);
        return "allOffers";
    }

    @GetMapping("/allHorses")
    public String getAllHorses(Model model) throws Exception {
        List<OfferViewModel> offers = offerServiceImpl.getAllHorsesOffers();

        model.addAttribute("offers", offers);
        return "allHorses";
    }

    @GetMapping("/allAccessories")
    public String getAllAccessory(Model model) {
        List<OfferViewModel> offers = offerServiceImpl.getAllAccessoryOffers();

        model.addAttribute("offers", offers);
        return "allAccessories";
    }


    @GetMapping("/offerdetail/{offerId}")
    public String showAllOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerServiceImpl.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Запазване на обявата с новия брой на прегледите
            offerServiceImpl.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allOffers"; // или друга страница за грешка
        }
    }

    @GetMapping("/allHorses/{offerId}")
    public String showHorseOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerServiceImpl.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Запазване на обявата с новия брой на прегледите
            offerServiceImpl.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allHorses"; // или друга страница за грешка
        }
    }

    @GetMapping("/allAccessories/{offerId}")
    public String showAccessoryOfferDetailPage(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerServiceImpl.findOfferById(offerId);

        if (offer != null) {
            offer.setOfferViewCount(offer.getOfferViewCount() + 1);
            // Запазване на обявата с новия брой на прегледите
            offerServiceImpl.saveInExistOffers(offer);

            model.addAttribute("offer", offer);
            return "offerdetail";
        } else {
            return "redirect:/allAccessories"; // или друга страница за грешка
        }
    }

    @PostMapping("/activate-offer")
    public String publishNewOffer(@RequestParam Long offerId, RedirectAttributes redirectAttributes) {
        try {
            offerServiceImpl.activateOffer(offerId);

            redirectAttributes.addFlashAttribute("successMessage", "Обявата беше успешно активирана.");
            return "redirect:/index";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("successMessage", "Грешка при публикуване на обявата.");
            return "offerDetailPreview";
        }
    }

    @GetMapping("/offerDetailPreview/{offerId}")
    public String showOfferDetailPreview(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerServiceImpl.findOfferById(offerId);

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

    @PostMapping("/deleteOffer/{offerId}")
    public String deletePreviewOffer(@PathVariable Long offerId) {
        offerServiceImpl.deleteOffer(offerId);
        return "redirect:/addOffers";
    }

}

