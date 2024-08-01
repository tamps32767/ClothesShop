package com.example.clothesshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clothesshop.model.Product;


public class Fragment_Favourite extends Fragment {

    private static final String ARG_PRODUCT = "product";

    public static Fragment newInstance(Product product) {
        Fragment_Favourite fragment = new Fragment_Favourite();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, (Parcelable) product);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__favourite, container, false);

//        TextView productName = view.findViewById(R.id.productName);
//        TextView productDescription = view.findViewById(R.id.productDescription);
        // Khởi tạo các view khác

        if (getArguments() != null) {
            Product product = getArguments().getParcelable(ARG_PRODUCT);
            if (product != null) {
//                productName.setText(product.getName());
//                productDescription.setText(product.getDescription());
                // Cài đặt các chi tiết sản phẩm khác
            }
        }

        return view;
    }
}