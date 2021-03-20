package com.mateuslima.mvvmpartedois.util;

public class Resource<T> {

    private T data;
    private Status status;
    private String mensagem;

    public Resource(Status status, T data, String mensagem) {
        this.status = status;
        this.data = data;
        this.mensagem = mensagem;
    }

    public static  <T> Resource <T> sucess(T data){
        return new Resource<>(Status.SUCESSO, data, null);
    }

    public static <T> Resource <T> erro(String mensagem, T data){
        return new Resource<>(Status.ERRO, data, mensagem);
    }

    public static <T> Resource <T> carregando(){
        return new Resource<>(Status.CARREGANDO, null, null);
    }

    public T getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public enum Status{
        SUCESSO, ERRO, CARREGANDO
    }
}
