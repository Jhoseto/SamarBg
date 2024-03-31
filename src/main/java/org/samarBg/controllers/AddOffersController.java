package org.samarBg.controllers;

import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.securityAndComponent.CurrentUser;
import org.samarBg.service.AddOffersService;
import org.samarBg.service.CurrentUserService;
import org.samarBg.service.OfferService;
import org.samarBg.view.AddOfferHorseViewModel;
import org.samarBg.view.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class AddOffersController {


    private final CurrentUserService currentUserService;
    private final AddOffersService addOffersService;
    private final OfferService offerService;
    private final OfferImageRepository offerImageRepository;
    @Autowired
    public AddOffersController(CurrentUserService currentUserService, AddOffersService addOffersService,
            OfferService offerService,
            OfferImageRepository offerImageRepository) {
        this.currentUserService = currentUserService;
        this.addOffersService = addOffersService;
        this.offerService = offerService;
        this.offerImageRepository = offerImageRepository;
    }


    @GetMapping("/addOffers")
    public String showAddOffers() {
        return "addOffers";
    }
    @GetMapping("/offerDetailPreview/{offerId}")
    public String showOfferDetailPreview(@PathVariable Long offerId, Model model) {
        OfferViewModel offer = offerService.findById(offerId);

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

    @PostMapping("/activate-offer")
    public String publishNewOffer(@RequestParam Long offerId, RedirectAttributes redirectAttributes) {

        try {
            offerService.activateOffer(offerId);
            redirectAttributes.addFlashAttribute("successMessage","Обявата беше успешно активирана.");
            return "redirect:/index";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("successMessage","Грешка при публикуване на обявата.");
            return "offerDetailPreview";
        }
    }


    @ModelAttribute("addHorseOffer")
    public AddOfferHorseViewModel addHorseOffer() {
        return new AddOfferHorseViewModel();
    }

    @PostMapping("/addOffers/addHorseOffer")
    public String addHorseOffer(@ModelAttribute("addHorseOffer") @Valid AddOfferHorseViewModel addOfferHorseViewModel,
                                @RequestParam("files") MultipartFile[] files,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        StringBuilder errorMessages = new StringBuilder();

        if (result.hasErrors()) {
            // Проверка за грешки във всички полета и добавяне на съобщенията към модела
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.append(error.getDefaultMessage()).append(" / ");
            }

            // Добавяне на общото съобщение за грешките към модела
            redirectAttributes.addFlashAttribute("error", errorMessages.toString());


            return "redirect:/addOffers";

        } else {
            try {
                // Качване на снимките и свързване с обявата
                List<String> imageUrls = addOffersService.uploadImages(List.of(files));

                if (imageUrls.isEmpty()){
                    redirectAttributes.addFlashAttribute("error","Моля добавете поне една снимка към вашата обява !");
                    return "redirect:/addOffers";
                }
                // Успешно качване на снимките, записване на обявата
                redirectAttributes.addFlashAttribute("successMessage",
                        "Данните за обявата са попълнени успешно!\n" +
                                "Моля прегледайте вашата обява след, което потвърдете, че\n" +
                                "желаете да бъде публикувана");

                Long offerId = addOffersService.addHorseOffer(addOfferHorseViewModel, imageUrls);
                currentUserService.getCurrentUser().setCurrentUserOfferId(offerId);
                return "redirect:/offerDetailPreview/"+offerId;

            } catch (IOException e) {
                e.printStackTrace();
                // Грешка при качването на снимките
                redirectAttributes.addFlashAttribute("error", "Грешка при качването на снимките: " + e.getMessage());
                return "redirect:/addOffers";
            }
        }
    }
}
