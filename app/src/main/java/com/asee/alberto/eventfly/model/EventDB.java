package com.asee.alberto.eventfly.model;

import android.app.usage.UsageEvents;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 19/11/16.
 */

public class EventDB extends RealmObject {
    /**
     * EventDB database model
     */
    //Event id
    private String id;
    //Name of the event
    @PrimaryKey
    private String name;
    //Description for event
    private String description;
    //Image for event
    private String image;
    //User that created the event
    private String owner;
    //Longitude of the event position
    private double longitude;
    //Latitude of the event position
    private double latitude;
    //Radius of event action
    private float radius;
    //List of tags associated to an event
    private RealmList<TagDB> tagList;

    public EventDB(){

    }

    public EventDB(String name, String description, String image, String owner, double longitude, double latitude, float radius, RealmList<TagDB> tagList) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.owner = owner;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.tagList = tagList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public RealmList<TagDB> getTagList() {
        return tagList;
    }

    public void setTagList(RealmList<TagDB> tagList) {
        this.tagList = tagList;
    }
}
