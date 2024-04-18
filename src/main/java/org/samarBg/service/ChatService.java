package org.samarBg.service;

import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;

import java.util.List;

public interface ChatService {

    List<MessageEntity> getChatHistory(UserEntity sender, UserEntity receiver);

    void saveMessage(UserEntity sender, UserEntity receiver, String content);
}
