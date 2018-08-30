package com.example.root.myapplication.ViewModels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import android.os.Handler;
import com.example.root.myapplication.AddNewFruit;
import com.example.root.myapplication.LocalRepository.Fruit;
import com.example.root.myapplication.LocalRepository.FruitDatabase;
import com.example.root.myapplication.controllers.LiveBus;
import com.example.root.myapplication.events.StartActivityEvent;


/**
 * Created by root on 19/08/18.
 */

public class MainActivityViewModel extends ViewModel {

    private String TAG = MainActivityViewModel.class.getSimpleName();
    private MutableLiveData<List<Fruit>> fruitList;
    private Context context;
    public String title = "title";
    public final LiveBus liveBus = new LiveBus();
    public LiveData<List<Fruit>> getFruitList() {
        if (fruitList == null) {
            fruitList = new MutableLiveData<>();
            loadFruits();
        }
        return fruitList;
    }

    public void loadFruits() {
        // do async operation to fetch users
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Fruit> fruitsStringList = FruitDatabase.getInstance(context).getFruitDao().getAllFruits();
                if(fruitsStringList==null)fruitsStringList = new ArrayList<>();
                if(fruitList==null)fruitList = new MutableLiveData<>();
                fruitList.postValue(fruitsStringList);
            }
        }).start();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

    public void setContext(Context context){
        this.context = context;
    }
}
