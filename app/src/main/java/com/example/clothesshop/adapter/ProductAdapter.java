package com.example.clothesshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clothesshop.R;
import com.example.clothesshop.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private List<Product> mListProduct;
    private Context context;
    private OnItemClickListener listener;

    public ProductAdapter(List<Product> mListProduct, Context context, OnItemClickListener listener) {
        this.mListProduct = mListProduct;
        this.context = context;
        this.listener = listener;
    }

    public void setFillteredList (List<Product> fillteredList){
        this.mListProduct = fillteredList;
        notifyDataSetChanged();
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
        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.product) // Hình ảnh hiển thị khi đang tải
                .error(R.drawable.product) // Hình ảnh hiển thị khi tải lỗi
                .into(holder.imgProducts);
    }

    @Override
    public int getItemCount() {
        if (mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
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
        public void bind(final Product product, final OnItemClickListener listener) {
            // Liên kết dữ liệu sản phẩm với các view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(product);
                }
            });
        }

    }
}
