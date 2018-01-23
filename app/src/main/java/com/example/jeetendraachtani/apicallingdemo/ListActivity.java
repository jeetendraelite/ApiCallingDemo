package com.example.jeetendraachtani.apicallingdemo;




import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        Context ctx;
        public ArrayList<SurveyModel> data;
        public RecyclerAdapter(Context ctx, ArrayList<SurveyModel> temdata) {
            this.ctx=ctx;
            this.data=temdata;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view=inflater.inflate(R.layout.list_item,null);


            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            SurveyModel surveyModel;

            SurveyModel product= data.get(position);
            holder.question_order.setText("question_order ="+product.question_order);
            holder.options.setText("Options ="+product.options);
            holder.question_id.setText("Question_Id = "+product.question_id);
            holder.survey_title.setText("Survey_Title ="+product.survey_title);
            holder.survey_id.setText("Survey_ID ="+product.survey_id);


        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView survey_id,question,options,question_id,survey_title,question_order;

            public ViewHolder(View itemView) {
                super(itemView);

                survey_id=itemView.findViewById(R.id.tv_survey_id);
                options=itemView.findViewById(R.id.tv_options);
                question=itemView.findViewById(R.id.tv_question);
                question_id=itemView.findViewById(R.id.tv_question_id);
                survey_title=itemView.findViewById(R.id.tv_survey_title);
                question_order=itemView.findViewById(R.id.tv_question_order);
            }


        }
    }

}

