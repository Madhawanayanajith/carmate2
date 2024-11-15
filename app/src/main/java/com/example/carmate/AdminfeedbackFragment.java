package com.example.carmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminfeedbackFragment extends Fragment {
      RecyclerView recyclerView;
      ArrayList<feedback> recycleList;
      FirebaseDatabase firebaseDatabase;

     public AdminfeedbackFragment(){
    // Required empty public constructor
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adminfeedback, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

          recyclerView = view.findViewById(R.id.recycleview40);
          recycleList = new ArrayList<>();
          firebaseDatabase = FirebaseDatabase.getInstance();

          adapterfeedback recyclerAdapter = new adapterfeedback(recycleList,requireActivity().getApplicationContext());
          LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
          recyclerView.setLayoutManager(linearLayoutManager);
          recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
          recyclerView.setNestedScrollingEnabled(false);
          recyclerView.setAdapter(recyclerAdapter);

          firebaseDatabase.getReference().child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        feedback model = dataSnapshot.getValue(feedback.class);
                        recycleList.add(model);

                    }
                    recyclerAdapter.notifyDataSetChanged();
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
    }
}