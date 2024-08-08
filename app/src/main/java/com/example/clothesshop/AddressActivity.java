package com.example.clothesshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Order;
import com.example.clothesshop.model.OrderHistory;
import com.google.android.material.textfield.TextInputEditText;


public class AddressActivity extends AppCompatActivity {

    private TextInputEditText tfTitle, tfNewAdress;
    private Button btnSave;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        tfTitle = findViewById(R.id.tfTitle);
        tfNewAdress = findViewById(R.id.tfNewAdress);
        btnSave = findViewById(R.id.btnSave);

        // Nhận order từ Intent
        Intent intent = getIntent();
        order = (Order) intent.getParcelableExtra("order");
        if (order == null) {
            finish();
            return;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shippingInfo = tfTitle.getText().toString();
                String shippingInfo1 = tfNewAdress.getText().toString();

                if (shippingInfo == null) shippingInfo = "";
                if (shippingInfo1 == null) shippingInfo1 = "";

                order.setShippingInfo(shippingInfo + " /n" + shippingInfo1);

                OrderHistory.getInstance().addOrder(order);

                Cart.getInstance().clearCart();

                Intent intent = new Intent(AddressActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}