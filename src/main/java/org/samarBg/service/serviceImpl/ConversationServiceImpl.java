package org.samarBg.service.serviceImpl;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.ConversationRepository;
import org.samarBg.service.ConversationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Transactional
    public List<ConversationEntity> getAllConversationsForUser(UserEntity user) {
        List<ConversationEntity> allConversationForUser = new ArrayList<>();
        List<ConversationEntity> allConversations = conversationRepository.findAll();

        for (ConversationEntity conversation : allConversations) {
            if (conversation.getBuyer().getId() == user.getId()
                    || conversation.getSeller().getId() == user.getId()) {
                allConversationForUser.add(conversation);
            }
        }

        // Sorting Offers Messages by timeStamp
        allConversationForUser.sort(Comparator.comparing((ConversationEntity c) ->
                c.getMessages().isEmpty() ? null : c.getMessages().get(c.getMessages().size() - 1).getTimestamp()).reversed());

        // Sorting conversation for all messages by timeStamp
        for (ConversationEntity conversation : allConversationForUser) {
            conversation.getMessages().sort(Comparator.comparing(MessageEntity::getTimestamp));
        }
        return allConversationForUser;
    }

    @Override
    public Optional<ConversationEntity> getById(Long conversationId) {
        return conversationRepository.findById(conversationId);
    }
}
