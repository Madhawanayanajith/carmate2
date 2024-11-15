package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class SinglereviewActivity extends AppCompatActivity {

            TextView name,carname,rate,review;
            ImageButton theimagebutton;
            Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singlereview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.s_textview_review_name);
        carname = findViewById(R.id.s_textview_review_car_name);
        rate = findViewById(R.id.s_textview_review_car_rate);
        review = findViewById(R.id.s_textview_car_review);
        theimagebutton = findViewById(R.id.imageButton2);
        button = findViewById(R.id.button2);

        Picasso.get().load(getIntent().getStringExtra("imageButton2"))
                .placeholder(R.drawable.r)
                .into(theimagebutton);
        name.setText(getIntent().getStringExtra("s_textview_review_name"));
        carname.setText(getIntent().getStringExtra("s_textview_review_car_name"));
        rate.setText(getIntent().getStringExtra("s_textview_review_car_rate"));
        review.setText(getIntent().getStringExtra("s_textview_car_review"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglereviewActivity.this,CarreviewsActivity.class);
                startActivity(intent);
            }
        });
    }
}