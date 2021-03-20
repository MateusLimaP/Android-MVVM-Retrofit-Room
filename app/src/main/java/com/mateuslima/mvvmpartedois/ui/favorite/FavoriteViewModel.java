package com.mateuslima.mvvmpartedois.ui.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mateuslima.mvvmpartedois.data.db.model.Favorite;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private FavoriteRepository repository;
    private LiveData<List<Favorite>> favoriteList;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        favoriteList = repository.getFavoriteList();
    }

    public void deleteFavorite(Favorite favorite){
        repository.deleteFavorite(favorite);
    }

    public LiveData<List<Favorite>> getFavoriteList() {
        return favoriteList;
    }
}
