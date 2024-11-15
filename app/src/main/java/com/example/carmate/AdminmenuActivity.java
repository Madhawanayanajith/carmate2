package com.example.carmate;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdminmenuActivity extends AppCompatActivity {
    private BottomNavigationView thebottomNavigationView;
    private FrameLayout theframeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmenu);

        thebottomNavigationView = findViewById(R.id.bottomNavView2);
        theframeLayout = findViewById(R.id.frameLayout2);

        thebottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.navCar){
                    loadFragment(new AddcarFragment(),false);

                }else if(itemId == R.id.navUser){
                    loadFragment(new AdminusersFragment(),false);

                }else {
                    loadFragment(new AdminfeedbackFragment(), false);
                }
//                loadFragment(new HomeFragment(),true);
                return true;

            }
        });
        thebottomNavigationView.setSelectedItemId(R.id.navCar);
    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isAppInitialized){
            fragmentTransaction.add(R.id.frameLayout2,fragment);
        }else{
            fragmentTransaction.replace(R.id.frameLayout2,fragment);
        }
        fragmentTransaction.commit();

    }
}
