package org.samarBg.service.serviceImpl;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.ConversationRepository;
import org.samarBg.repository.MessageRepository;
import org.samarBg.service.MessageService;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.views.OfferViewModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserService userService;
    private final ConversationRepository conversationRepository;
    private final OfferService offerService;

    public MessageServiceImpl(MessageRepository messageRepository,
                              UserService userService,
                              ConversationRepository conversationRepository,
                              OfferService offerService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.conversationRepository = conversationRepository;
        this.offerService = offerService;
    }


    @Override
    @Transactional
    public void createMessage(Long offerId, String senderUsername, String messageText) {
        MessageEntity message = new MessageEntity();
        ConversationEntity conversation = new ConversationEntity();
        OfferViewModel currentOffer = offerService.findOfferById(offerId);
        Optional<UserEntity> buyer = userService.findUserByUsername(senderUsername);
        Optional<UserEntity> seller = userService.findUserByUsername(currentOffer.getAuthorName());

        conversation.setOfferId(offerId);
        conversation.setOfferName(currentOffer.getOfferName());
        conversation.setBuyer(buyer.get());
        conversation.setSeller(seller.get());
        conversation.setMarkAsRead(1);

        conversation = conversationRepository.save(conversation);

        message.setConversation(conversation);
        message.setSender(userService.getCurrentUser());
        message.setContent(messageText);
        message.setTimestamp(new Date());

        messageRepository.save(message);
    }


    @Override
    @Transactional
    public void sendMessage(Long conversationId, String senderUsername, String messageText) {
        Optional<ConversationEntity> conversation = conversationRepository.findById(conversationId);
        if (conversation.isPresent()){
            MessageEntity message = new MessageEntity();
            conversation.get().setMarkAsRead(1);
            conversationRepository.save(conversation.get());

            message.setConversation(conversation.get());
            message.setSender(userService.getCurrentUser());
            message.setContent(messageText);
            message.setTimestamp(new Date());

            messageRepository.save(message);

        }
    }

    @Override
    public boolean checkNotificationsForUserPanel(boolean notification) {
        List<ConversationEntity> allConversations = conversationRepository.findAll();

        for (ConversationEntity conversation : allConversations) {
            if (conversation.getMarkAsRead() == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int checkNotificationsNumForUserPanel() {
        List<ConversationEntity> allConversations = conversationRepository.findAll();
        int notificationNum = 0;
        for (ConversationEntity conversation : allConversations) {
            if (conversation.getMarkAsRead() == 1){
                notificationNum += 1;
            }
        }
        return notificationNum;
    }

}