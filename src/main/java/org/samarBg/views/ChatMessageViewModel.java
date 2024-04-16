package org.samarBg.views;

import java.time.LocalDateTime;

public class ChatMessageViewModel {
    private Long id;
    private Long senderId;
    private String senderName;
    private String content;
    private LocalDateTime time;


    public Long getId() {
        return id;
    }

    public ChatMessageViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getSenderId() {
        return senderId;
    }

    public ChatMessageViewModel setSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getSenderName() {
        return senderName;
    }

    public ChatMessageViewModel setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ChatMessageViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ChatMessageViewModel setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}
