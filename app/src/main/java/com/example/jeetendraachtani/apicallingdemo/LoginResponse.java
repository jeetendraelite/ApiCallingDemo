package com.example.jeetendraachtani.apicallingdemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public class LoginResponse extends CommonResponse {

    @SerializedName("data")
    public LoginModel data;



}
