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

        // Chuyển kiểu từ TextInputLayout sang TextInputEditText
        TextInputLayout nameInputLayout = findViewById(R.id.tfName);
        TextInputLayout emailInputLayout = findViewById(R.id.tfEmail);
        TextInputLayout passwordInputLayout = findViewById(R.id.tfPassword);
        TextInputLayout confirmPasswordInputLayout = findViewById(R.id.tfrePassword);

        tfName = (TextInputEditText) nameInputLayout.getEditText();
        tfEmail = (TextInputEditText) emailInputLayout.getEditText();
        tfPassword = (TextInputEditText) passwordInputLayout.getEditText();
        tfrePassword = (TextInputEditText) confirmPasswordInputLayout.getEditText();

        btnSignUp = findViewById(R.id.btnSignUp);
        txtSignIn = findViewById(R.id.txtSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tfName.getText().toString();
                String email = tfEmail.getText().toString();
                String password = tfPassword.getText().toString();
                String rePassword = tfrePassword.getText().toString();

                if (password.equals(rePassword)) {
                    registerUser(name, email, password);
                } else {
                    Toast.makeText(Sign_Up.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registerUser(String username, String email, String password) {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        User user = new User(username, email, password);

        Call<User> call = apiService.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null && user.isSuccess()) {
                        // Xử lý phản hồi đăng ký thành công
                        Toast.makeText(Sign_Up.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Sign_Up.this, Sign_In.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Sign_Up.this, "Registration Failed: Invalid response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Xử lý lỗi phản hồi từ server
                    Toast.makeText(Sign_Up.this, "Registration Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Xử lý lỗi khi gọi API
                Toast.makeText(Sign_Up.this, "Registration Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
