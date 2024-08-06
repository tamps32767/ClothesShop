package com.example.clothesshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.OnCartUpdateListener;
import com.example.clothesshop.R;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Product> products;
    private Context context;
    private OnCartUpdateListener cartUpdateListener;

    public CartAdapter(Context context, List<Product> products, OnCartUpdateListener cartUpdateListener) {
        this.context = context;
        this.products = products;
        this.cartUpdateListener = cartUpdateListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        // holder.productImage.setImageResource(product.getImage()); // Set the product image here

        holder.removeProduct.setOnClickListener(v -> {
            Cart.getInstance().removeProduct(product);
            products.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, products.size());
            if (cartUpdateListener != null) {
                cartUpdateListener.onCartUpdated();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage, removeProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
            removeProduct = itemView.findViewById(R.id.imgDelete);
        }
    }
}
