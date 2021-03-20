package com.mateuslima.mvvmpartedois.data.network.response;

import com.google.gson.annotations.SerializedName;

public class BookVolumeInfo {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("pageCount")
    private String pageCount;
    @SerializedName("imageLinks")
    private BookImageLink imageLink;

    public BookVolumeInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public BookImageLink getImageLink() {
        return imageLink;
    }

    public void setImageLink(BookImageLink imageLink) {
        this.imageLink = imageLink;
    }
}
