package com.asee.alberto.eventfly.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by alberto on 18/12/16.
 */

public class MessageDto {
    // idMessage (objectID)
    @SerializedName("_id")
    private String messageId;
    // Event id which belongs the message
    private String idEvent;
    // Event name which belongs the message
    private String eventName;
    // Content of the message
    private String body;
    // Token of the user that created the msg
    private String token;

    public MessageDto() {
    }

    public MessageDto(String body, String idEvent, String token) {
        this.body = body;
        this.idEvent = idEvent;
        this.token = token;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }
}
