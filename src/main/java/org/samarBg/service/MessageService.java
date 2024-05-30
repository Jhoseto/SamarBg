package org.samarBg.service;


public interface MessageService {

    void sendMessage(Long offerId, Long senderId, String messageText) throws Exception;

    void answerMessage(Long offerId, Long senderId, String messageText) throws Exception;
}
