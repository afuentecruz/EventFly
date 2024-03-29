package com.asee.alberto.eventfly;

/**
 * Created by alberto on 15/11/16.
 */
import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this.getApplicationContext());
        // Realm DB default configuration
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("eventfly.realm")
                .schemaVersion(0)
                .build());
    }
}
