package com.ooslab.messfee;

public class Fee {
    private  String name;
    private double amount;
    public Fee(String name, double amount){
        this.name=name;
        this.amount=amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}
