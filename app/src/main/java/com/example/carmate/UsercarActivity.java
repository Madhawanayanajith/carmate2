package com.example.carmate;

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
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
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

import java.util.ArrayList;
import java.util.List;

public class UsercarActivity extends AppCompatActivity {

    EditText ucarname, uedition, ubrand, utype, umanufacturedyear, uregisteredyear, urange, ucardetails, username, ucontactnumber, ucity, uprice;
    ImageView imageView1, imageView2, imageView3, imageView4;
    VideoView avideoView;

    Button addcar;
    List<Uri> imageUris = new ArrayList<>();
    Uri videoUri;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usercar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        ucarname = findViewById(R.id.editText_user_car_name);
        uedition = findViewById(R.id.editText_user_edition);
        ubrand = findViewById(R.id.editText_user_brand);
        utype = findViewById(R.id.editText_user_type);
        umanufacturedyear = findViewById(R.id.editText_user_manufactured_year);
        uregisteredyear = findViewById(R.id.editText_user_registered_year);
        urange = findViewById(R.id.editText_user_car_range);
        ucardetails = findViewById(R.id.editText_user_car_details);
        username = findViewById(R.id.editText_user_name);
        ucontactnumber = findViewById(R.id.editText_user_contact_number);
        ucity = findViewById(R.id.editText_user_car_city);
        uprice = findViewById(R.id.editText_user_price);
        addcar = findViewById(R.id.user_addcar);

        imageView1 = findViewById(R.id.usercarimage);
        imageView2 = findViewById(R.id.usercarimage2);
        imageView3 = findViewById(R.id.usercarimage3);
        imageView4 = findViewById(R.id.usercarimage4);
        avideoView = findViewById(R.id.videoView);


        View.OnClickListener imageClickListener = v -> uploadImage();
        imageView1.setOnClickListener(imageClickListener);
        imageView2.setOnClickListener(imageClickListener);
        imageView3.setOnClickListener(imageClickListener);
        imageView4.setOnClickListener(imageClickListener);

        avideoView.setOnClickListener(v -> uploadVideo());


        addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }

        });
    }

    private void uploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_MEDIA_IMAGES)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        startActivityForResult(intent, 101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(UsercarActivity.this, "Access Denied: Please grant permission to access external storage", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest request, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void uploadVideo() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_MEDIA_VIDEO)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("video/*");
                        startActivityForResult(intent, 102);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(UsercarActivity.this, "Access Denied: Please grant permission to access external storage", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest request, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
            } else if (data.getData() != null) {
                imageUris.add(data.getData());
            }

            if (!imageUris.isEmpty()) {
                if (imageUris.size() > 0) imageView1.setImageURI(imageUris.get(0));
                if (imageUris.size() > 1) imageView2.setImageURI(imageUris.get(1));
                if (imageUris.size() > 2) imageView3.setImageURI(imageUris.get(2));
                if (imageUris.size() > 3) imageView4.setImageURI(imageUris.get(3));
            }
        } else if (requestCode == 102 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            videoUri = data.getData();
            avideoView.setVideoURI(videoUri);
            avideoView.start();
        }
    }

    private void uploadData() {
        if (imageUris.isEmpty() && videoUri == null) {
            Toast.makeText(UsercarActivity.this, "No images or video to upload", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the user ID of the authenticated user
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final List<String> imageUrls = new ArrayList<>();
        final int totalFiles = imageUris.size() + (videoUri != null ? 1 : 0);
        final int[] uploadCount = {0};

        // Create storage references for images and videos under the user's ID
        StorageReference imagesRef = FirebaseStorage.getInstance().getReference().child("images").child(userId);
        StorageReference videosRef = FirebaseStorage.getInstance().getReference().child("videos").child(userId);

        // Upload each image and add URLs to imageUrls
        for (Uri imageUri : imageUris) {
            String imageName = "image_" + System.currentTimeMillis() + ".jpg";
            StorageReference imageRef = imagesRef.child(imageName);
            UploadTask uploadTask = imageRef.putFile(imageUri);
            uploadImage(uploadTask, imageRef, imageUrls, totalFiles, uploadCount);
        }

        // Upload video if it exists
        if (videoUri != null) {
            String videoName = "video_" + System.currentTimeMillis() + ".mp4";
            StorageReference videoRef = videosRef.child(videoName);
            UploadTask uploadTask = videoRef.putFile(videoUri);
            uploadVideo(uploadTask, videoRef, imageUrls, totalFiles, uploadCount);
        }
    }

    private void uploadImage(UploadTask uploadTask, StorageReference imageRef, List<String> imageUrls, int totalFiles, int[] uploadCount) {
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                imageUrls.add(uri.toString());
                uploadCount[0]++;
                if (uploadCount[0] == totalFiles) {
                    // All files uploaded, proceed to upload car details
                    uploadCarDetails(imageUrls, null);
                }
            });
        }).addOnFailureListener(e -> {
            // Handle image upload failure
            Toast.makeText(UsercarActivity.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void uploadVideo(UploadTask uploadTask, StorageReference videoRef, List<String> imageUrls, int totalFiles, int[] uploadCount) {
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            videoRef.getDownloadUrl().addOnSuccessListener(uri -> {
                imageUrls.add(uri.toString());
                uploadCount[0]++;
                if (uploadCount[0] == totalFiles) {
                    // All files uploaded, proceed to upload car details
                    uploadCarDetails(imageUrls, null);
                }
            });
        }).addOnFailureListener(e -> {
            // Handle video upload failure
            Toast.makeText(UsercarActivity.this, "Failed to upload video: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void uploadCarDetails(List<String> imageUrls, String videoUrl) {
        String imageUrl0 = imageUrls.size() > 0 ? imageUrls.get(0) : null;
        String imageUrl1 = imageUrls.size() > 1 ? imageUrls.get(1) : null;
        String imageUrl2 = imageUrls.size() > 2 ? imageUrls.get(2) : null;
        String imageUrl3 = imageUrls.size() > 3 ? imageUrls.get(3) : null;

        usercar model = new usercar(

                ucarname.getText().toString(),
                uedition.getText().toString(),
                ubrand.getText().toString(),
                utype.getText().toString(),
                umanufacturedyear.getText().toString(),
                uregisteredyear.getText().toString(),
                urange.getText().toString(),
                ucardetails.getText().toString(),
                username.getText().toString(),
                ucontactnumber.getText().toString(),
                ucity.getText().toString(),
                uprice.getText().toString(),
                imageUrl0,
                imageUrl1,
                imageUrl2,
                imageUrl3,
                videoUrl

        );

        DatabaseReference databaseRef = firebaseDatabase.getReference("user car").push();
        databaseRef.setValue(model).addOnSuccessListener(aVoid ->
                Toast.makeText(this, "Car details uploaded successfully!", Toast.LENGTH_SHORT).show()
        ).addOnFailureListener(e ->
                Toast.makeText(this, "Failed to upload car details: " + e.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }
}
