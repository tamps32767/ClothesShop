package com.example.clothesshop;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.clothesshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        relaceFragment(new Fragment_Home());

        binding.btnMenu.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home:
                    relaceFragment(new Fragment_Home());
                    break;

                case R.id.favourite:
                    relaceFragment(new Fragment_Favourite());
                    break;

                case R.id.cart:
                    relaceFragment(new Fragment_Cart());
                    break;

                case R.id.account:
                    relaceFragment(new Fragment_Account());
                    break;
            }
            return true;

        });
    }
    private void relaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
    }
}