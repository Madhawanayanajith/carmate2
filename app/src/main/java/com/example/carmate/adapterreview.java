package com.example.carmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterreview extends RecyclerView.Adapter<adapterreview.ViewHolder>{

      ArrayList<review> list;
      Context context;

    public adapterreview(ArrayList<review> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(context).inflate(R.layout.itemreview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                review model = list.get(position);
        Picasso.get().load(model.getImageButton()).placeholder(R.drawable.r).into(holder.imageButton);
        holder.carname.setText(model.getCarname());
        holder.rate.setText(model.getRate());
        holder.name.setText(model.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SinglereviewActivity.class);
                intent.putExtra("imageButton2",model.getImageButton());
                intent.putExtra("s_textview_review_car_name",model.getCarname());
                intent.putExtra("s_textview_review_car_rate",model.getRate());
                intent.putExtra("s_textview_car_review",model.getReview());
                intent.putExtra("s_textview_review_name",model.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

          TextView carname,rate,name;
          ImageButton imageButton;

          public ViewHolder(@NonNull View itemView) {
              super(itemView);

                 carname = itemView.findViewById(R.id.i_textview_review_car_name);
                 rate = itemView.findViewById(R.id.i_textview_review_rate);
                 name = itemView.findViewById(R.id.i_textview_review_name);
                 imageButton = itemView.findViewById(R.id.the_car_image);

          }
      }
}




































