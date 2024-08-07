package com.example.clothesshop;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.clothesshop.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                if (item.getItemId() == R.id.home) {
                    fragment = new Fragment_Home();
                } else if (item.getItemId() == R.id.homeAdimin) {
                    fragment = new Fragment_HomeAdmin();
                }else if (item.getItemId() == R.id.favourite){
                    fragment = new Fragment_Favourite();
                } else if (item.getItemId() == R.id.cart) {
                    fragment = new Fragment_Cart();
                }else {
                    fragment = new Fragment_Account();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                return true;
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        String loaitk = sharedPreferences.getString("loaitaikhoan", "");
        if (loaitk.equals("Admin")){
            Menu menu = btnMenu.getMenu();
            menu.findItem(R.id.home).setVisible(false);
            menu.findItem(R.id.favourite).setVisible(false);
            menu.findItem(R.id.cart).setVisible(false);
        }else {
            Menu menu = btnMenu.getMenu();
            menu.findItem(R.id.homeAdimin).setVisible(false);
        }

    }
}