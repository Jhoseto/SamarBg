package org.samarBg.service;


import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;

import java.util.List;

public interface MessageService {


    void sendMessage(Long offerId, Long senderId, String messageText) throws Exception;

    List<MessageEntity> findMessagesByOfferId(Long offerId);

}
