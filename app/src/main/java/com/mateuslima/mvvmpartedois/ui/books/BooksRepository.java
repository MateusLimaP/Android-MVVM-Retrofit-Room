package com.mateuslima.mvvmpartedois.ui.books;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mateuslima.mvvmpartedois.data.db.FavoriteDatabase;
import com.mateuslima.mvvmpartedois.data.db.dao.FavoriteDao;
import com.mateuslima.mvvmpartedois.data.db.model.Favorite;
import com.mateuslima.mvvmpartedois.util.Resource;
import com.mateuslima.mvvmpartedois.data.network.ApiServices;
import com.mateuslima.mvvmpartedois.data.network.response.BooksResponse;
import com.mateuslima.mvvmpartedois.data.network.result.BooksResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksRepository {

    private MutableLiveData<Resource<List<BooksResponse>>> responseList = new MutableLiveData<>();
    private LiveData<List<Favorite>> favoriteList;
    private FavoriteDao favoriteDao;
    private Application application;

    public BooksRepository(Application application) {
        this.application = application;
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        favoriteDao = database.favoriteDao();
        favoriteList = favoriteDao.getAllFavorites();

    }

    public void pesquisarLivro(String pesquisa){
        responseList.setValue(Resource.<List<BooksResponse>>loading());
        ApiServices.getInstance().getLivros(pesquisa)
                .enqueue(new Callback<BooksResult>() {
                    @Override
                    public void onResponse(Call<BooksResult> call, Response<BooksResult> response) {
                        if (response.isSuccessful()){

                            if (favoriteList.getValue() != null) {
                                for (Favorite favorite : favoriteList.getValue()) {
                                    for (BooksResponse livro : response.body().getBooksResponseList()) {
                                        if (favorite.getBookId().equals(livro.getId()))
                                            livro.setFavorite(true);
                                    }
                                }
                            }

                            responseList.postValue(Resource.sucess(response.body().getBooksResponseList()));
                        }else{
                            Log.i("INFO","erro "+response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<BooksResult> call, Throwable t) {
                        responseList.setValue(Resource.<List<BooksResponse>>erro(t.getMessage(), null));
                    }
                });

    }

    public void insertFavorite(Favorite favorite){
        new FavoriteAsyncTask(favoriteDao, "insert").execute(favorite);
    }

    public void deleteFavorite(Favorite favorite){
        new FavoriteAsyncTask(favoriteDao, "delete").execute(favorite);
    }


    public LiveData<Resource<List<BooksResponse>>> getResponseList() {
        return responseList;
    }

    public LiveData<List<Favorite>> getFavoriteList() {
        return favoriteList;
    }

    private static class FavoriteAsyncTask extends AsyncTask<Favorite, Void, Void>{
        private FavoriteDao favoriteDao;
        private String option;

        public FavoriteAsyncTask(FavoriteDao favoriteDao, String option) {
            this.favoriteDao = favoriteDao;
            this.option = option;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            switch (option){
                case "insert":
                    favoriteDao.insert(favorites[0]);
                    break;
                case "delete":
                    favoriteDao.delete(favorites[0]);
                    break;
                default:
                    break;
            }
            return null;
        }
    }
}
