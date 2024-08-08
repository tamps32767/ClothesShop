package com.example.clothesshop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {private static Cart instance;
    private List<Product> products;

    private Cart() {
        products = new ArrayList<>();
    }

    public static synchronized Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }
    public void clearCart() {
        products.clear();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
