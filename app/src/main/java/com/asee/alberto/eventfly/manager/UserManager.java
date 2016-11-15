package com.asee.alberto.eventfly.manager;

/**
 * Created by alberto on 15/11/16.
 */

import com.asee.alberto.eventfly.model.UserDB;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UserManager {
    /**
     * UserDB database manager
     */

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
}
