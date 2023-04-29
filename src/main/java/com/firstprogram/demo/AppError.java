package com.firstprogram.demo;

import org.springframework.http.HttpStatus;

public class AppError {
    
        private HttpStatus statusCode;
        private String message;
    
        public HttpStatus getStatusCode() {
            return statusCode;
        }
    
        public void setStatusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
        }
    
        public String getMessage() {
            return message;
        }
    
        public void setMessage(String message) {
            this.message = message;
        }
    
        public AppError() {
        }
    
        public AppError(HttpStatus statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }
    }

