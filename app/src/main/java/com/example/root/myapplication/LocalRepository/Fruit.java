package com.example.root.myapplication.LocalRepository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Fruit implements Serializable {
    @NonNull
    @PrimaryKey
    private final String Id;
    private final String Name;
    private final int Cost;

    public Fruit(String Id, String Name, int Cost){
        this.Id = Id;
        this.Name = Name;
        this.Cost = Cost;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getCost() {
        return Cost;
    }
}
