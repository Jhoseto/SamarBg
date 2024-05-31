package org.samarBg.service.serviceImpl;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.ConversationRepository;
import org.samarBg.service.ConversationService;
import org.samarBg.views.ConversationViewModel;
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
    @Transactional
    public List<ConversationViewModel> getAllNotificationForConversations(UserEntity user) {
        List<ConversationEntity> allConversation = getAllConversationsForUser(user);
        List<ConversationViewModel> conversationViewModels = new ArrayList<>();

        for (ConversationEntity conversation : allConversation) {
            ConversationViewModel con;
            if (user.getNotification().contains(conversation.getId())){
                con = new ConversationViewModel(
                        conversation.getId(),
                        conversation.getOfferId(),
                        conversation.getOfferName(),
                        conversation.getBuyer(),
                        conversation.getSeller(),
                        conversation.getMessages(),1);
            } else {
                con = new ConversationViewModel(
                        conversation.getId(),
                        conversation.getOfferId(),
                        conversation.getOfferName(),
                        conversation.getBuyer(),
                        conversation.getSeller(),
                        conversation.getMessages(),0);
            }
            conversationViewModels.add(con);
        }

        return conversationViewModels;
    }

}
