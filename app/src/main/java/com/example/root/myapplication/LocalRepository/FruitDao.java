package com.example.root.myapplication.LocalRepository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FruitDao {
    @Query("SELECT * from fruit")
    List<Fruit> getAllFruits();

    @Insert
    void insert(Fruit... fruits);

    @Update
    void update(Fruit... fruits);

    @Delete
    void delete(Fruit... fruits);

    @Query("Select * from fruit where Id =:Id")
    Fruit getFruitById(String Id);
}

