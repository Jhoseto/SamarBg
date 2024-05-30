package org.samarBg.service.serviceImpl;

import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.MessageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.MessageService;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.views.OfferViewModel;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final UserService userService;
    private final OfferService offerService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(UserService userService,
                              OfferService offerService,
                              UserRepository userRepository,
                              MessageRepository messageRepository) {
        this.userService = userService;
        this.offerService = offerService;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }


    @Override
    public void sendMessage(Long offerId, Long senderId, String messageText) throws Exception {
        UserEntity sender = userService.getCurrentUser();
        OfferViewModel currentOffer = offerService.findOfferById(offerId);
        Optional<UserEntity> receiver = userRepository.findByUsername(currentOffer.getAuthorName());


        if (sender.getId() != receiver.get().getId() ){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setOfferId(offerId);
            messageEntity.setSender(sender);
            if (receiver.isPresent()){
                messageEntity.setReceiver(receiver.get());
            }
            messageEntity.setContent(messageText);
            messageEntity.setMarkAsRead(0);
            messageEntity.setTimestamp(new Date());

            messageRepository.save(messageEntity);
        } else {
            throw new Exception();
        }
    }

    @Override
    public void answerMessage(Long offerId, Long senderId, String messageText) throws Exception {
        UserEntity sender = userService.getCurrentUser();
        OfferViewModel currentOffer = offerService.findOfferById(offerId);
        Optional<UserEntity> receiver = userRepository.findByUsername(currentOffer.getAuthorName());


        if (sender.getId() != receiver.get().getId() ){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setOfferId(offerId);
            messageEntity.setSender(sender);
            if (receiver.isPresent()){
                messageEntity.setReceiver(receiver.get());
            }
            messageEntity.setContent(messageText);
            messageEntity.setMarkAsRead(0);
            messageEntity.setTimestamp(new Date());

            messageRepository.save(messageEntity);
        } else {
            throw new Exception();
        }
    }
}
