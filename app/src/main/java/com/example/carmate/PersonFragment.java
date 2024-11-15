package com.example.carmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PersonFragment extends Fragment {
    Button Signout,car,review,Thefeedback;
    TextView thegreet;
    EditText thefeedback;
    DatabaseReference reference;
    private FirebaseDatabase database;


    public PersonFragment(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = FirebaseDatabase.getInstance();
        Signout = view.findViewById(R.id.signoutbutton);
        car = view.findViewById(R.id.car_button);
        review = view.findViewById((R.id.review_button));
        thefeedback = view.findViewById(R.id.editTextthefeedback);
        Thefeedback = view.findViewById(R.id.addfeedback);
        thegreet = view.findViewById(R.id.textgreet);

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), UsercarActivity.class);
                startActivity(intent);

            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ReviewcarActivity.class);
                startActivity(intent);
            }
        });

        Thefeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userFeedback = thefeedback.getText().toString().trim(); // Get the feedback and remove leading/trailing whitespace
                if (!userFeedback.isEmpty()) { // Check if the feedback is not empty
                reference = database.getReference("Feedback");
                feedback model = new feedback();
                model.setFeedback(thefeedback.getText().toString());
                database.getReference().child("Feedback").push().setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(requireContext(), "Feedback added successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                } else {
                    Toast.makeText(requireContext(), "Please enter your feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void logout() {
        FirebaseAuth.getInstance().signOut(); //logout
        startActivity(new Intent(getActivity(),LoginActivity.class));
        getActivity().finish();
    }


}