package org.samarBg.controllers;

import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.MessageRepository;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.service.MessageService;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.views.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessagesController {

    private final MessageService messageService;
    private final OfferService offerService;
    private final UserService userService;
    private final MessageRepository messageRepository;
    private final OfferImageRepository offerImageRepository;

    @Autowired
    public MessagesController(MessageService messageService, OfferService offerService, UserService userService, MessageRepository messageRepository, OfferImageRepository offerImageRepository) {
        this.messageService = messageService;
        this.offerService = offerService;
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.offerImageRepository = offerImageRepository;
    }


    @GetMapping("/messages")
    public String getMessages(Model model) {

        UserEntity currentUser = userService.getCurrentUser();
        List<MessageEntity> allUserMessages = messageRepository.findAllBySenderOrReceiver(currentUser, currentUser);

        List<OfferViewModel> allOffersForUser = new ArrayList<>();
        for (MessageEntity allUserMessage : allUserMessages) {
            OfferViewModel currentOffer = offerService.findOfferById(allUserMessage.getOfferId());
            allOffersForUser.add(currentOffer);
        }

        model.addAttribute("allOffersForUser", allOffersForUser);
        model.addAttribute("allUserMessage", allUserMessages);
        model.addAttribute("currentUser", currentUser);
        return "messages";
    }


    @PostMapping("/sendAnswer")
    public String sendMessageFromPanel(@RequestParam("offerId") Long offerId,
                                       @RequestParam("senderId") Long senderId,
                                       @RequestParam("messageText") String messageText,
                                       RedirectAttributes redirectAttributes) {
        try {
            messageService.sendMessage(offerId, senderId, messageText);
            redirectAttributes.addFlashAttribute("successMessage", "Отговорът ви е изпратено успешно!");
            return "redirect:/messages";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Грешка при изпращане на съобщението !");
            return "redirect:/messages";
        }
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
