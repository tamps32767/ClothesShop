package com.example.clothesshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.OnCartUpdateListener;
import com.example.clothesshop.R;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<Product> cartProducts;
    private OnCartUpdateListener listener;

    public CartAdapter(Context context, List<Product> cartProducts, OnCartUpdateListener listener) {
        this.context = context;
        this.cartProducts = cartProducts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartProducts.get(position);
        holder.txtNameProducts.setText(product.getName());
        holder.txtPriceProducts.setText("Rs. " + String.format("%.2f", product.getPrice()));
        // Set image resource if you have it, e.g. holder.imgProducts.setImageResource(product.getImageResId());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.getInstance().removeProduct(product);
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onCartUpdated();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameProducts;
        TextView txtPriceProducts;
        ImageView imgProducts;
        ImageButton imgDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameProducts = itemView.findViewById(R.id.txtNameProducts);
            txtPriceProducts = itemView.findViewById(R.id.txtPriceProducts);
            imgProducts = itemView.findViewById(R.id.imgProducts);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
