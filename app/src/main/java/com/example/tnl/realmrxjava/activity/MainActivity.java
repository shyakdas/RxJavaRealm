package com.example.tnl.realmrxjava.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.tnl.realmrxjava.R;
import com.example.tnl.realmrxjava.adapter.DataAdapter;
import com.example.tnl.realmrxjava.model.Model;
import com.example.tnl.realmrxjava.realm.RealmHelper;
import com.example.tnl.realmrxjava.remote.Service;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.learn2crack.com/";
    private DataAdapter dataAdapter;
    private RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable;
    private ArrayList<Model> modelArrayList;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        initRecylerView();
        loadJson();
    }

    private void initRecylerView() {
        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(new ArrayList<Model>());
        recyclerView.setAdapter(dataAdapter);
    }

    private void loadJson() {
        Service service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service.class);

        compositeDisposable.add(service.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleError(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    private void handleResponse(List<Model> models) {
       /* modelArrayList = new ArrayList<>(models);
        dataAdapter = new DataAdapter(modelArrayList);
        recyclerView.setAdapter(dataAdapter);*/

        //Save the data into Realm Database
        RealmHelper.getInstance().add(models);
        populateDataToRecylerView();
    }

    private void populateDataToRecylerView() {
        List<Model> results = RealmHelper.getInstance().fetchData();
        if (results == null || results.size() == 0) {
            Toast.makeText(getApplicationContext(), "Network is not working", Toast.LENGTH_SHORT).show();
        } else {
            List<Model> list = new ArrayList<>();
            for (Model model : results) {
                Model dataModel = new Model(model.getName(), model.getVer(), model.getApi());
                list.add(dataModel);
            }
            dataAdapter.add(list);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
