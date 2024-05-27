package org.samarBg.service;

import org.samarBg.views.MessagesViewModel;

public interface MessageService {

    void sendMessage(Long offerId, Long senderId, String messageText) throws Exception;
}
