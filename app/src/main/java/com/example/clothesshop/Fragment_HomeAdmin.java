package com.example.clothesshop;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.clothesshop.adapter.ProductAdapter;
import com.example.clothesshop.dao.ProductDao;
import com.example.clothesshop.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class Fragment_HomeAdmin extends Fragment {

    private RecyclerView rcvProducts;
    private ProductAdapter productAdapter;
    private List<Product> mListProduct;
    private FloatingActionButton flaAdd;
    private ProductDao productDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__home_admin, container, false);

        rcvProducts = view.findViewById(R.id.rcvProducts);
        flaAdd = view.findViewById(R.id.flaAdd);



        productDao = new ProductDao(getContext());
        loadData();


        flaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });

        return view;
    }


    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextInputEditText tfName = view.findViewById(R.id.tfName);
        TextInputEditText tfPrice = view.findViewById(R.id.tfPrice);
        Button btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp = tfName.getText().toString();
                String giasp = tfPrice.getText().toString();

                if (tensp.length() == 0 || giasp.length() == 0){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Product product = new Product(tensp, Double.parseDouble(giasp));
                    boolean check = productDao.themSP(product);
                    if (check){
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        alertDialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void loadData(){
        mListProduct = productDao.getProduct();
        ArrayList<Product> list = productDao.getProduct();


        rcvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mListProduct = new ArrayList<>();
        productAdapter = new ProductAdapter( list, getContext(), new ProductAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Product product) {
                        Fragment fragment = Fragment_Favourite.newInstance(product);
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.framelayout, fragment) // Replace with the appropriate container ID
                                .addToBackStack(null)
                                .commit();
                    }
                }, productDao);
        rcvProducts.setAdapter(productAdapter);
    }
}
