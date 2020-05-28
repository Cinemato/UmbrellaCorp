package com.example.project;

import java.util.UUID;

public class Message
{
    String name, email, phoneNumber, message, messageId;

    public Message(String name, String email, String phoneNumber, String message)
    {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;

        UUID uuid = UUID.randomUUID();
        this.messageId = uuid.toString().substring(0, 8);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
