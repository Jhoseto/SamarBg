package org.samarBg.service.serviceImpl;

import org.samarBg.service.WebSocketService;
import org.samarBg.views.ChatMessageViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebSocketImpl implements WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final List<ChatMessageViewModel> chatMessages;

    @Autowired
    public WebSocketImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessages = new ArrayList<>();
    }

    public void sendMessage(String destination, String message) {
        messagingTemplate.convertAndSend(destination, message);
    }

    @MessageMapping("/chat")
    public void receivedMessage(@Payload ChatMessageViewModel message) {
        message.setTime(LocalDateTime.now());
        chatMessages.add(message);
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

    public List<ChatMessageViewModel> getChatMessages(String username) {
        List<ChatMessageViewModel> userMessages = new ArrayList<>();
        for (ChatMessageViewModel message : chatMessages) {
            if (message.getSenderName().equals(username)) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }
}
