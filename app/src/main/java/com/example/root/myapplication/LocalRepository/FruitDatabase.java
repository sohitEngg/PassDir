package com.example.root.myapplication.LocalRepository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Fruit.class}, version = 1)
public abstract class FruitDatabase extends RoomDatabase{
    public static final String DB_NAME = "fruitDatabase.db";
    private static volatile FruitDatabase instance;

    public static synchronized FruitDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }

        return instance;
    }

    private static FruitDatabase create(final Context context) {
        return Room.databaseBuilder(context, FruitDatabase.class, DB_NAME).build();
    }

    public abstract FruitDao getFruitDao();
}
