package com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carmate.R;
import com.squareup.picasso.Picasso;

public class SinglecarActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singlecar2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView thecarname1, theeditions1, thetype1, themanufacturedyear1, thebrand1, theprice1, thefuelconsumption1, theseats1, thespecifications1, thepros1, thecons1, theservicecost1, thecertificates1;
        ImageView thecarimage1;
        Button comparecars1;

        thecarname1 = findViewById(R.id.s_textview_car_name2);
        theeditions1 = findViewById(R.id.s_textview_editions2);
        thetype1 = findViewById(R.id.s_textview_type2);
        themanufacturedyear1 = findViewById(R.id.s_textview_manufactured_year2);
        thebrand1 = findViewById(R.id.s_textview_brand2);
        theprice1 = findViewById(R.id.s_textview_price2);
        thefuelconsumption1 = findViewById(R.id.s_textview_fuel_consumption2);
        theseats1 = findViewById(R.id.s_textview_seats2);
        thespecifications1 = findViewById(R.id.s_textview_specifications2);
        thepros1 = findViewById(R.id.s_textview_pros2);
        thecons1 = findViewById(R.id.s_textview_cons2);
        theservicecost1 = findViewById(R.id.s_textview_service_cost2);
        thecertificates1 = findViewById(R.id.s_textview_certificates2);
        thecarimage1 = findViewById(R.id.imageViewcar2);
        comparecars1 = findViewById(R.id.compare1);


        Picasso.get().load(getIntent().getStringExtra("imageViewcar2"))
                .placeholder(R.drawable.a)
                .into(thecarimage1);
        thecarname1.setText(getIntent().getStringExtra("s_textview_car_name2"));
        theeditions1.setText(getIntent().getStringExtra("s_textview_editions2"));
        thetype1.setText(getIntent().getStringExtra("s_textview_type2"));
        themanufacturedyear1.setText(getIntent().getStringExtra("s_textview_manufactured_year2"));
        thebrand1.setText(getIntent().getStringExtra("s_textview_brand2"));
        theprice1.setText(getIntent().getStringExtra("s_textview_price2"));
        thefuelconsumption1.setText(getIntent().getStringExtra("s_textview_fuel_consumption2"));
        theseats1.setText(getIntent().getStringExtra("s_textview_seats2"));
        thespecifications1.setText(getIntent().getStringExtra("s_textview_specifications2"));
        thepros1.setText(getIntent().getStringExtra("s_textview_pros2"));
        thecons1.setText(getIntent().getStringExtra("s_textview_cons2"));
        theservicecost1.setText(getIntent().getStringExtra("s_textview_service_cost2"));
        thecertificates1.setText(getIntent().getStringExtra("s_textview_certificates2"));

        // Set onClickListener for Compare button
        comparecars1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCompareButtonClick();
            }
        });
    }
    private void onCompareButtonClick() {
        // Create a new intent to start the ComparisonActivity
        Intent intent = new Intent(SinglecarActivity2.this, CompareActivity.class);

        // Put the data of the single car into the intent
        intent.putExtras(getIntent().getExtras());
        // Start the ComparisonActivity with the intent
        startActivity(intent);
    }


}
