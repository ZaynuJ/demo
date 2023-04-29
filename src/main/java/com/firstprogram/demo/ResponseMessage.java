package com.firstprogram.demo;

public class ResponseMessage {

    public Diary diary;
    private AppError appError;


    public ResponseMessage(Diary diary, AppError appError) {
        this.diary = diary;
        this.appError = appError;
    }



    public ResponseMessage(AppError appError) {
        this.appError = appError;
    }

    
    public long id;
    public String author;
    public AppError appError;

    public ResponseMessage(long id, String author, AppError appError) {
        this.id = id;
        this.author = author;
        this.appError = appError;
    }

    public ResponseMessage(long id, String author) {
        this.id = id;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public AppError getAppError() {
        return appError;
    }

    public void setAppError(AppError appError) {
        this.appError = appError;
    }
    
    
}
