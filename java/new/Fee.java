package com.ooslab.messfee;

public class Fee {
    private String name;
    private double total;
    public Fee(String name, double total){
        this.name=name;
        this.total=total;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total+=total;
    }
}
