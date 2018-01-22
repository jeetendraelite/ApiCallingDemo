package com.example.jeetendraachtani.apicallingdemo;

import java.util.ArrayList;
import java.util.List;

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

    @FormUrlEncoded
    @POST("rest/view_survey")
    Call<SurveyResponse> listSurvey(
            @Field("user_id") String user_id,
            @Field("access_token") String access_token,
            @Field("survey_id") String survey_id
    );
}
