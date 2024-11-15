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

public class MenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.navHome){
                    loadFragment(new HomeFragment(),false);

                }else if(itemId == R.id.navSearch){
                      loadFragment(new SearchFragment(),false);

                }else if(itemId == R.id.navCalculator) {
                    loadFragment(new CalculatorFragment(), false);

                }else if(itemId == R.id.navServicecenters){
                    loadFragment(new ServicecentersFragment(),false);

                }else {
                    loadFragment(new PersonFragment(),false);
                }
//                loadFragment(new HomeFragment(),true);
                return true;

            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navHome);
    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isAppInitialized){
            fragmentTransaction.add(R.id.frameLayout,fragment);
        }else{
            fragmentTransaction.replace(R.id.frameLayout,fragment);
        }
        fragmentTransaction.commit();

    }


}