package com.example.carmate;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class ReviewcarActivity extends AppCompatActivity {

    EditText name,carname,rate,review;

    ImageButton imageButton;

    Button addreview;
    Uri imageuri;

    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reviewcar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        name = findViewById(R.id.editText_review_name);
        carname = findViewById(R.id.editText_review_car_name);
        rate = findViewById(R.id.editText_review_rate);
        review = findViewById(R.id.editText_review_car);
        imageButton = findViewById(R.id.imagebutton_car);
        addreview = findViewById(R.id.add_review_button);

        rate.setFilters(new InputFilter[]{ new InputFilterMax(10)});//rate car edit text can get the maximum number is 10

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 UploadImage();
            }
        });

        addreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the  text is empty
                if(name.getText().toString().isEmpty() || carname.getText().toString().isEmpty() ||  rate.getText().toString().isEmpty() || review.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewcarActivity.this, "Please add all details write to the review", Toast.LENGTH_SHORT).show();
                    return; // Exit the method
                }
                 final StorageReference reference = FirebaseStorage.getInstance().getReference().child("reviews")
                         .child(System.currentTimeMillis()+"");
                reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                review model = new review();
                                model.setImageButton(uri.toString());
                                model.setName(name.getText().toString());
                                model.setCarname(carname.getText().toString());
                                model.setRate(rate.getText().toString());
                                model.setReview(review.getText().toString());

                                database.getReference().child("reviews").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(ReviewcarActivity.this,"Review added successfully",Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(ReviewcarActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
            }
        });

    }
        });
    }

    private void UploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_MEDIA_IMAGES)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        // Permission is granted, proceed with image selection
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        // Permission is denied, display appropriate message
                        Toast.makeText(ReviewcarActivity.this, "Access Denied: Please grant permission to access external storage", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        // Display rationale for the permission request, or retry the request
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
            imageButton.setImageURI(imageuri);
        }
    }
}