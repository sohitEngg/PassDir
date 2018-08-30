package com.example.root.myapplication.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.root.myapplication.AddNewFruit;
import com.example.root.myapplication.LocalRepository.Fruit;
import com.example.root.myapplication.LocalRepository.FruitDatabase;
import com.example.root.myapplication.controllers.LiveBus;
import com.example.root.myapplication.events.CloseActivityEvent;

public class AddNewFruitViewModel extends ViewModel{
    Context context;
    public LiveBus liveBus = new LiveBus();
    public MutableLiveData<Fruit> fruit = new MutableLiveData<>();
    public void setContext(Context context){
        this.context = context;
    }

    public MutableLiveData<Fruit> getFruit(){
        if(fruit==null)fruit= new MutableLiveData<>();
        return fruit;
    }

    public void setFruit(Fruit f){
        fruit.setValue(f);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void addNewFruit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FruitDatabase.getInstance(context).getFruitDao().insert(fruit.getValue());
            }
        }).start();
    }
}
