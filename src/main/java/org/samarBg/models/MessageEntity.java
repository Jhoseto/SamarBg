package org.samarBg.models;

import org.samarBg.models.enums.OfferCategory;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "offer_id", nullable = false)
    private Long offerId;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "mark_as_read")
    private int markAsRead;

    public Long getId() {
        return id;
    }

    public MessageEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOfferId() {
        return offerId;
    }

    public MessageEntity setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public UserEntity getSender() {
        return sender;
    }

    public MessageEntity setSender(UserEntity sender) {
        this.sender = sender;
        return this;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public MessageEntity setReceiver(UserEntity receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public MessageEntity setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getMarkAsRead() {
        return markAsRead;
    }

    public MessageEntity setMarkAsRead(int markAsRead) {
        this.markAsRead = markAsRead;
        return this;
    }
}
