package org.samarBg.service;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ConversationService {

    ConversationEntity createConversation(ConversationEntity conversation);

    List<ConversationEntity> getAllConversationsForUser(UserEntity user);

    Optional<ConversationEntity> getById(Long conversationId);

}
