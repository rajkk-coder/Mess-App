package com.ooslab.messfee;

public class Order {
    private String name;
    private String price;
    private String quantity;
    public Order(){}
    public Order(String name, String price, String  quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalPrice() {
        return price;
    }
}
