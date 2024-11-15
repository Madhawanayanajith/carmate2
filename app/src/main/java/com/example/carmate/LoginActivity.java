package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText loginEmail,loginPassword;
    Button loginbutton;
    private Button button; //create a variable to access the button in activity_main
     FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         loginEmail = findViewById(R.id.user_email_text);
         loginPassword = findViewById(R.id.user_password_text);
         button = findViewById(R.id.create_account_btn); //set connection to button
         loginbutton = findViewById(R.id.login_btn);
         fAuth = FirebaseAuth.getInstance();

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Email , password can not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() { //authinticate user
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(LoginActivity.this,"Login in success",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                           }else{
                               Toast.makeText(LoginActivity.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                           }
                    }
                });

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

    }
    }

