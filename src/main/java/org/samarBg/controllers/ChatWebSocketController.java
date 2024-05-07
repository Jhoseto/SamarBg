package org.samarBg.controllers;

import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatWebSocketController {

    private final ChatService chatService;
    private final UserRepository userRepository;


    @Autowired
    public ChatWebSocketController(ChatService chatService,
                                   UserRepository userRepository) {
        this.chatService = chatService;
        this.userRepository = userRepository;
    }


    @GetMapping("/users/{userId}")
    public UserEntity chat(@PathVariable Long userId, Model model) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        model.addAttribute("user", user);
       return user;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageEntity handleMessage(@Payload MessageEntity message) {
        return message;
    }
}
