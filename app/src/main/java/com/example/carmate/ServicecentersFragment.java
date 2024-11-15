package com.example.carmate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class ServicecentersFragment extends Fragment {
       Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10;
       AdView adView;

public ServicecentersFragment(){
      // Required empty public constructor
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_servicecenters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button1 = view.findViewById(R.id.button3);
        button2 = view.findViewById(R.id.button4);
        button3 = view.findViewById(R.id.button5);
        button4 = view.findViewById(R.id.button6);
        button5 = view.findViewById(R.id.button7);
        button6 = view.findViewById(R.id.button8);
        button7 = view.findViewById(R.id.button9);
        button8 = view.findViewById(R.id.button10);
        button9 = view.findViewById(R.id.button11);
        button10 = view.findViewById(R.id.button12);
        adView = view.findViewById(R.id.adView);

        MobileAds.initialize(requireContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Mint Auto Care";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Maxx Park Auto Care";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Magic Touch Automobile Repair and Service Center";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Stafford Motor Co. (Pvt) Ltd - Automobile Service Center";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Toyota Lanka Service Center / Pirelli Tyre Center";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Auto Miraj Grand1";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Premier Center";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Advanced Car Diagnostics";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Platinum Auto Spa";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = "Selinico Enterprises Accident Repairs";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(shopName));
                // Intent to open Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
    }

}