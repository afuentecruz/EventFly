package com.asee.alberto.eventfly.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alberto on 18/12/16.
 */

public class EventDto {

    //The Event id
    @SerializedName("_id")
    private String id;

    //Name of the event
    private String name;

    //User that created the event
    private String owner;

    //Longitude of the event position
    private double longitude;

    //Latitude of the event position
    private double latitude;

    //Radius of event action
    private float radius;

    //List of tags associated to an event
    private List<String> tagList;

    //Auth token in order to post into the server
    private String token;

    public EventDto() {
    }

    public EventDto (String id, String name, double latitude, double longitude, float radius, String owner, List<String> tagList){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.tagList = tagList;
    }

    public EventDto(String name, double longitude, double latitude, float radius, String token) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.token = token;
    }

    public EventDto(String name, double longitude, double latitude, float radius, List<String> tagList) {
        this.name = name;
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

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
