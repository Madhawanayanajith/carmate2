package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.CompareActivity;
import com.squareup.picasso.Picasso;

public class SinglecarActivity extends AppCompatActivity {

    private  ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlecar);

        TextView thecarname, theeditions, thetype, themanufacturedyear, thebrand, theprice, thefuelconsumption, theseats, thespecifications, thepros, thecons, theservicecost, thecertificates;
        ImageView thecarimage;
        Button comparecars,button;


        thecarname = findViewById(R.id.s_textview_car_name);
        theeditions = findViewById(R.id.s_textview_editions);
        thetype = findViewById(R.id.s_textview_type);
        themanufacturedyear = findViewById(R.id.s_textview_manufactured_year);
        thebrand = findViewById(R.id.s_textview_brand);
        theprice = findViewById(R.id.s_textview_price);
        thefuelconsumption = findViewById(R.id.s_textview_fuel_consumption);
        theseats = findViewById(R.id.s_textview_seats);
        thespecifications = findViewById(R.id.s_textview_specifications);
        thepros = findViewById(R.id.s_textview_pros);
        thecons = findViewById(R.id.s_textview_cons);
        theservicecost = findViewById(R.id.s_textview_service_cost);
        thecertificates = findViewById(R.id.s_textview_certificates);
        thecarimage = findViewById(R.id.imageViewcar1);
        comparecars = findViewById(R.id.compare);
        button = findViewById(R.id.button14);

        Picasso.get().load(getIntent().getStringExtra("imageViewcar1"))
                .placeholder(R.drawable.a)
                .into(thecarimage);
        thecarname.setText(getIntent().getStringExtra("s_textview_car_name"));
        theeditions.setText(getIntent().getStringExtra("s_textview_editions"));
        thetype.setText(getIntent().getStringExtra("s_textview_type"));
        themanufacturedyear.setText(getIntent().getStringExtra("s_textview_manufactured_year"));
        thebrand.setText(getIntent().getStringExtra("s_textview_brand"));
        theprice.setText(getIntent().getStringExtra("s_textview_price"));
        thefuelconsumption.setText(getIntent().getStringExtra("s_textview_fuel_consumption"));
        theseats.setText(getIntent().getStringExtra("s_textview_seats"));
        thespecifications.setText(getIntent().getStringExtra("s_textview_specifications"));
        thepros.setText(getIntent().getStringExtra("s_textview_pros"));
        thecons.setText(getIntent().getStringExtra("s_textview_cons"));
        theservicecost.setText(getIntent().getStringExtra("s_textview_service_cost"));
        thecertificates.setText(getIntent().getStringExtra("s_textview_certificates"));


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SearchFragment fragment = new SearchFragment();
               FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
               transaction.replace(R.id.fragment_container, fragment);
               transaction.addToBackStack(null);
               transaction.commit();

           }
       });

        // Set onClickListener for Compare button
        comparecars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCompareButtonClick();
            }
        });



    }
    private void onCompareButtonClick() {

        // Create a new intent to start the ComparisonActivity
        Intent intent = new Intent(SinglecarActivity.this, CompareActivity.class);

        // Put the data of the single car into the intent
        intent.putExtras(getIntent().getExtras());

        // Start the ComparisonActivity with the intent
        startActivity(intent);
    }





}
