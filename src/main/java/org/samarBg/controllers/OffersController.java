package org.samarBg.controllers;


import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.service.OfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private final OfferService offerService;
    private final OfferImageRepository offerImageRepository;



    public OffersController(OfferService offerService,
                            OfferImageRepository offerImageRepository) {
        this.offerService = offerService;
        this.offerImageRepository = offerImageRepository;
    }

//    @GetMapping("/allOffers")
//    public String getAllOffers(@RequestParam(defaultValue = "0") int page,
//                               @RequestParam(defaultValue = "12") int size,
//                               Model model) throws Exception {
//        // Изчисляване на номера на страницата (page) и размера на страницата (size)
//        PageRequest pageRequest = PageRequest.of(page, size);
//
//        // Извличане на обяви със странициране
//        Page<OfferViewModel> offersPage = offerService.getAllOffers(PageRequest.of(page, size));
//
//        // Подаване на страницираните обяви към Thymeleaf шаблона
//        model.addAttribute("offers", offersPage);
//        return "allOffers";
//    }

//    @GetMapping("/allHorses")
//    public String getAllHorses(Model model,
//                               @RequestParam(defaultValue = "0") int page,
//                               @RequestParam(defaultValue = "12") int size) throws Exception {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<OfferViewModel> offers = offerService.getAllHorsesOffers(pageRequest);
//
//        model.addAttribute("offers", offers);
//        return "allHorses";
//    }
//
//    @GetMapping("/allAccessories")
//    public String getAllAccessory(Model model,
//                                  @RequestParam(defaultValue = "0") int page,
//                                  @RequestParam(defaultValue = "12") int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<OfferViewModel> offers = offerService.getAllAccessoryOffers(pageRequest);
//
//        model.addAttribute("offers", offers);
//        return "allAccessories";
//    }


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

    @GetMapping("/allHorses/{offerId}")
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

    @PostMapping("/deleteOffer/{offerId}")
    public String deletePreviewOffer(@PathVariable Long offerId) {
        offerService.deleteOffer(offerId);
        return "redirect:/addOffers";
    }

}

