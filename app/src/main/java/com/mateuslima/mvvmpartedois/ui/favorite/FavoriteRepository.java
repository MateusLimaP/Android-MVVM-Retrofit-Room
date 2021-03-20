package com.mateuslima.mvvmpartedois.ui.favorite;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mateuslima.mvvmpartedois.data.db.FavoriteDatabase;
import com.mateuslima.mvvmpartedois.data.db.dao.FavoriteDao;
import com.mateuslima.mvvmpartedois.data.db.model.Favorite;

import java.util.List;

public class FavoriteRepository {
    private FavoriteDao favoriteDao;
    private LiveData<List<Favorite>> favoriteList;

    public FavoriteRepository(Application application){
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        favoriteDao = database.favoriteDao();
        favoriteList = favoriteDao.getAllFavorites();
    }

    public void deleteFavorite(Favorite favorite){
        new DeleteFavoriteAsyncTask(favoriteDao).execute(favorite);
    }

    public LiveData<List<Favorite>> getFavoriteList() {
        return favoriteList;
    }

    private static class DeleteFavoriteAsyncTask extends AsyncTask<Favorite, Void, Void>{
        private FavoriteDao favoriteDao;

        public DeleteFavoriteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favoriteDao.delete(favorites[0]);
            return null;
        }
    }
}
