package com.example.clothesshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothesshop.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_Up extends AppCompatActivity {

    private TextInputEditText tfName;
    private TextInputEditText tfEmail;
    private TextInputEditText tfPassword;
    private TextInputEditText tfrePassword;
    private Button btnSignUp;
    private TextView txtSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        tfName = findViewById(R.id.tfName);
        tfEmail = findViewById(R.id.tfEmail);
        tfPassword = findViewById(R.id.tfPassword);
        tfrePassword = findViewById(R.id.tfrePassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        txtSignIn = findViewById(R.id.txtSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tfName.getText().toString();
                String email = tfEmail.getText().toString();
                String password = tfPassword.getText().toString();
                String rePassword = tfrePassword.getText().toString();

            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
