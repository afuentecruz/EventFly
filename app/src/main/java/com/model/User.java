package com.model;
/**
 * Created by alberto on 15/11/16.
 */

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{
    /**
     * User com.com.model for database
     * */
    //Username
    private String name;

    //User email
    @PrimaryKey
    private String email;

    //User password
    private String password;

    //User avatar
    private String photo;

    public User() {
    }

    public User(String name, String email, String password, String photo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
