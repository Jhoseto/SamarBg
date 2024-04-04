package org.samarBg.controllers;

import org.samarBg.service.AddOffersService;
import org.samarBg.service.implementation.AddOffersImpl;
import org.samarBg.service.CurrentUserService;
import org.samarBg.view.AddAccessoriesViewModel;
import org.samarBg.view.AddOfferHorseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    private final AddOffersService addOffersService;

    @Autowired
    public AddOffersController(AddOffersService addOffersService) {
        this.addOffersService = addOffersService;

    }

    @GetMapping("/addOffers")
    public String showAddOffers() {
        return "addOffers";
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
                return "redirect:/offerDetailPreview/"+offerId;

            } catch (IOException e) {
                e.printStackTrace();
                // Грешка при качването на снимките
                redirectAttributes.addFlashAttribute("error", "Грешка при качването на снимките: " + e.getMessage());
                return "redirect:/addOffers";
            }
        }
    }

    @ModelAttribute("addAccessoriesOffer")
    public AddAccessoriesViewModel addAccessoriesOffer() {
        return new AddAccessoriesViewModel();
    }
    @PostMapping("/addOffers/addAccessoriesOffer")
    public String addAccessoriesOffer(@ModelAttribute("addAccessoriesOffer") @Valid AddAccessoriesViewModel addAccessoriesViewModel,
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

                Long offerId = addOffersService.addAccessoriesOffer(addAccessoriesViewModel, imageUrls);
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
