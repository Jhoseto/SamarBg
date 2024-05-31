package org.samarBg.controllers;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.ConversationRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.ConversationService;
import org.samarBg.service.MessageService;
import org.samarBg.service.UserService;
import org.samarBg.views.ConversationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class MessagesController {

    private final ConversationService conversationService;
    private final MessageService messageService;
    private final UserService userService;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessagesController(ConversationService conversationService,
                              MessageService messageService,
                              UserService userService,
                              ConversationRepository conversationRepository, UserRepository userRepository) {
        this.conversationService = conversationService;
        this.messageService = messageService;
        this.userService = userService;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/messages")
    public String getAllConversations(Model model) {
        UserEntity user = userService.getCurrentUser();
        List<ConversationViewModel> conversation = conversationService.getAllNotificationForConversations(user);

        model.addAttribute("allConversations", conversation);
        model.addAttribute("currentUser", user);
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
                             @RequestParam String senderName,
                             @RequestParam String messageText,
                             RedirectAttributes redirectAttributes) {
        try {
            messageService.sendMessage(conversationId,senderName,messageText);
            redirectAttributes.addFlashAttribute("successMessage", "Съобщението ви е изпратено успешно!");
            return "redirect:/messages";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Грешка при изпращане на съобщението !");
            return "redirect:/messages";
        }
    }

    @PostMapping("/markAsRead")
    @ResponseBody
    public ResponseEntity<?> markAsRead(@RequestBody Map<String, Long> payload) {
        Long conversationId = payload.get("conversationId");
        UserEntity user = userService.getCurrentUser();

        if (conversationId != null) {
            user.getNotification().remove(conversationId);
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conversation not found");
        }
    }
}
