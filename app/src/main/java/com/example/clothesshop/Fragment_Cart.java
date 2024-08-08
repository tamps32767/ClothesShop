package com.example.clothesshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.clothesshop.adapter.CartAdapter;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Order;
import com.example.clothesshop.model.Product;

import java.util.List;

public class Fragment_Cart extends Fragment implements OnCartUpdateListener {
    private Context context;
    private CartAdapter cartAdapter;
    private TextView txtPrice;
    private RecyclerView rcvCart;
    private Button btnCheckout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__cart, container, false);

        rcvCart = view.findViewById(R.id.rcvCart);
        txtPrice = view.findViewById(R.id.txtPrice);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        List<Product> cartProducts = Cart.getInstance().getProducts();
        cartAdapter = new CartAdapter(getContext(), cartProducts, this);

        rcvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvCart.setAdapter(cartAdapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Product> selectedProducts = Cart.getInstance().getProducts();
                Order order = new Order(selectedProducts, null);

                Intent intent = new Intent(getActivity(), AddressActivity.class);
                intent.putExtra("order", order);
                startActivity(intent);
            }
        });

        return view;
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        List<Product> cartProducts = Cart.getInstance().getProducts();
        for (Product product : cartProducts) {
            totalPrice += product.getPrice();
        }
        txtPrice.setText("Total: $" + String.format("%.2f", totalPrice));
    }

    @Override
    public void onCartUpdated() {
        updateTotalPrice();
    }
}
