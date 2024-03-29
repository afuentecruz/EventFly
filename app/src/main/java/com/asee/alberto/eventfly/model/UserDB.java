package com.asee.alberto.eventfly.model;
/**
 * Created by alberto on 15/11/16.
 */

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserDB extends RealmObject{
    /**
     * UserDB database model
     * */
    //Username
    private String name;

    //UserDB email
    @PrimaryKey
    private String email;

    //UserDB password
    private String password;

    //UserDB avatar
    private String photo;

    //User token
    private String token;

    //User gcm token, void field but necessary to register in the api platform
    private String gcm_token;

    public UserDB() {
    }

    public UserDB(String name, String email, String password, String photo, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.token = token;
    }

    public UserDB(String name, String email, String password, String gcmToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gcm_token = gcmToken;
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

    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getGcmToken(){
        return this.gcm_token;
    }

    public void setGcmToken(String gcmToken){
        this.gcm_token = gcmToken;
    }
}
