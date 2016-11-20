package com.asee.alberto.eventfly.model;

import io.realm.RealmObject;
import io.realm.Realm;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 19/11/16.
 */

public class TagDB extends RealmObject{
    /**
     * Tag database model
     * The purpose of this class is act like a hashtag.
     */
    //Name of the tag
    @PrimaryKey
    private String tagname;

    public TagDB(){

    }

    public TagDB(String tagname) {
        this.tagname = tagname;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
