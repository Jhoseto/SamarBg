package org.samarBg.service.serviceImpl;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.ConversationRepository;
import org.samarBg.service.ConversationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public ConversationEntity createConversation(ConversationEntity conversation) {
        return conversationRepository.save(conversation);

    }

    @Override
    public List<ConversationEntity> getAllConversationsForUser(UserEntity user) {
        List<ConversationEntity> allConversationForUser = new ArrayList<>();
        List<ConversationEntity> allConversations = conversationRepository.findAll();

        for (ConversationEntity conversation : allConversations) {
            if (conversation.getBuyer().equals(user) || conversation.getSeller().equals(user)){
                allConversationForUser.add(conversation);
            }
        }
        return allConversationForUser;
    }

    @Override
    public Optional<ConversationEntity> getById(Long conversationId) {
        return conversationRepository.findById(conversationId);
    }
}
