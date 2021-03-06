package com.afifrdzf.infinitychat;

/**
 * Created by Afifrdzf on 5/8/2017.
 */

import java.util.Date;

/**
 * Created by Hathibelagal on 7/10/16.
 */
public class ChatMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;
    
    //this is the constructor to set the date of chat

    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
