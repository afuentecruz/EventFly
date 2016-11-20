package com.asee.alberto.eventfly.manager;

import android.util.EventLog;
import android.util.Log;

import com.asee.alberto.eventfly.model.EventDB;

import java.util.List;

import io.realm.Realm;

/**
 * Created by alberto on 20/11/16.
 */

public class EventManager {
    /**
     * Event database manager
     */

    private static String TAG = "EventManager";

    /**
     * Save or update an event object in DB
     * @param event, event object to save
     */
    public static void saveOrUpdateEvent(EventDB event){

        Realm realm = Realm.getDefaultInstance(); //instantiate RealmDB

        //Save or update event
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(event);
        realm.commitTransaction();
        Log.i(TAG, " >>> Saved in db " + event.getName());
    }

    /**
     * Returns all the events saved in realm
     */
    public static List<EventDB> getAllEvents(){

        List<EventDB> eventDBList;
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        eventDBList = realm.allObjects(EventDB.class);
        realm.commitTransaction();
        return eventDBList;
    }
}
