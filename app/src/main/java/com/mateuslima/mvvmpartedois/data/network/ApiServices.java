package com.mateuslima.mvvmpartedois.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {

    private static BookService instance;

    public static BookService getInstance(){

        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/books/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(BookService.class);
        }
        return instance;
    }

}
