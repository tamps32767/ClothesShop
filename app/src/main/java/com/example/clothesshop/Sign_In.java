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

public class Sign_In extends AppCompatActivity {

    private TextInputEditText tfEmail;
    private TextInputEditText tfPassword;
    private Button btnSignIn;
    private TextView txtSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        // Chuyển kiểu từ TextInputLayout sang TextInputEditText

        TextInputLayout emailInputLayout = findViewById(R.id.tfEmail);
        TextInputLayout passwordInputLayout = findViewById(R.id.tfPassword);



        tfEmail = (TextInputEditText) emailInputLayout.getEditText();
        tfPassword = (TextInputEditText) passwordInputLayout.getEditText();


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
                getUser(email, password);
                Intent intent = new Intent(Sign_In.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getUser( String email, String password){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        User user = new User( email, password);

        Call<User> call = apiService.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                        User user = response.body();

                        // Xử lý phản hồi đăng ký thành công
                        Toast.makeText(Sign_In.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Sign_In.this, MainActivity.class);
                        startActivity(intent);

                } else {
                    // Xử lý lỗi phản hồi từ server
                    Toast.makeText(Sign_In.this, "Login Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Xử lý lỗi khi gọi API
                Toast.makeText(Sign_In.this, "Login Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
