package org.samarBg.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private ConversationEntity conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Long getId() {
        return id;
    }

    public MessageEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ConversationEntity getConversation() {
        return conversation;
    }

    public MessageEntity setConversation(ConversationEntity conversation) {
        this.conversation = conversation;
        return this;
    }

    public UserEntity getSender() {
        return sender;
    }

    public MessageEntity setSender(UserEntity sender) {
        this.sender = sender;
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
}
