package com.meancat.polyjackson.messages;

public class GameMessageHeader {

    private String messageId;
    // TODO other interesting message header stuff.


    public GameMessageHeader() {
    }

    public GameMessageHeader(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
