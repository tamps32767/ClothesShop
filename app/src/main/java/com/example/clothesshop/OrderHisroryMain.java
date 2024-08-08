package com.example.clothesshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.adapter.OrderHistoryAdapter;
import com.example.clothesshop.model.Order;
import com.example.clothesshop.model.OrderHistory;

import java.util.ArrayList;
import java.util.List;

public class OrderHisroryMain extends AppCompatActivity {
    private RecyclerView rcvHistory;
    private OrderHistoryAdapter orderHistoryAdapter;
    private List<Order> orderHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        rcvHistory = findViewById(R.id.rcvHistory);

        orderHistoryAdapter = new OrderHistoryAdapter(OrderHistory.getInstance().getOrders());
        rcvHistory.setLayoutManager(new LinearLayoutManager(this));
        rcvHistory.setAdapter(orderHistoryAdapter);

        ArrayList<Order> orders = getIntent().getParcelableArrayListExtra("orderHistory");
        if (orders != null) {
            orderHistoryList.clear();
            orderHistoryList.addAll(orders);
            orderHistoryAdapter.notifyDataSetChanged();
        }
    }
}
