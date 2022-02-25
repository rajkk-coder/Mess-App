package com.ooslab.messfee;

import java.util.ArrayList;

public class Student extends User {
    private double fee;
    private ArrayList<Fee> fees;

    public Student(){
        super();
    }
    public Student(String username, String name,String email, String dob, String hostel){
        super(username, name, email, dob, hostel);
        this.fee=0;
        fees=new ArrayList<>();
    }

    public double getFee() {
        return fee;
    }

    public ArrayList<Fee> getFees() {
        return fees;
    }

    public void setFees(Fee fee) {
        this.fees.add(fee);
    }
}
