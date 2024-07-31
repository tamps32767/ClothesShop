package com.example.clothesshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private List<Product> mListProduct;

    public ProductAdapter(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if (product == null){
            return;
        }
        holder.txtNameProducts.setText(product.getName());
        holder.txtPriceProducts.setText(product.getPrice());
        Picasso.get().load(product.getImageUrl()).into(holder.imgProducts);
    }

    @Override
    public int getItemCount() {
        if (mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView txtNameProducts, txtPriceProducts;
        private ImageView imgProducts;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameProducts = itemView.findViewById(R.id.txtNameProducts);
            txtPriceProducts = itemView.findViewById(R.id.txtPriceProducts);
            imgProducts = itemView.findViewById(R.id.imgProducts);
        }
    }
}
