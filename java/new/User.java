package com.ooslab.messfee;

import java.io.Serializable;

class User implements Serializable {
    private  String username;
    private  String name;
    private  String email;
    private  String dob;
    private String hostel;
    public User(){}
    public User(String username, String name,String email, String dob, String hostel){
        this.username=username;
        this.name=name;
        this.email=email;
        this.dob=dob;
        this.hostel=hostel;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getHostel() {
        return hostel;
    }
}

