package com.example.clothesshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.clothesshop.adapter.ProductAdapter;
import com.example.clothesshop.dao.ProductDao;
import com.example.clothesshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Home extends Fragment {

    private RecyclerView rcvProducts;
    private ProductAdapter productAdapter;
    private List<Product> mListProduct;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__home, container, false);

        rcvProducts = view.findViewById(R.id.rcvProducts);
        rcvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        mListProduct = new ArrayList<>();

        // Initialize adapter with an empty list
        productAdapter = new ProductAdapter(new ArrayList<>());
        rcvProducts.setAdapter(productAdapter);

        // Load product data from API
        loadProductData();

        return view;
    }

    private void loadProductData() {
        mListProduct.add(new Product("Product 1","", "100$", "30","https://example.com/image1.jpg"));
        mListProduct.add(new Product("Product 2","", "200$", "10","https://example.com/image2.jpg"));
        mListProduct.add(new Product("Product 3","", "300$", "20","https://example.com/image3.jpg"));
        mListProduct.add(new Product("Product 4", "","300$","5", "https://example.com/image4.jpg"));
    }
}