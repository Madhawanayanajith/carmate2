package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminloginActivity extends AppCompatActivity {
    EditText email,password;
     Button button;
    //FirebaseDatabase database;
    //DatabaseReference reference;
    //FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        button = findViewById(R.id.the_button);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        //fAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  //database = FirebaseDatabase.getInstance();
                  //reference = database.getReference("admin");

                  //String adminemail = email.getText().toString();
                  //String adminpassword = password.getText().toString();

                  //admin theadmin = new admin(adminemail,adminpassword);
                  //reference.child(adminemail).setValue(theadmin);

                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(AdminloginActivity.this, "Email,password can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.getText().toString().equals("admin@gmail.com") && password.getText().toString().equals("123456")){
                    Intent intent = new Intent(AdminloginActivity.this, AdminmenuActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(AdminloginActivity.this,"Error !"  ,Toast.LENGTH_SHORT).show();

                }

                  }
        });

    }
}