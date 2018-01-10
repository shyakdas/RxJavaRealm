package com.example.tnl.realmrxjava.remote;

import com.example.tnl.realmrxjava.model.Model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by tnl on 1/10/2018;
 */

public interface Service {

    @GET("android/jsonarray/")
    Observable<List<Model>> register();
}
