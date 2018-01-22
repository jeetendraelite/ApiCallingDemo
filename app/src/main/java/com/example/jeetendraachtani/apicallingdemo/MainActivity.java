package com.example.jeetendraachtani.apicallingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Call<LoginResponse> call;
    Retrofit retrofit;
    ApiInterface api;
    Button btn;
    EditText eusername;
    EditText epassword;
    LinearLayout linearLayout;
    TextView res_fullname,res_username, res_email,res_dob,res_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn_submit);
        linearLayout=findViewById(R.id.layout);
        linearLayout.setVisibility(View.GONE);
        res_fullname=findViewById(R.id.response_fullname);
        res_username=findViewById(R.id.response_username);
        res_email=findViewById(R.id.response_email);
        res_dob=findViewById(R.id.response_dob);

        res_gender=findViewById(R.id.response_gender);
        eusername=findViewById(R.id.username);
        epassword=findViewById(R.id.password);

        String baseUrl="http://192.168.4.99:5555/privee/";


        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        api = retrofit.create(ApiInterface.class);
/*
        call = api.Login("RaawzzMehta","111111");

        Log.e("API Call", "username=" + "RaawzzMehta"+ "&password=111111");

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse model = response.body();
                Log.d("TAG","STATuS "+model.status);
                if (model.status.equalsIgnoreCase("1")) {
                    Log.d("TAG","Userlogin "+model.data.username);
                }
                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Eusername= eusername.getText().toString();
                String Epass= epassword.getText().toString();

                call = api.Login(Eusername,Epass);

               // Log.e("API Call", "username=" + "RaawzzMehta"+ "&password=111111");

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        LoginResponse model = response.body();
                        Log.d("TAG","STATuS "+model.status);
                        if (model.status.equalsIgnoreCase("1")) {
                            linearLayout.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Log.d("TAG","Userlogin "+model.data.username);
                            res_fullname.setText(model.data.fullname);
                            res_username.setText(model.data.username);
                            res_email.setText(model.data.email);
                            res_dob.setText(model.data.dob);
                            res_gender.setText(model.data.gender);


                        }
                        if(model.status.matches("2")){
                    Toast.makeText(MainActivity.this,model.msg, Toast.LENGTH_SHORT).show();
                    linearLayout.setVisibility(View.GONE);

                        }
                        //now we can do whatever we want with this list

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                       Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}
