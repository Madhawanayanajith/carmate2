package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class SingleusercarActivity extends AppCompatActivity {


    TextView name,edition,brand,type,manufacturedyear,registeredyear,range,details,owner,contactno,city,price;
    ImageView car1,car2,car3,car4;
    VideoView videoView;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleusercar);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        car1 = findViewById(R.id.imageView4);
        car2 = findViewById(R.id.imageView5);
        car3 = findViewById(R.id.imageView6);
        car4 = findViewById(R.id.imageView7);
        videoView = findViewById(R.id.videoView5);
        name = findViewById(R.id.s_user_car_name);
        edition = findViewById(R.id.s_user_car_edition);
        brand = findViewById(R.id.s_user_car_brand);
        type = findViewById(R.id.s_user_car_type);
        manufacturedyear = findViewById(R.id.s_user_car_manufactured_year);
        registeredyear = findViewById(R.id.s_user_car_registered_year);
        range = findViewById(R.id.s_user_car_range);
        details = findViewById(R.id.s_user_car_details);
        owner = findViewById(R.id.s_user_name);
        contactno = findViewById(R.id.s_user_contact_number);
        city = findViewById(R.id.s_user_car_city);
        price = findViewById(R.id.s_user_price);
        b  = findViewById(R.id.button);

        Picasso.get().load(getIntent().getStringExtra("imageView4"))
                .placeholder(R.drawable.usercar)
                .into(car1);
        Picasso.get().load(getIntent().getStringExtra("imageView5"))
                .placeholder(R.drawable.usercar)
                .into(car2);
        Picasso.get().load(getIntent().getStringExtra("imageView6"))
                .placeholder(R.drawable.usercar)
                .into(car3);
        Picasso.get().load(getIntent().getStringExtra("imageView7"))
                .placeholder(R.drawable.usercar)
                .into(car4);

        // Load video URL into VideoView
//        String videoUrl = getIntent().getStringExtra("videoView5");
//        Uri videoUri = Uri.parse(videoUrl);
//        videoView.setVideoURI(videoUri);
//
//        // Start playing the video
//        videoView.start();

        name.setText(getIntent().getStringExtra("s_user_car_name"));
        edition.setText(getIntent().getStringExtra("s_user_car_edition"));
        brand.setText(getIntent().getStringExtra("s_user_car_brand"));
        type.setText(getIntent().getStringExtra("s_user_car_type"));
        manufacturedyear.setText(getIntent().getStringExtra("s_user_car_manufactured_year"));
        registeredyear.setText(getIntent().getStringExtra("s_user_car_registered_year"));
        range.setText(getIntent().getStringExtra("s_user_car_range"));
        details.setText(getIntent().getStringExtra("s_user_car_details"));
        owner.setText(getIntent().getStringExtra("s_user_name"));
        contactno.setText(getIntent().getStringExtra("s_user_contact_number"));
        city.setText(getIntent().getStringExtra("s_user_car_city"));
        price.setText(getIntent().getStringExtra("s_user_price"));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                         Intent intent = new Intent(SingleusercarActivity.this,HomeFragment.class);
                           startActivity(intent);

            }
        });


    }


}