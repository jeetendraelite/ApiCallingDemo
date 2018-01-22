package com.example.jeetendraachtani.apicallingdemo;




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    Call<SurveyResponse> call;
    Retrofit retrofit;
    ApiInterface api;
    RecyclerView recyclerView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String baseUrl="http://192.168.4.99:5555/privee/";

        btn=findViewById(R.id.btn_submit1);

        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.GONE);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        api = retrofit.create(ApiInterface.class);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    call = api.listSurvey("67","NmeIHp7FixAW","4");

                    call.enqueue(new Callback<SurveyResponse>() {
                        @Override
                        public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {
                            SurveyResponse model2 = response.body();

                            Log.d("TAG","StAtUs: "+model2.status);
                            Log.d("TAG","MSG : "+ model2.msg);
                            for(int i=0;i<model2.data.size();i++){

                                Log.d("TAG","ArrayLIST : "+ model2.data.get(i).options);
                                Log.d("TAG","ArrayLIST : "+ model2.data.get(i).survey_id);
                                Log.d("TAG","ArrayLIST : "+ model2.data.get(i).question_type);
                                Log.d("TAG","ArrayLIST : "+ model2.data.get(i).survey_title);
                                Log.d("TAG","ArrayLIST : "+ model2.data.get(i).question_id);
                                Log.d("TAG","ArrayLIST : "+ model2.data.get(i).survey_title);
                                Log.d("TAG","ArrayLIST : "+ model2.data.get(i).question_order);


                            }
                            //Log.d("TAG","surveryid : "+ model2.data.));
                            if(model2.status.equalsIgnoreCase("1")){
                                recyclerView.setVisibility(View.VISIBLE);

                                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ListActivity.this,model2.data);
                                recyclerView.setAdapter(recyclerAdapter);

                            }

                        }

                        @Override
                        public void onFailure(Call<SurveyResponse> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
