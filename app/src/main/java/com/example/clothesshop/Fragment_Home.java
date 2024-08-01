package com.example.clothesshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

        View view = inflater.inflate(R.layout.fragment__home, container, false);

        rcvProducts = view.findViewById(R.id.rcvProducts);

        ProductDao productDao = new ProductDao(getContext());
        ArrayList<Product> list = productDao.getProduct();

        rcvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mListProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(list, getContext());
        rcvProducts.setAdapter(productAdapter);

        return view;
    }
}