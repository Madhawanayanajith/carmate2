package com.example.carmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

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


public class SearchFragment extends Fragment {
    RecyclerView recyclerview;
    SearchView searchView;
    ArrayList<car> recyclelist;
    adapter recycleadapter;


    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;
    public  SearchFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerview = view.findViewById(R.id.recycleview10);
        searchView   = view.findViewById(R.id.search);
        searchView.clearFocus();
        // Initialize ProgressBar
        progressBar = view.findViewById(R.id.progressBar2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    filterList(newText);
                return  true;
            }
        });


        recyclelist = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        recycleadapter = new adapter(recyclelist, requireActivity().getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new DividerItemDecoration(recyclerview.getContext(),DividerItemDecoration.VERTICAL));
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(recycleadapter);

        // Show ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        firebaseDatabase.getReference().child("car").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                       for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                           car model = dataSnapshot.getValue(car.class);
                           recyclelist.add(model);
                       }
                       recycleadapter.notifyDataSetChanged();
                       progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                       progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void filterList(String text) {
        ArrayList<car> filteredList = new ArrayList<>();
        for(car Car : recyclelist){
               if(Car.getThecarname().toLowerCase().contains(text.toLowerCase())){
                   filteredList.add(Car);
               }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
        }else{
            recycleadapter.setFilteredList(filteredList);
        }
    }
}