package com.example.tnl.realmrxjava.realm;

import com.example.tnl.realmrxjava.model.Model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by tnl on 1/10/2018;
 */

public class RealmHelper {
    private static RealmHelper instance;
    Realm realm;

    private RealmHelper() {

    }

    public static RealmHelper getInstance() {
        if (instance == null) {
            instance = new RealmHelper();
        }
        return instance;
    }

    public void add(final List<Model> model) {
        realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(model);
                }
            });
        } finally {
            realm.close();
        }
    }

    public List<Model> fetchData() {
        realm = Realm.getDefaultInstance();
        RealmResults<Model> realmResults;
        try {
            RealmQuery<Model> realmQuery = realm.where(Model.class);
            realmResults = realmQuery.findAll();
        } finally {

        }
        return realmResults;
    }

    public int getSize() {
        realm = Realm.getDefaultInstance();
        RealmResults<Model> realmResults;
        try {
            RealmQuery<Model> realmQuery = realm.where(Model.class);
            realmResults = realmQuery.findAll();
        } finally {

        }
        return realmResults.size();
    }
}
