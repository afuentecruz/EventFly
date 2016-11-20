package com.asee.alberto.eventfly.manager;

import android.util.Log;

import com.asee.alberto.eventfly.model.EventDB;

import io.realm.Realm;

/**
 * Created by alberto on 20/11/16.
 */

public class EventManager {
    /**
     * Event database manager
     */

    private static String TAG = "EventManager";

    public static void saveOrUpdateEvent(EventDB event){

        Realm realm = Realm.getDefaultInstance(); //instantiate RealmDB

        //Save or update event
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(event);
        realm.commitTransaction();
        Log.i(TAG, " >>> Saved in db " + event);
    }
}
