package com.ooslab.messfee;

public class Notification {
    private  String message;
    private String time;
    public Notification(){

    }
    public Notification(String name, String time) {
        this.message = name;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

}
