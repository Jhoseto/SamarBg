package org.samarBg.controllers;

import org.samarBg.models.UserEntity;
import org.samarBg.service.ConversationService;
import org.samarBg.service.MessageService;
import org.samarBg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessagesController {

    private final ConversationService conversationService;
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessagesController(ConversationService conversationService,
                              MessageService messageService,
                              UserService userService) {
        this.conversationService = conversationService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/messages")
    public String getAllConversations(Model model) {
        UserEntity user = userService.getCurrentUser();
        model.addAttribute("allConversations", conversationService.getAllConversationsForUser(user));
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "messages";
    }


    @PostMapping("/createMessage")
    public String createMessage(
            @RequestParam Long offerId,
            @RequestParam String messageText,
            RedirectAttributes redirectAttributes) {
        try {
            String senderUsername = userService.getCurrentUser().getUsername();
            messageService.createMessage(offerId, senderUsername, messageText);
            redirectAttributes.addFlashAttribute("successMessage", "Съобщението ви е изпратено успешно!");
            return "redirect:/offerdetail/" + offerId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Не може да изпратите съобщение на себе си !");
            return "redirect:/offerdetail/" + offerId;
        }
    }


    @PostMapping("/sendAnswer")
    public String sendAnswer(@RequestParam Long conversationId,
                             @RequestParam String senderUsername,
                             @RequestParam String messageText) {

        messageService.sendMessage(conversationId,senderUsername,messageText);

        return "redirect:/messages";
    }
}
