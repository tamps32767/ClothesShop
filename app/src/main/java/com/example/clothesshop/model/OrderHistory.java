package com.example.clothesshop.model;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;
    private static OrderHistory instance;

    private OrderHistory() {
        orders = new ArrayList<>();
    }

    public static OrderHistory getInstance() {
        if (instance == null) {
            instance = new OrderHistory();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
