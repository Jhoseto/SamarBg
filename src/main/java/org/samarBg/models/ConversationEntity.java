package org.samarBg.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conversations")
public class ConversationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "offer_id", nullable = false)
    private Long offerId;

    @Column(name = "offer_name", nullable = false)
    private String offerName;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private UserEntity buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private UserEntity seller;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MessageEntity> messages;

    @Column(name = "mark_as_read", nullable = false)
    private int markAsRead;

    public Long getId() {
        return id;
    }

    public ConversationEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOfferId() {
        return offerId;
    }

    public ConversationEntity setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getOfferName() {
        return offerName;
    }

    public ConversationEntity setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public ConversationEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public ConversationEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public ConversationEntity setMessages(List<MessageEntity> messages) {
        this.messages = messages;
        return this;
    }

    public int getMarkAsRead() {
        return markAsRead;
    }

    public ConversationEntity setMarkAsRead(int markAsRead) {
        this.markAsRead = markAsRead;
        return this;
    }
}
