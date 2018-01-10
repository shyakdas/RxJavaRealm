package com.example.tnl.realmrxjava.model;

/**
 * Created by tnl on 1/10/2018;
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmObject;

public class Model extends RealmObject implements RealmModel {

    @SerializedName("ver")
    @Expose
    private String ver;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("api")
    @Expose
    private String api;

    public Model(){

    }

    public Model(String ver, String name, String api) {
        this.ver = ver;
        this.name = name;
        this.api = api;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

}