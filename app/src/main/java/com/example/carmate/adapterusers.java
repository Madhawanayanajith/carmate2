package com.example.carmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterusers extends RecyclerView.Adapter<adapterusers.ViewHolder>{

      ArrayList<users> list;
      Context context;

    public adapterusers(ArrayList<users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemuser,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          users model = list.get(position);
          holder.name.setText(model.getName());
          holder.email.setText(model.getEmail());
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
           TextView name,email;
           public ViewHolder(@NonNull View itemView) {
               super(itemView);
                    name = itemView.findViewById(R.id.i_textview_user);
                    email = itemView.findViewById(R.id.i_textview_user_email);
           }
       }
}
