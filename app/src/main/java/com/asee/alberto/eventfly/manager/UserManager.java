package com.asee.alberto.eventfly.manager;

/**
 * Created by alberto on 15/11/16.
 */

import android.util.Log;

import com.asee.alberto.eventfly.model.UserDB;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class UserManager {
    /**
     * UserDB database manager
     */

    private static String TAG = "UserManager";

    public static void saveOrUpdateUser(UserDB user){

        Realm realm = Realm.getDefaultInstance(); //instantiate realmDB

        //Save or update user
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }

    public static UserDB getUser(){

        return Realm.getDefaultInstance().where(UserDB.class).findFirst();
    }

    public static UserDB getUserByName(String username){
        UserDB user = Realm.getDefaultInstance().where(UserDB.class).equalTo("name", username).findFirst();
        Log.d(TAG, " >>> getUserByName: " + user);

        return user;
    }

    public static String getUserToken() {

        return Realm.getDefaultInstance().where(UserDB.class).findFirst().getToken();
    }

    public static void saveUserToken(String token) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        UserDB user = realm.where(UserDB.class).findFirst();
        user.setToken(token);
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }
}
