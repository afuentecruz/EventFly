package com.asee.alberto.eventfly.manager;

import android.util.EventLog;
import android.util.Log;

import com.asee.alberto.eventfly.model.EventDB;
import com.asee.alberto.eventfly.model.EventDto;
import com.asee.alberto.eventfly.model.TagDB;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

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
    }

    public static String findIdByName(String name){

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        EventDB event = realm.where(EventDB.class).equalTo("name", name).findFirst();
        realm.commitTransaction();
        return event.getId();
    }

    /**
     * Returns all the events saved in realm
     */
    public static List<EventDB> getAllEvents(){

        List<EventDB> eventDBList;
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        eventDBList = realm.where(EventDB.class).findAll();
        realm.commitTransaction();
        return eventDBList;
    }

    /**
     * Saves a list of events in the DB
     */
    public static void saveEvents(List<EventDto> eventList) {

        List<EventDB> eventDBs = new ArrayList<>();

        for (EventDto e : eventList) {
            eventDBs.add(eventDTOtoDB(e));
        }

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(eventDBs);
        realm.commitTransaction();
    }

    /**
     * This method convert a DTO object to a DB object
     */
    private static EventDB eventDTOtoDB(EventDto e) {
        //id, event.getName(), event.getLatitude(), event.getLongitude(), event.getRadius()
        return new EventDB(e.getId(), e.getName(), e.getLatitude(), e.getLongitude(), e.getRadius());
    }


    /**
     * This method convert a DB object to a DTO object
     */
    private static EventDto eventDBtoDTO(EventDB e) {

        return new EventDto(e.getId(), e.getName(), e.getLatitude(), e.getLongitude(), e.getRadius(), e.getOwner(), tagRealmListToList(e.getTagList()));
    }

    /**
     * This method converts a Tag RealmList to Java List
     */
    private static List<String> tagRealmListToList(RealmList<TagDB> tagDBRealmList) {

        List<String> tagList = new ArrayList<>();

        for (TagDB t : tagDBRealmList) {
            tagList.add(t.getTagname());
        }
        return tagList;
    }

    /**
     * This method converts a Tag Java List to RealmList
     */
    private static RealmList<TagDB> tagListToRealmList(List<String> tagListString) {

        RealmList<TagDB> tagDBRealmList = new RealmList<>();

        for (String tag : tagListString) {
            tagDBRealmList.add(new TagDB(tag));
        }
        return tagDBRealmList;
    }

}
