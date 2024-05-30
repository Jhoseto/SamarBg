package org.samarBg.service;


public interface MessageService {

    void createMessage(Long offerId, String senderUsername, String messageText);

    void sendMessage(Long conversationId, String senderUsername, String messageText);

    boolean checkNotificationsForUserPanel(boolean notification);

    int checkNotificationsNumForUserPanel();
}
