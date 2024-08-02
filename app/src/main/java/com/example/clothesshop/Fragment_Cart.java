package com.example.clothesshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clothesshop.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Cart extends Fragment {

    private static final String ARG_PRODUCT = "product";

    public static Fragment_Cart newInstance(Product product) {
        Fragment_Cart fragment = new Fragment_Cart();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__cart, container, false);


        // Retrieve the product from arguments
        if (getArguments() != null) {
            Product product = getArguments().getParcelable(ARG_PRODUCT);
            if (product != null) {
                // Update UI or handle product data here
                // For example:
                TextView txtNameProducts = view.findViewById(R.id.txtNameProducts);
                TextView txtPriceProducts = view.findViewById(R.id.txtPriceProducts);

                txtNameProducts.setText(product.getName());
                txtPriceProducts.setText(product.getPrice());
            }
        }

        return view;
    }
}