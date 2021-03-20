package com.mateuslima.mvvmpartedois.data.network.response;

import com.google.gson.annotations.SerializedName;

public class BookImageLink {

    @SerializedName("smallThumbnail")
    private String urlLowImage;
    @SerializedName("thumbnail")
    private String urlBigImage;

    public BookImageLink() {
    }

    public String getUrlLowImage() {
        return urlLowImage;
    }

    public void setUrlLowImage(String urlLowImage) {
        this.urlLowImage = urlLowImage;
    }

    public String getUrlBigImage() {
        return urlBigImage;
    }

    public void setUrlBigImage(String urlBigImage) {
        this.urlBigImage = urlBigImage;
    }
}
