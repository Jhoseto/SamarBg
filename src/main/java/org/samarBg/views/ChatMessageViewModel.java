package org.samarBg.views;

import java.time.LocalDateTime;

public class ChatMessageViewModel {
    private Long id;
    private Long senderId;
    private String senderName;
    private String content;
    private LocalDateTime time;


    public ChatMessageViewModel(Long id, Long senderId, String senderName, String content, LocalDateTime time) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.time = time;
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
