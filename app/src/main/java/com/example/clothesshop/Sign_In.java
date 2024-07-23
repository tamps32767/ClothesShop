package com.example.clothesshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothesshop.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_In extends AppCompatActivity {

    private TextInputEditText tfEmail;
    private TextInputEditText tfPassword;
    private Button btnSignIn;
    private TextView txtSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        tfEmail = findViewById(R.id.tfEmail);
        tfPassword = findViewById(R.id.tfPassword);

        txtSignUp = findViewById(R.id.txtSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_In.this, Sign_Up.class);
                startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tfEmail.getText().toString();
                String password = tfPassword.getText().toString();


                Intent intent = new Intent(Sign_In.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
