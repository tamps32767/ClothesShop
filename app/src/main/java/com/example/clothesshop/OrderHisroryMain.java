package com.example.clothesshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.adapter.OrderHistoryAdapter;
import com.example.clothesshop.model.OrderHistory;

public class OrderHisroryMain extends AppCompatActivity {
    private RecyclerView rcvHistory;
    private OrderHistoryAdapter orderHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        rcvHistory = findViewById(R.id.rcvHistory);

        orderHistoryAdapter = new OrderHistoryAdapter(OrderHistory.getInstance().getOrders());
        rcvHistory.setLayoutManager(new LinearLayoutManager(this));
        rcvHistory.setAdapter(orderHistoryAdapter);
    }
}
