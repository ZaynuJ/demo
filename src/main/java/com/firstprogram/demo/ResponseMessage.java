package com.firstprogram.demo;

public class ResponseMessage {


    public Diary diary;
    public Author author;
    private AppError appError;

    public ResponseMessage(Diary diary, AppError appError) {
        this.diary = diary;
        this.appError = appError;
    }
    public ResponseMessage() {

    }

    public ResponseMessage(Author author, AppError appError) {
        this.author = author;
        this.appError = appError;
    }

    public ResponseMessage(AppError appError) {
        this.appError = appError;
    }

    public AppError getAppError() {
        return appError;
    }

    public void setAppError(AppError appError) {
        this.appError = appError;
    }
    
    
}
