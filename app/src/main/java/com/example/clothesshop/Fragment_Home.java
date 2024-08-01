package com.example.clothesshop;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
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
    private SearchView svSearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__home, container, false);

        rcvProducts = view.findViewById(R.id.rcvProducts);
        svSearch = view.findViewById(R.id.svSearch);

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fillterList(newText);
                return true;
            }
        });

        ProductDao productDao = new ProductDao(getContext());
        ArrayList<Product> list = productDao.getProduct();

        rcvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mListProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(list, getContext(), new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Fragment fragment = Fragment_Favourite.newInstance(product);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, fragment) // Replace with the appropriate container ID
                        .addToBackStack(null)
                        .commit();
            }
        });
        rcvProducts.setAdapter(productAdapter);

        return view;
    }

    private void fillterList(String newText) {
        List<Product> fillteredList = new ArrayList<>();
        for (Product product : mListProduct) {
            if (product.getName().toLowerCase().contains(newText.toLowerCase())) {
                fillteredList.add(product);
            }
        }
        if (fillteredList.isEmpty()){
        //Toast.makeText(this, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
        }else {
            productAdapter.setFillteredList(fillteredList);
        }
    }
}