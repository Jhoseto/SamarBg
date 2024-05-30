package org.samarBg.service;


import org.samarBg.models.MessageEntity;

import java.util.List;

public interface MessageService {

    List<MessageEntity> getMessagesByConversation(Long conversationId);

    void createMessage(Long offerId, String senderUsername, String messageText);

    void sendMessage(Long conversationId, String senderUsername, String messageText);
}
