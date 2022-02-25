package com.ooslab.messfee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Bill {
    private String dateOfGeneration;
    private ArrayList<FoodItem>foodItems;
    private double sum;
    public Bill(){
        Date dt= Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        this.dateOfGeneration=df.format(dt);
        this.sum=0;
    }

    public String getDateOfGeneration() {
        return dateOfGeneration;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public double getSum() {
        return sum;
    }
}
