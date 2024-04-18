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

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageEntity handleMessage(@Payload MessageEntity message) {
        // Тук може да обработите полученото съобщение и да върнете отговор
        return message;
    }

}
