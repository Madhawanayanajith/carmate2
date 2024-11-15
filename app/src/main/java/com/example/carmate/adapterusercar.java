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

public class adapterusercar extends RecyclerView.Adapter<adapterusercar.ViewHolder>{
      ArrayList<usercar> list;
      Context context;

    public adapterusercar(ArrayList<usercar> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_car,parent,false);
        return       new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        usercar model = list.get(position);
        Picasso.get().load(model.getImageView1()).placeholder(R.drawable.t).into(holder.image);
        holder.carname.setText(model.getUcarname());
        holder.username.setText(model.getUsername());
        holder.price.setText(model.getUprice());
        holder.city.setText(model.getUcity());




        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SingleusercarActivity.class);
                intent.putExtra("s_user_car_name",model.getUcarname());
                intent.putExtra("s_user_car_edition",model.getUedition());
                intent.putExtra("s_user_car_brand",model.getUbrand());
                intent.putExtra("s_user_car_type",model.getUtype());
                intent.putExtra("s_user_car_manufactured_year",model.getUmanufacturedyear());
                intent.putExtra("s_user_car_registered_year",model.getUregisteredyear());
                intent.putExtra("s_user_car_range",model.getUrange());
                intent.putExtra("s_user_car_details",model.getUcardetails());
                intent.putExtra("s_user_name",model.getUsername());
                intent.putExtra("s_user_contact_number",model.getUcontactnumber());
                intent.putExtra("s_user_car_city",model.getUcity());
                intent.putExtra("s_user_price",model.getUprice());
                intent.putExtra("imageView4",model.getImageView1());
                intent.putExtra("imageView5",model.getImageView2());
                intent.putExtra("imageView6",model.getImageView3());
                intent.putExtra("imageView7",model.getImageView4());
                //intent.putExtra("videoView5", model.getAvideoView());
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

           TextView carname,username,price,city;
           ImageView image;
          public ViewHolder(@NonNull View itemView) {
              super(itemView);

                   carname = itemView.findViewById(R.id.i_textview_user_car_name);
                   username = itemView.findViewById(R.id.i_textview_user_name);
                   price = itemView.findViewById(R.id.i_textview_user_car_price);
                   city = itemView.findViewById(R.id.i_textview_user_car_city);
                   image = itemView.findViewById(R.id.the_user_car_image);
          }
      }
}
