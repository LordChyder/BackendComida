package com.coderdot.models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MessageResult {
    private List<String> messages;

    public MessageResult() {
        this.messages = new ArrayList<>();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String newMessage) {
        messages.add(newMessage);
    }
}