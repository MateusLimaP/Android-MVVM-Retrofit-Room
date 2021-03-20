package com.mateuslima.mvvmpartedois.ui.books;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mateuslima.mvvmpartedois.data.db.model.Favorite;
import com.mateuslima.mvvmpartedois.util.Resource;
import com.mateuslima.mvvmpartedois.data.network.response.BooksResponse;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {
    private BooksRepository repository;
    private LiveData<Resource<List<BooksResponse>>> booksResponseList;
    private LiveData<List<Favorite>> favoriteList;


    public BooksViewModel(@NonNull Application application) {
        super(application);
        repository = new BooksRepository(application);
        booksResponseList  = repository.getResponseList();
        favoriteList = repository.getFavoriteList();


    }

    public void pesquisarLivro(String pesquisa){
        repository.pesquisarLivro(pesquisa);

    }

    public void addFavorite(BooksResponse book){
        Favorite favorite = new Favorite();
        favorite.setBookName(book.getVolumeInfo().getTitle());
        favorite.setBookId(book.getId());
        if (book.getVolumeInfo().getImageLink() != null)
            favorite.setBookImageUrl(book.getVolumeInfo().getImageLink().getUrlLowImage());
        favorite.setBookDescription(book.getVolumeInfo().getDescription());
        repository.insertFavorite(favorite);

    }
    public void removeFavorite(BooksResponse book){
        Favorite favorite = new Favorite();
        favorite.setBookName(book.getVolumeInfo().getTitle());
        favorite.setBookId(book.getId());
        if (book.getVolumeInfo().getImageLink() != null)
            favorite.setBookImageUrl(book.getVolumeInfo().getImageLink().getUrlLowImage());
        favorite.setBookDescription(book.getVolumeInfo().getDescription());
        repository.deleteFavorite(favorite);
    }

    public LiveData<Resource<List<BooksResponse>>> getBooksResponseList() {
        return booksResponseList;
    }

    public LiveData<List<Favorite>> getFavoriteList() {
        return favoriteList;
    }
}
