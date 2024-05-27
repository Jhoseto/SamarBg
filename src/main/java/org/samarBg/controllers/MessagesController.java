package org.samarBg.controllers;

import org.samarBg.service.MessageService;
import org.samarBg.views.MessagesViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MessagesController {

    private final MessageService messageService;


    public MessagesController(MessageService messageService) {
        this.messageService = messageService;

    }


    @PostMapping("/sendMessage")
    public String sendMessage(
            @RequestParam("offerId") Long offerId,
            @RequestParam("senderId") Long senderId,
            @RequestParam("messageText") String messageText,
            RedirectAttributes redirectAttributes) {
        try {
            messageService.sendMessage(offerId, senderId, messageText);
            redirectAttributes.addFlashAttribute("successMessage", "Съобщението ви е изпратено успешно!");
            return "redirect:/offerdetail/" + offerId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Не може да изпратите съобщение на себе си !");
            return "redirect:/offerdetail/" + offerId;
        }
    }
}
