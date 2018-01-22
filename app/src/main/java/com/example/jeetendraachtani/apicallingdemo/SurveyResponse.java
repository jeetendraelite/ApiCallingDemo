package com.example.jeetendraachtani.apicallingdemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public class SurveyResponse extends CommonResponse {


    @SerializedName("data")
    public ArrayList<SurveyModel> data;

}
