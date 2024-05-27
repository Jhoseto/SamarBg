package org.samarBg.views;

import java.util.Date;

public class MessagesViewModel {

    private Long offerId;

    private String senderUsername;

    private String senderEmail;

    private String receiverUsername;

    private String receiverEmail;

    private String messageText;

    private Date dateAndTime;


    public Long getOfferId() {
        return offerId;
    }

    public MessagesViewModel setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public MessagesViewModel setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
        return this;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public MessagesViewModel setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public MessagesViewModel setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
        return this;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public MessagesViewModel setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
        return this;
    }

    public String getMessageText() {
        return messageText;
    }

    public MessagesViewModel setMessageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public MessagesViewModel setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
        return this;
    }
}
