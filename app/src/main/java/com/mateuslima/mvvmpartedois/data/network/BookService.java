package com.mateuslima.mvvmpartedois.data.network;

import com.mateuslima.mvvmpartedois.data.network.result.BooksResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("volumes")
    Call<BooksResult> getLivros(
            @Query("q") String pesquisa
            );
}
