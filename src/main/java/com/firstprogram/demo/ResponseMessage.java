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


    public AppError getAppError() {
        return appError;
    }

    public void setAppError(AppError appError) {
        this.appError = appError;
    }
    
    
}
