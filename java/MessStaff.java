package com.ooslab.messfee;

import java.util.ArrayList;
import java.util.Iterator;

public class MessStaff extends  User{
    private ArrayList<Student> students;
    private ArrayList<FoodItem> foodItems;
    public MessStaff(){
        super();
    }
    public MessStaff(String username, String name,String email, String dob, String hostel){
        super(username, name, email, dob, hostel);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
    public void addFoodItem(FoodItem foodItem){
        foodItems.add(foodItem);
    }
    public void removeFoodItem(String foodName){
        Iterator itr= foodItems.iterator();
        while (itr.hasNext()) {
            if (itr.getClass().getName().equals(foodName)) {
                itr.remove();
                return;
            }
        }
    }
}
