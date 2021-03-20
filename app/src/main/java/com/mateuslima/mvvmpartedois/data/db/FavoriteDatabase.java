package com.mateuslima.mvvmpartedois.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mateuslima.mvvmpartedois.data.db.dao.FavoriteDao;
import com.mateuslima.mvvmpartedois.data.db.model.Favorite;

@Database(entities = Favorite.class, version = 2)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase instance;

    public abstract FavoriteDao favoriteDao();

    public static synchronized FavoriteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FavoriteDatabase.class, "favorite_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
