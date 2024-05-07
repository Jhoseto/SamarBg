package org.samarBg.service.serviceImpl;

import org.samarBg.models.BaseEntity;
import org.samarBg.models.MessageEntity;
import org.samarBg.service.ChatService;
import org.samarBg.views.ChatMessageViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.MessageRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final MessageRepository messageRepository;

    @Autowired
    public ChatServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageEntity> getChatHistory(UserEntity sender, UserEntity receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }

    @Override
    public void saveMessage(UserEntity sender, UserEntity receiver, String content) {
        MessageEntity message = new MessageEntity();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(new Date());

        messageRepository.save(message);

    }
}
