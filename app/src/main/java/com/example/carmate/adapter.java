package com.example.carmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{
                     ArrayList<car> list;
                     Context context;

 public adapter(ArrayList<car> list , Context context){
               this.list = list;
               this.context = context;


 }

    public void setFilteredList(ArrayList<car> filteredList){
        this.list = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View     view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     car model = list.get(position);
     Picasso.get().load(model.getThecarimage()).placeholder(R.drawable.a).into(holder.carimage);
     holder.carname.setText(model.getThecarname());
     holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context,SinglecarActivity.class);
                intent.putExtra("imageViewcar1",model.getThecarimage());
                intent.putExtra("s_textview_car_name",model.getThecarname());
                intent.putExtra("s_textview_editions",model.getTheeditions());
                intent.putExtra("s_textview_type",model.getThetype());
                intent.putExtra("s_textview_manufactured_year",model.getThemanufacturedyear());
                intent.putExtra("s_textview_brand",model.getThebrand());
                intent.putExtra("s_textview_price",model.getTheprice());
                intent.putExtra("s_textview_fuel_consumption",model.getThefuelconsumption());
                intent.putExtra("s_textview_seats",model.getTheseats());
                intent.putExtra("s_textview_specifications",model.getThespecifications());
                intent.putExtra("s_textview_pros",model.getThepros());
                intent.putExtra("s_textview_cons",model.getThecons());
                intent.putExtra("s_textview_service_cost",model.getTheservicecost());
                intent.putExtra("s_textview_certificates",model.getThecertificates());
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
           TextView carname;
           ImageView carimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carname = itemView.findViewById(R.id.i_textview_car_name);
            carimage = itemView.findViewById(R.id.a_car_image);
        }
    }
}


