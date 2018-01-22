package com.example.jeetendraachtani.apicallingdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public interface ApiInterface {


    @FormUrlEncoded
    @POST("rest/login")
    Call<LoginResponse> Login(
            @Field("username") String username,
            @Field("password") String email
    );
}
