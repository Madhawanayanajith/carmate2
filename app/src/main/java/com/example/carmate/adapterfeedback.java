package com.example.carmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterfeedback extends RecyclerView.Adapter<adapterfeedback.ViewHolder>{
    ArrayList<feedback> list;
    Context context;

    public adapterfeedback(ArrayList<feedback> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(context).inflate(R.layout.item_feedback,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          feedback model = list.get(position);
          holder.feedback.setText(model.getFeedback());
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context,SinglefeedbackActivity.class);
                  intent.putExtra("s_textview_feedback",model.getFeedback());
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView feedback;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feedback = itemView.findViewById(R.id.i_textview_feedback);
        }
    }
}
