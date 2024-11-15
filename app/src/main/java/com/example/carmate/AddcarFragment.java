package com.example.carmate;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Addcar2Activity;
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

public class AddcarFragment extends Fragment {

    EditText carname, editions, type, manufacturedyear, brand, price, fuelconsumption, seats, specifications, pros, cons, servicecost, certificates;
    ImageView carimage;
    Button addcarimagebtn, addbtn,addbtn2;
    Uri imageuri;


    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

    public AddcarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addcar, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = FirebaseDatabase.getInstance();
        firebaseStorage =  FirebaseStorage.getInstance();

        carname = view.findViewById(R.id.editText_car_name);
        editions = view.findViewById(R.id.editText_editions);
        type = view.findViewById(R.id.editText_type);
        manufacturedyear = view.findViewById(R.id.editText_manufactured_year);
        brand = view.findViewById(R.id.editText_brand);
        price = view.findViewById(R.id.editText_price);
        fuelconsumption = view.findViewById(R.id.editText_fuel_consumption);
        seats = view.findViewById(R.id.editText_seats);
        specifications = view.findViewById(R.id.editText_specifications);
        pros = view.findViewById(R.id.editText_pros);
        cons = view.findViewById(R.id.editText_cons);
        servicecost = view.findViewById(R.id.editText_service_cost);
        certificates = view.findViewById(R.id.editText_certificates);
        carimage = view.findViewById(R.id.imageViewcar);
        addcarimagebtn = view.findViewById(R.id.addbutton);
        addbtn = view.findViewById(R.id.add);
        addbtn2 = view.findViewById(R.id.comparecar);


        addcarimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  final StorageReference reference = firebaseStorage.getReference().child("car")
                          .child(System.currentTimeMillis()+"");

                  reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                      @Override
                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    car model = new car();
                                    model.setThecarimage(uri.toString());
                                    model.setThecarname(carname.getText().toString());
                                    model.setTheeditions(editions.getText().toString());
                                    model.setThetype(type.getText().toString());
                                    model.setThemanufacturedyear(manufacturedyear.getText().toString());
                                    model.setThebrand(brand.getText().toString());
                                    model.setTheprice(price.getText().toString());
                                    model.setThefuelconsumption(fuelconsumption.getText().toString());
                                    model.setTheseats(seats.getText().toString());
                                    model.setThespecifications(specifications.getText().toString());
                                    model.setThepros(pros.getText().toString());
                                    model.setThecons(cons.getText().toString());
                                    model.setTheservicecost(servicecost.getText().toString());
                                    model.setThecertificates(certificates.getText().toString());

                                    database.getReference().child("car").push().setValue(model)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                      Toast.makeText(requireContext(),"Car added successfully",Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                      Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                      }
                  });
            }
        });

        addbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(requireContext(), Addcar2Activity.class);
                  startActivity(intent);
            }
        });
    }



    private void uploadImage() {
        Dexter.withContext(requireActivity())
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
                        Toast.makeText(requireContext(), "Access Denied: Please grant permission to access external storage", Toast.LENGTH_SHORT).show();
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
            carimage.setImageURI(imageuri);
        }
    }
}










