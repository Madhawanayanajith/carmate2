package com;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carmate.R;
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

public class Addcar2Activity extends AppCompatActivity {
    EditText carname1, editions1, type1, manufacturedyear1, brand1, price1, fuelconsumption1, seats1, specifications1, pros1, cons1, servicecost1, certificates1;
    ImageView carimage1;
    Button addcarimagebtn1, addbtn1;
    Uri imageuri1;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addcar2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = FirebaseDatabase.getInstance();
        firebaseStorage =  FirebaseStorage.getInstance();

        carname1 = findViewById(R.id.editText_car_name1);
        editions1 = findViewById(R.id.editText_editions1);
        type1 = findViewById(R.id.editText_type1);
        manufacturedyear1 = findViewById(R.id.editText_manufactured_year1);
        brand1 = findViewById(R.id.editText_brand1);
        price1 = findViewById(R.id.editText_price1);
        fuelconsumption1 = findViewById(R.id.editText_fuel_consumption1);
        seats1 = findViewById(R.id.editText_seats1);
        specifications1 = findViewById(R.id.editText_specifications1);
        pros1 = findViewById(R.id.editText_pros1);
        cons1 = findViewById(R.id.editText_cons1);
        servicecost1 = findViewById(R.id.editText_service_cost1);
        certificates1 = findViewById(R.id.editText_certificates1);
        carimage1 = findViewById(R.id.imageViewcar1);
        addcarimagebtn1 = findViewById(R.id.addbutton1);
        addbtn1 =findViewById(R.id.add2);

        addcarimagebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StorageReference reference = firebaseStorage.getReference().child("car2")
                        .child(System.currentTimeMillis()+"");

                reference.putFile(imageuri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                car2 model = new car2();
                                model.setThecarimage1(uri.toString());
                                model.setThecarname1(carname1.getText().toString());
                                model.setTheeditions1(editions1.getText().toString());
                                model.setThetype1(type1.getText().toString());
                                model.setThemanufacturedyear1(manufacturedyear1.getText().toString());
                                model.setThebrand1(brand1.getText().toString());
                                model.setTheprice1(price1.getText().toString());
                                model.setThefuelconsumption1(fuelconsumption1.getText().toString());
                                model.setTheseats1(seats1.getText().toString());
                                model.setThespecifications1(specifications1.getText().toString());
                                model.setThepros1(pros1.getText().toString());
                                model.setThecons1(cons1.getText().toString());
                                model.setTheservicecost1(servicecost1.getText().toString());
                                model.setThecertificates1(certificates1.getText().toString());



                                database.getReference().child("car2").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(Addcar2Activity.this,"Car 2 added successfully",Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Addcar2Activity.this,"Error",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                });
            }
        });


    }

    private void uploadImage() {
        Dexter.withContext(Addcar2Activity.this)
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
                        Toast.makeText(Addcar2Activity.this, "Access Denied: Please grant permission to access external storage", Toast.LENGTH_SHORT).show();
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
            imageuri1 = data.getData();
            carimage1.setImageURI(imageuri1);
        }
    }

}





