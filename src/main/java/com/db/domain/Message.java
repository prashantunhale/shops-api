package com.db.domain;

/**
 * Created by Prashant on 23-04-2017.
 */
public class Message {

    private String message;

    public Message(){

    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
