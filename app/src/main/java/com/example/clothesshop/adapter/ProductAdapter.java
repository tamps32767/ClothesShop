package com.example.clothesshop.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clothesshop.R;
import com.example.clothesshop.dao.ProductDao;
import com.example.clothesshop.model.Product;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mListProduct;
    private Context context;
    private OnItemClickListener listener;
    private ProductDao productDao;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public ProductAdapter(List<Product> mListProduct, Context context, OnItemClickListener listener, ProductDao productDao) {
        this.mListProduct = mListProduct;
        this.context = context;
        this.listener = listener;
        this.productDao = productDao;
    }

    public void setFilteredList(List<Product> filteredList) {
        this.mListProduct = filteredList;
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
        if (product == null) {
            return;
        }
        holder.bind(product, listener);
        holder.txtNameProducts.setText(product.getName());
        holder.txtPriceProducts.setText(String.valueOf(product.getPrice()));
        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.sp2) // Hình ảnh hiển thị khi đang tải
                .error(R.drawable.sp2) // Hình ảnh hiển thị khi tải lỗi
                .into(holder.imgProducts);

        holder.imgProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogUpdate(mListProduct.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNameProducts, txtPriceProducts;
        private ImageView imgProducts;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameProducts = itemView.findViewById(R.id.txtNameProducts);
            txtPriceProducts = itemView.findViewById(R.id.txtPriceProducts);
            imgProducts = itemView.findViewById(R.id.imgProducts);
        }

        public void bind(Product product, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(product);
                }
            });
        }
    }

    private void showDialogUpdate(Product product){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextInputEditText tfName = view.findViewById(R.id.tfName);
        TextInputEditText tfPrice = view.findViewById(R.id.tfPrice);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        Button btnDelete = view.findViewById(R.id.btnDelete);

        tfName.setText(product.getName());
        tfPrice.setText(String.valueOf(product.getPrice()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer productid = product.getProductid();
                String tensp = tfName.getText().toString();
                String giasp = tfPrice.getText().toString();

                if (tensp.length() == 0 || giasp.length() == 0 ){
                    Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Product productUpdate = new Product(productid, tensp, Double.parseDouble(giasp));
                    boolean check = productDao.suaSP(productUpdate);
                    if (check){
                        Toast.makeText(context, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                        mListProduct.clear();
                        mListProduct = productDao.getProduct();
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }else {
                        Toast.makeText(context, "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int productid = product.getProductid();

                // Xác nhận xóa sản phẩm
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean check = productDao.xoaSP(productid);
                                if (check) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    mListProduct.clear();
                                    mListProduct = productDao.getProduct();
                                    notifyDataSetChanged();
                                    alertDialog.dismiss();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });
    }
}
