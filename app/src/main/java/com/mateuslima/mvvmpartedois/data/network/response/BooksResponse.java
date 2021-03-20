package com.mateuslima.mvvmpartedois.data.network.response;

import com.google.gson.annotations.SerializedName;

public class BooksResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("volumeInfo")
    private BookVolumeInfo volumeInfo;

    private boolean isFavorite;

    public BooksResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(BookVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public boolean isFavorite() {
        return isFavorite;
    }


    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
