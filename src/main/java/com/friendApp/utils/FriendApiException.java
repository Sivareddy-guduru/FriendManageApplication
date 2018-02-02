package com.friendApp.utils;

import org.springframework.http.HttpStatus;

/**
 * Created by sivareddy.guduru on 31/1/18.
 */
@SuppressWarnings("serial")
public class FriendApiException extends Exception{


        private HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

         String message;

        public FriendApiException(HttpStatus httpStatus, String message) {
            super();
            this.httpStatus = httpStatus;
            this.message = message;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


