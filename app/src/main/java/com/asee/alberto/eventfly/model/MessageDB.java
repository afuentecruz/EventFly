package com.asee.alberto.eventfly.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 20/11/16.
 */

public class MessageDB extends RealmObject {

    /**
     * Message model for database
     */

    // Event id which belongs the message
    @PrimaryKey
    private String idEvent;
    // Event name which belongs the message
    private String eventName;
    // Content of the message
    private String body;

    public MessageDB(){
        idEvent = UUID.randomUUID().toString();
    }

    public MessageDB(String idEvent, String body) {
        this.idEvent = idEvent;
        this.body = body;
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
}
