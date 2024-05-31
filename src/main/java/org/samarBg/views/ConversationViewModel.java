package org.samarBg.views;

import org.samarBg.models.ConversationEntity;
import org.samarBg.models.MessageEntity;
import org.samarBg.models.UserEntity;

import java.util.List;

public class ConversationViewModel extends ConversationEntity {

    private Long id;

    private Long offerId;

    private String offerName;

    private UserEntity buyer;

    private UserEntity seller;

    private List<MessageEntity> messages;

    private int markAsRead;

    public ConversationViewModel(Long id,
                                 Long offerId,
                                 String offerName,
                                 UserEntity buyer,
                                 UserEntity seller,
                                 List<MessageEntity> messages,
                                 int markAsRead) {
        this.id = id;
        this.offerId = offerId;
        this.offerName = offerName;
        this.buyer = buyer;
        this.seller = seller;
        this.messages = messages;
        this.markAsRead = markAsRead;
    }

    public Long getId() {
        return id;
    }

    public ConversationViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOfferId() {
        return offerId;
    }

    public ConversationViewModel setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getOfferName() {
        return offerName;
    }

    public ConversationViewModel setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public ConversationViewModel setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public ConversationViewModel setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public ConversationViewModel setMessages(List<MessageEntity> messages) {
        this.messages = messages;
        return this;
    }

    public int getMarkAsRead() {
        return markAsRead;
    }

    public ConversationViewModel setMarkAsRead(int markAsRead) {
        this.markAsRead = markAsRead;
        return this;
    }

    @Override
    public String toString() {
        return "ConversationViewModel{" +
                "id=" + id +
                ", offerId=" + offerId +
                ", offerName='" + offerName + '\'' +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", messages=" + messages +
                ", markAsRead=" + markAsRead +
                '}';
    }
}
