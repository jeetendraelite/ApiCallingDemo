package com.example.jeetendraachtani.apicallingdemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public class SurveyModel {

    @SerializedName("survey_id")
    public String survey_id;

    @SerializedName("question")
    public String question;

    @SerializedName("question_type")
    public String question_type;


    @SerializedName("question_order")
    public String question_order;

    @SerializedName("options")
    public String options;


    @SerializedName("survey_title")
    public String survey_title;

    @SerializedName("question_id")
    public String question_id;
}
