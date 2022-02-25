package com.ooslab.messfee;

public class Item {
    private  String name;
    private  String price;
    Item(){}
    Item(String name, String price){
        this.name=name;this.price=price;
    }
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}