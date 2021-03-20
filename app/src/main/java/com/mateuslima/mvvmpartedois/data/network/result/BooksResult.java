package com.mateuslima.mvvmpartedois.data.network.result;

import com.google.gson.annotations.SerializedName;
import com.mateuslima.mvvmpartedois.data.network.response.BooksResponse;

import java.util.List;

public class BooksResult {

    @SerializedName("kind")
    private String tipo;
    @SerializedName("totalItems")
    private String totalItens;

    @SerializedName("items")
    private List<BooksResponse> booksResponseList;


    public BooksResult() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(String totalItens) {
        this.totalItens = totalItens;
    }

    public List<BooksResponse> getBooksResponseList() {
        return booksResponseList;
    }

    public void setBooksResponseList(List<BooksResponse> booksResponseList) {
        this.booksResponseList = booksResponseList;
    }
}
