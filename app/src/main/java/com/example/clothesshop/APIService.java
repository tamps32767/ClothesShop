package com.example.clothesshop;

import com.example.clothesshop.model.Product;
import com.example.clothesshop.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {



    @POST("users/register")
    Call<User> registerUser(@Body User user);

    @POST("users/login")
    Call<User> loginUser(@Body User user);

    @GET("products")
    Call<Product> getProduct(@Body Product product);
}
