package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {

    EditText signupName,signupEmail,signupPassword,signupPhoneno;
    TextView signupRedirect;
    FirebaseDatabase database;
    FirebaseAuth fAuth; //Resgister user
    Button signinbutton;
    private ImageButton button1;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupPhoneno = findViewById(R.id.signup_phonenumber);
        signupRedirect =findViewById(R.id.redirect_loginin);
        signinbutton = findViewById(R.id.btn_signin);
        button1 = findViewById(R.id.imagebtn);

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){ //user already log go to menu activity
            startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            finish();
        }

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();
                String phoneno = signupPhoneno.getText().toString();


                users model = new users(name,email,password,phoneno); //add data to database
                reference.child(name).setValue(model);


                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneno.isEmpty()){ //The fields can not be empty give user to error
                    Toast.makeText(SigninActivity.this,"Name,email,password,phone number can not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() { //register user to database
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SigninActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                        }else{
                            Toast.makeText(SigninActivity.this,"Error !" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(SigninActivity.this,LoginActivity.class);
                  startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, AdminloginActivity.class);
                startActivity(intent);
            }
        });

    }
}