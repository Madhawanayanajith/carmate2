package com;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carmate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompareActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    SearchView searchView;

    ArrayList<car2> recyclelist;
    adapter2 recycleadapter;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerview = findViewById(R.id.recycleview100);
        searchView = findViewById(R.id.searchcars);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclelist = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        recycleadapter = new adapter2(recyclelist, CompareActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CompareActivity.this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new DividerItemDecoration(recyclerview.getContext(), DividerItemDecoration.VERTICAL));
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(recycleadapter);
        firebaseDatabase.getReference().child("car2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    car2 model = dataSnapshot.getValue(car2.class);
                    recyclelist.add(model);
                }
                recycleadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Retrieve the data of the single car from the intent
        Bundle extras = getIntent().getExtras();

        // Populate UI with car details
        if (extras != null) {
            TextView carname = findViewById(R.id.c_textview_car_name);
            TextView edition = findViewById(R.id.c_textview_editions);
            TextView type = findViewById(R.id.c_textview_type);
            TextView manufacturedyear = findViewById(R.id.c_textview_manufactured_year);
            TextView brand = findViewById(R.id.c_textview_brand);
            TextView price = findViewById(R.id.c_textview_price);
            TextView fuelconsumption = findViewById(R.id.c_textview_fuel_consumption);
            TextView seats = findViewById(R.id.c_textview_seats);
            TextView specifications = findViewById(R.id.c_textview_specifications);
            TextView pros = findViewById(R.id.c_textview_pros);
            TextView cons = findViewById(R.id.c_textview_cons);
            TextView servicecost = findViewById(R.id.c_textview_service_cost);
            TextView certficates = findViewById(R.id.c_textview_certificates);
            ImageView image = findViewById(R.id.car);

            carname.setText(extras.getString("s_textview_car_name"));
            edition.setText(extras.getString("s_textview_editions"));
            type.setText(extras.getString("s_textview_type"));
            manufacturedyear.setText(extras.getString("s_textview_manufactured_year"));
            brand.setText(extras.getString("s_textview_brand"));
            price.setText(extras.getString("s_textview_price"));
            fuelconsumption.setText(extras.getString("s_textview_fuel_consumption"));
            seats.setText(extras.getString("s_textview_seats"));
            specifications.setText(extras.getString("s_textview_specifications"));
            pros.setText(extras.getString("s_textview_pros"));
            cons.setText(extras.getString("s_textview_cons"));
            servicecost.setText(extras.getString("s_textview_service_cost"));
            certficates.setText(extras.getString("s_textview_certificates"));
            Picasso.get().load(getIntent().getStringExtra("imageViewcar1"))
                    .placeholder(R.drawable.a)
                    .into(image);
        }

        // Retrieve the data of the single car from the intent
        Bundle extras1 = getIntent().getExtras();

        // Populate UI with car details
        if (extras1 != null) {
            TextView carname2 = findViewById(R.id.c2_textview_car_name);
            TextView edition2 = findViewById(R.id.c2_textview_editions);
            TextView type2 = findViewById(R.id.c2_textview_type);
            TextView manufacturedyear2 = findViewById(R.id.c2_textview_manufactured_year);
            TextView brand2 = findViewById(R.id.c2_textview_brand);
            TextView price2 = findViewById(R.id.c2_textview_price);
            TextView fuelconsumption2 = findViewById(R.id.c2_textview_fuel_consumption);
            TextView seats2 = findViewById(R.id.c2_textview_seats);
            TextView specifications2 = findViewById(R.id.c2_textview_specifications);
            TextView pros2 = findViewById(R.id.c2_textview_pros);
            TextView cons2 = findViewById(R.id.c2_textview_cons);
            TextView servicecost2 = findViewById(R.id.c2_textview_service_cost);
            TextView certficates2 = findViewById(R.id.c2_textview_certificates);
            ImageView image1 = findViewById(R.id.car1);

            carname2.setText(extras1.getString("s_textview_car_name2"));
            edition2.setText(extras1.getString("s_textview_editions2"));
            type2.setText(extras1.getString("s_textview_type2"));
            manufacturedyear2.setText(extras1.getString("s_textview_manufactured_year2"));
            brand2.setText(extras1.getString("s_textview_brand2"));
            price2.setText(extras1.getString("s_textview_price2"));
            fuelconsumption2.setText(extras1.getString("s_textview_fuel_consumption2"));
            seats2.setText(extras1.getString("s_textview_seats2"));
            specifications2.setText(extras1.getString("s_textview_specifications2"));
            pros2.setText(extras1.getString("s_textview_pros2"));
            cons2.setText(extras1.getString("s_textview_cons2"));
            servicecost2.setText(extras1.getString("s_textview_service_cost2"));
            certficates2.setText(extras1.getString("s_textview_certificates2"));
            Picasso.get().load(getIntent().getStringExtra("imageViewcar2"))
                    .placeholder(R.drawable.a)
                    .into(image1);


        }

    }

    private void filterList(String text) {
        ArrayList<car2> filteredList = new ArrayList<>();
        for (car2 Car : recyclelist) {
            if (Car.getThecarname1().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(Car);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(CompareActivity.this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            recycleadapter.setFilteredList(filteredList);
        }

    }

}

