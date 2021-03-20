package com.mateuslima.mvvmpartedois.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mateuslima.mvvmpartedois.data.db.model.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("DELETE FROM favorite_table")
    void deleteAllFavorites();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Favorite>> getAllFavorites();
}
