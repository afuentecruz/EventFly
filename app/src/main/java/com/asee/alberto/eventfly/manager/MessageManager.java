package com.asee.alberto.eventfly.manager;

import android.util.Log;

import com.asee.alberto.eventfly.model.MessageDB;
import com.asee.alberto.eventfly.model.MessageDto;

import java.util.List;

import io.realm.Realm;

/**
 * Created by alberto on 20/11/16.
 */

public class MessageManager {
    /**
     * UserDTO database manager
     */

    private static String TAG = "MessageManager";

    /**
     * Save or update a message object in DB
     * @param message, message object to save
     */
    public static void saveOrUpdateMessage(MessageDB message){

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(message);
        realm.commitTransaction();
        Log.i(TAG, " >>> Saved in db message " + message.getBody());
    }

    /**
     * This methods gets all the messages associated to an event
     * @param eventName, name of the event that we want to consult
     * @return List of MessageDB objects
     */
    public static List<MessageDB> getMessageByEvent(String eventName){

        List<MessageDB> messageDBList;
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        messageDBList = realm.where(MessageDB.class).equalTo("eventName", eventName).findAll();
        realm.commitTransaction();

        return messageDBList;
    }

    public static MessageDB messageDtoToMessageDB(MessageDto message){
        return new MessageDB(message.getIdEvent(), message.getBody(), message.getEventName());
    }

}
