package org.samarBg.service.serviceImpl;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.ConversationRepository;
import org.samarBg.repository.MessageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.MessageService;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.views.OfferViewModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserService userService;
    private final ConversationRepository conversationRepository;
    private final OfferService offerService;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository,
                              UserService userService,
                              ConversationRepository conversationRepository,
                              OfferService offerService, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.conversationRepository = conversationRepository;
        this.offerService = offerService;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void createMessage(Long offerId, String senderUsername, String messageText) {
        MessageEntity message = new MessageEntity();
        ConversationEntity conversation = new ConversationEntity();
        OfferViewModel currentOffer = offerService.findOfferById(offerId);
        Optional<UserEntity> sender = userService.findUserByUsername(senderUsername);
        Optional<UserEntity> receiver = userService.findUserByUsername(currentOffer.getAuthorName());

        conversation.setOfferId(offerId);
        conversation.setOfferName(currentOffer.getOfferName());
        if (sender.isPresent()){
            conversation.setBuyer(sender.get());
        }
        if (receiver.isPresent()){
            conversation.setSeller(receiver.get());
        }
        conversation = conversationRepository.save(conversation);
        if (receiver.isPresent()){
            receiver.get().getNotification().add(conversation.getId());
            userRepository.save(receiver.get());
        }

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
            UserEntity notificationUser = new UserEntity();
            Optional<UserEntity> sender = userRepository.findByUsername(senderUsername);
            if (sender.isPresent()){
                if (sender.get().getId() == conversation.get().getBuyer().getId()){
                    notificationUser = conversation.get().getSeller();
                } else {
                    notificationUser = conversation.get().getBuyer();
                }
            }
            notificationUser.getNotification().add(conversationId);
            userRepository.save(notificationUser);

            MessageEntity message = new MessageEntity();
            message.setConversation(conversation.get());
            message.setSender(userService.getCurrentUser());
            message.setContent(messageText);
            message.setTimestamp(new Date());

            messageRepository.save(message);

        }
    }

    @Override
    @Transactional
    public boolean checkNotificationsForUserPanel(boolean notification) {
        UserEntity user = userService.getCurrentUser();

        if (user.getNotification().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public int checkNotificationsNumForUserPanel() {
        UserEntity user = userService.getCurrentUser();

        return user.getNotification().size();
    }

}