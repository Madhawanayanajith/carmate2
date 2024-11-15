package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private Handler handler;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        button = findViewById(R.id.button);
//        handler = new Handler();
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intetnt = new Intent(SplashScreen.this, SigninActivity.class);
//                startActivity(intetnt);
//                finish();
//            }
//        },4000);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreen.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
