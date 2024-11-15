package com;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carmate.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter2 extends RecyclerView.Adapter<adapter2.ViewHolder>{

      ArrayList<car2> list;
      Context context;

    public adapter2(ArrayList<car2> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setFilteredList(ArrayList<car2> filteredList){
        this.list = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
               car2 model = list.get(position);
               Picasso.get().load(model.getThecarimage1()).placeholder(R.drawable.a).into(holder.carimage);
               holder.carname.setText(model.getThecarname1());
               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent = new Intent(context, SinglecarActivity2.class);
                       intent.putExtra("imageViewcar2",model.getThecarimage1());
                       intent.putExtra("s_textview_car_name2",model.getThecarname1());
                       intent.putExtra("s_textview_editions2",model.getTheeditions1());
                       intent.putExtra("s_textview_type2",model.getThetype1());
                       intent.putExtra("s_textview_manufactured_year2",model.getThemanufacturedyear1());
                       intent.putExtra("s_textview_brand2",model.getThebrand1());
                       intent.putExtra("s_textview_price2",model.getTheprice1());
                       intent.putExtra("s_textview_fuel_consumption2",model.getThefuelconsumption1());
                       intent.putExtra("s_textview_seats2",model.getTheseats1());
                       intent.putExtra("s_textview_specifications2",model.getThespecifications1());
                       intent.putExtra("s_textview_pros2",model.getThepros1());
                       intent.putExtra("s_textview_cons2",model.getThecons1());
                       intent.putExtra("s_textview_service_cost2",model.getTheservicecost1());
                       intent.putExtra("s_textview_certificates2",model.getThecertificates1());
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
              carname = itemView.findViewById(R.id.i_textview_car_name1);
              carimage = itemView.findViewById(R.id.a_car_image1);
          }
      }
}
