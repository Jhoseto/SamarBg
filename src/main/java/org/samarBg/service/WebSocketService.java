package org.samarBg.service;

import org.samarBg.views.ChatMessageViewModel;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface WebSocketService {
    void sendMessage(String destination, String message);

    void receivedMessage(@Payload ChatMessageViewModel message);

    List<ChatMessageViewModel> getChatMessages(String username);
}
