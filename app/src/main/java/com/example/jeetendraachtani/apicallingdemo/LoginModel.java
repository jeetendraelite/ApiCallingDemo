package com.example.jeetendraachtani.apicallingdemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public class LoginModel {

    @SerializedName("username")
    public String username;

    @SerializedName("fullname")
    public String fullname;


    @SerializedName("dob")
    public String dob;


    @SerializedName("gender")
    public String gender;


    @SerializedName("email")
    public String email;


   /* public String username;
    public String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/
}
