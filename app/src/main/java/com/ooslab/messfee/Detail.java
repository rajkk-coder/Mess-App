package com.ooslab.messfee;

public class Detail {
    private String id;
    private String name;
    public Detail(){

    }
    public Detail(String id, String name){
        this.id=id;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
