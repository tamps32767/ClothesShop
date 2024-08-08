package com.example.clothesshop;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Fragment_Account extends Fragment {
    private TextView profile_name, profile_email, order_history;
    private FrameLayout log_out;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__account, container, false);

        profile_name = view.findViewById(R.id.profile_name);
        profile_email = view.findViewById(R.id.profile_email);
        log_out = view.findViewById(R.id.log_out);
        order_history = view.findViewById(R.id.order_history);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THONGTIN", MODE_PRIVATE);

        String name = sharedPreferences.getString("username", "");
        profile_name.setText(name);
        String email = sharedPreferences.getString("email", "");
        profile_email.setText(email);


        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Sign_In.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderHisroryMain.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
