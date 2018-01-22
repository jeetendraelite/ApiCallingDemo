package com.example.jeetendraachtani.apicallingdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

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
        holder.question_order.setText(product.question_order);
        holder.options.setText(product.options);
        holder.question_id.setText(product.question_id);
        holder.survey_title.setText(product.survey_title);
        holder.survey_id.setText(product.survey_id);


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
