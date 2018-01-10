package com.example.tnl.realmrxjava.application;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by tnl on 1/10/2018;
 */

public class MyApplication extends Application implements RealmMigration {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Network")
                .schemaVersion(1)
                .migration(this)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
